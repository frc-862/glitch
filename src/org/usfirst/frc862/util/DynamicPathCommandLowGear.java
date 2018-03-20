package org.usfirst.frc862.util;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.util.ChezyMath;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.subsystems.DriveTrain;
import org.usfirst.frc862.util.FaultCode.Codes;

public class DynamicPathCommandLowGear extends DynamicPathCommandBase {
    private final static double pathP = 7.5;
    private final static double pathI = 0;
    private final static double pathD = 0;
    private final static double pathV = 1;  // Velocity is in IPS, we command IPS
    private final static double pathA = 0;
    private final static double pathTurn = 1; // 1; // 2; // 1.2; // 0.862;
    private final static double pathFeedF = 6; // 4;

    private CommandLogger logger;
    private final TrajectoryFollower followerLeft = new TrajectoryFollower();
    private final TrajectoryFollower followerRight = new TrajectoryFollower();
    private Notifier notifier;
    private double starting_heading;
    private Path path;

    protected DynamicPathCommandLowGear() {
        super();
        setup();
    }

    public DynamicPathCommandLowGear(String name) {
        super(name);
        setup();
    }
        
    private void setup() {
        requires(Robot.driveTrain);

        notifier = new Notifier(this::followPath);
        loadPath();

        if (isReversed()) {
            path.reverse();
        }
    }

    public Path getPath() {
        return null;
    }

    private boolean loadPath() {
        path = getPath();

        return path != null;
    }

    public double duration() {
        Trajectory left = path.getLeftWheelTrajectory();
        Trajectory.Segment point = left.getSegment(left.getNumSegments() - 1);
        return point.dt * left.getNumSegments();
    }
    
    @Override
    protected void initialize() {
        // move this to ensure that we get a new log for each run
        logger = new CommandLogger(this.getName());
        logger.addDataElement("projected_left_pos");
        logger.addDataElement("requested_left_vel");
        logger.addDataElement("actual_left_pos");
        logger.addDataElement("projected_left_vel");
        logger.addDataElement("actual_left_vel");
        logger.addDataElement("projected_right_pos");
        logger.addDataElement("requested_right_vel");
        logger.addDataElement("actual_right_pos");
        logger.addDataElement("projected_right_vel");
        logger.addDataElement("actual_right_vel");
        logger.addDataElement("projected_heading");
        logger.addDataElement("actual_heading");
        logger.addDataElement("angle_diff");
        logger.addDataElement("delta_heading");
        logger.addDataElement("delta_feedf");
        logger.addDataElement("delta_pgain");

        Robot.driveTrain.setVelocityMode();
//        Robot.shifter.downshift();
        
        if (!loadPath()) {
            Logger.error("Failed to load path");
        }

        Robot.driveTrain.resetDistance();
        starting_heading = Robot.core.getGyroAngle();
        followerLeft.configure(pathP, pathI, pathD, pathV, pathA);
        followerRight.configure(pathP, pathI, pathD, pathV, pathA);

        followerLeft.setTrajectory(path.getLeftWheelTrajectory());
        followerLeft.reset();
        followerRight.setTrajectory(path.getRightWheelTrajectory());
        followerRight.reset();

        notifier.startPeriodic(path.getLeftWheelTrajectory().getSegment(0).dt);
    }

    private void followPath() {
        SmartDashboard.putNumber("followPathTime", Timer.getFPGATimestamp());
        SmartDashboard.putNumber("theta_feedf", -42);
        DriveTrain drive = Robot.driveTrain;
        double distanceL = drive.getLeftDistanceInches();
        double distanceR = drive.getRightDistanceInches();

        Trajectory.Segment left = followerLeft.getSegment();
        Trajectory.Segment right = followerRight.getSegment();
        
        double speedLeft = followerLeft.calculate(distanceL);
        double speedRight = followerRight.calculate(distanceR);
        
        double goalHeading = ChezyMath.boundAngleNeg180to180Degrees(Math.toDegrees(followerLeft.getHeading()));
        double deltaHeading = Math.toDegrees(followerLeft.deltaHeading());
        double observedHeading = ChezyMath.getDifferenceInAngleDegrees(Robot.core.getGyroAngle(), starting_heading);
        double angleDiff = ChezyMath.getDifferenceInAngleDegrees(observedHeading, goalHeading);
        double theta_sign = (deltaHeading < 0) ? -1 : 1;
        double theta_feedf = deltaHeading * pathFeedF;
//        double theta_feedf = Math.abs(Math.pow(deltaHeading, 1.25)) * theta_sign; // deltaHeading * pathFeedF;
        SmartDashboard.putNumber("theta_feedf", theta_feedf);

        if (theta_feedf == Double.NaN) {
            theta_feedf = 0;
        }
        double theta_pgain = pathTurn * angleDiff;

//        double turn = pathTurn * angleDiff - pathFeedF * deltaHeading;
        double turn = theta_pgain + theta_feedf;
        double requestedLeft = speedLeft - turn;
        double requestedRight = speedRight + turn;
        
        drive.setVelocityIPS(requestedLeft, requestedRight);

        logger.set("projected_left_pos", left.pos);
        logger.set("requested_left_vel", requestedLeft);
        logger.set("actual_left_pos", distanceL);
        logger.set("projected_left_vel", left.vel);
        logger.set("actual_left_vel", drive.getLeftVelocityInchesPerSec());
        logger.set("projected_right_pos", right.pos);
        logger.set("requested_right_vel", requestedRight);
        logger.set("actual_right_pos", distanceR);
        logger.set("projected_right_vel", right.vel);
        logger.set("actual_right_vel", drive.getRightVelocityInchesPerSec());
        logger.set("projected_heading", goalHeading);
        logger.set("actual_heading", observedHeading);
        logger.set("angle_diff", angleDiff);
        logger.set("delta_heading", deltaHeading);
        logger.set("delta_feedf", theta_feedf);
        logger.set("delta_pgain", theta_pgain);
        logger.write();
    }

    @Override
    protected void execute() {
        logger.drain();
    }

    @Override
    protected void end() {
        Logger.info("Stopping DynamicPath: " + getName());
        notifier.stop();
        logger.drain();
        logger.flush();
        logger.close();

        if (LightningMath.isZero(Robot.driveTrain.getLeftDistanceInches())) {
            FaultCode.write(Codes.LEFT_ENCODER_NOT_FOUND);
        }
        
        if (LightningMath.isZero(Robot.driveTrain.getRightDistanceInches())) {
            FaultCode.write(Codes.RIGHT_ENCODER_NOT_FOUND);
        }

        Robot.driveTrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return followerLeft.isFinishedTrajectory() && 
                followerRight.isFinishedTrajectory();
    }

    public boolean isReversed() {
        return false;
    }

}
