package org.usfirst.frc862.util;

import com.team254.lib.util.SmartDashboardUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.util.FaultCode.Codes;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.subsystems.DriveTrain;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.util.ChezyMath;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;

public class DynamicPathCommand extends DynamicPathCommandBase {
    private CommandLogger logger;
    private TrajectoryFollower followerLeft = new TrajectoryFollower();
    private TrajectoryFollower followerRight = new TrajectoryFollower();
    private Notifier notifier;
    private double starting_heading;
    private Path path;

    public DynamicPathCommand() {
        super();
        setup();
    }
        
    public DynamicPathCommand(String name) {
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

    protected boolean loadPath() {
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
        Logger.info("Starting path " + this.getName());

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
        Robot.shifter.forceUpShift();
        
        if (!loadPath()) {
            Logger.error("Failed to load path");
        }

        Robot.driveTrain.resetDistance();
        starting_heading = Robot.core.getGyroAngle();
        followerLeft.configure(Constants.pathP, Constants.pathI, Constants.pathD, Constants.pathV, Constants.pathA);        
        followerRight.configure(Constants.pathP, Constants.pathI, Constants.pathD, Constants.pathV, Constants.pathA);

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
        double theta_feedf = deltaHeading * Constants.pathFeedF;
//        double theta_feedf = Math.abs(Math.pow(deltaHeading, 1.25)) * theta_sign; // deltaHeading * Constants.pathFeedF;
        SmartDashboard.putNumber("theta_feedf", theta_feedf);

        if (theta_feedf == Double.NaN) {
            theta_feedf = 0;
        }
        double theta_pgain = Constants.pathTurn * angleDiff;

//        double turn = Constants.pathTurn * angleDiff - Constants.pathFeedF * deltaHeading;
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
