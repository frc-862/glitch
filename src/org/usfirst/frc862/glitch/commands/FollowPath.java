package org.usfirst.frc862.glitch.commands;

import com.team254.lib.util.control.Lookahead;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.control.PathFollower;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Twist2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.paths.*;
import org.usfirst.frc862.glitch.state.Kinematics;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.state.RobotState;
import org.usfirst.frc862.util.CommandLogger;

public class FollowPath extends Command {
    protected Path mCurrentPath;
    private RobotState mRobotState;
    private PathFollower mPathFollower;
    private boolean finished;
    private CommandLogger logger;

    public FollowPath() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        super.initialize();

        logger = new CommandLogger("follow");
        //  debug adaptive pursuit and fix -- by next year
//        PathContainer path = new GentleCurve();
//        PathContainer path = new Straight();
        PathContainer path = null;
        Robot.robotStateEstimator.setStartPose(path.getStartPose());
        mCurrentPath = (path.buildPath());

        finished = false;
        Robot.driveTrain.setVelocityMode();
        mRobotState = RobotState.getInstance();
        mRobotState.resetDistanceDriven();

        boolean reversed = false;
        mPathFollower = new PathFollower(mCurrentPath, reversed,
                new PathFollower.Parameters(
                        new Lookahead(Constants.kMinLookAhead, Constants.kMaxLookAhead,
                                Constants.kMinLookAheadSpeed, Constants.kMaxLookAheadSpeed),
                        Constants.kInertiaSteeringGain, Constants.kPathFollowingProfileKp,
                        Constants.kPathFollowingProfileKi, Constants.kPathFollowingProfileKv,
                        Constants.kPathFollowingProfileKffv, Constants.kPathFollowingProfileKffa,
                        Constants.kPathFollowingMaxVel, Constants.kPathFollowingMaxAccel,
                        Constants.kPathFollowingGoalPosTolerance, Constants.kPathFollowingGoalVelTolerance,
                        Constants.kPathStopSteeringDistance));

        logger.addDataElement("t");
        logger.addDataElement("pose_x");
        logger.addDataElement("pose_y");
        logger.addDataElement("pose_theta");
        logger.addDataElement("linear_displacement");
        logger.addDataElement("linear_velocity");
        logger.addDataElement("profile_displacement");
        logger.addDataElement("profile_velocity");
        logger.addDataElement("velocity_command_dx");
        logger.addDataElement("velocity_command_dy");
        logger.addDataElement("velocity_command_dtheta");
        logger.addDataElement("steering_command_dx");
        logger.addDataElement("steering_command_dy");
        logger.addDataElement("steering_command_dtheta");
        logger.addDataElement("cross_track_error");
        logger.addDataElement("along_track_error");
        logger.addDataElement("lookahead_point_x");
        logger.addDataElement("lookahead_point_y");
        logger.addDataElement("lookahead_point_velocity");
        logger.addDataElement("raw_left_pos");
        logger.addDataElement("raw_right_pos");
    }

    @Override
    protected void execute() {
        double timestamp = Timer.getFPGATimestamp();
        RigidTransform2d robot_pose = mRobotState.getLatestFieldToVehicle().getValue();
        Twist2d command = mPathFollower.update(timestamp, robot_pose,
                RobotState.getInstance().getDistanceDriven(), RobotState.getInstance().getPredictedVelocity().dx);
        if (!mPathFollower.isFinished()) {
            Kinematics.DriveVelocity setpoint = Kinematics.inverseKinematics(command);

            double left_inches_per_sec = setpoint.left;
            double right_inches_per_sec = setpoint.right;
            final double max_desired = Math.max(Math.abs(left_inches_per_sec), Math.abs(right_inches_per_sec));
            final double scale = max_desired > Constants.kDriveHighGearMaxSetpoint
                    ? Constants.kDriveHighGearMaxSetpoint / max_desired : 1.0;

            Robot.driveTrain.setVelocityIPS(left_inches_per_sec * scale, right_inches_per_sec * scale);
        } else {
            finished = true;
        }

        logger.set("t", mPathFollower.getDebug().t);
        logger.set("pose_x", mPathFollower.getDebug().pose_x);
        logger.set("pose_y", mPathFollower.getDebug().pose_y);
        logger.set("pose_theta", mPathFollower.getDebug().pose_theta);
        logger.set("linear_displacement", mPathFollower.getDebug().linear_displacement);
        logger.set("linear_velocity", mPathFollower.getDebug().linear_velocity);
        logger.set("profile_displacement", mPathFollower.getDebug().profile_displacement);
        logger.set("profile_velocity", mPathFollower.getDebug().profile_velocity);
        logger.set("velocity_command_dx", mPathFollower.getDebug().velocity_command_dx);
        logger.set("velocity_command_dy", mPathFollower.getDebug().velocity_command_dy);
        logger.set("velocity_command_dtheta", mPathFollower.getDebug().velocity_command_dtheta);
        logger.set("steering_command_dx", mPathFollower.getDebug().steering_command_dx);
        logger.set("steering_command_dy", mPathFollower.getDebug().steering_command_dy);
        logger.set("steering_command_dtheta", mPathFollower.getDebug().steering_command_dtheta);
        logger.set("cross_track_error", mPathFollower.getDebug().cross_track_error);
        logger.set("along_track_error", mPathFollower.getDebug().along_track_error);
        logger.set("lookahead_point_x", mPathFollower.getDebug().lookahead_point_x);
        logger.set("lookahead_point_y", mPathFollower.getDebug().lookahead_point_y);
        logger.set("lookahead_point_velocity", mPathFollower.getDebug().lookahead_point_velocity);
        logger.set("raw_left_pos", Robot.driveTrain.getLeftDistanceInches());
        logger.set("raw_right_pos", Robot.driveTrain.getRightDistanceInches());

        logger.drain();
        logger.write();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {
        Robot.driveTrain.stop();
        logger.flush();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
