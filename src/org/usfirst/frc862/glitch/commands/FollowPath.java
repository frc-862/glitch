package org.usfirst.frc862.glitch.commands;

import com.team254.lib.util.control.Lookahead;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.control.PathFollower;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Twist2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.paths.RightStartLeftSwitch;
import org.usfirst.frc862.glitch.state.Kinematics;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.state.RobotState;

public class FollowPath extends Command {
    protected Path mCurrentPath;
    private RobotState mRobotState;
    private PathFollower mPathFollower;
    private boolean finished;

    public FollowPath() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        super.initialize();

        mCurrentPath = (new RightStartLeftSwitch()).buildPath();
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

            SmartDashboard.putNumber("setpoint left", left_inches_per_sec * scale);
            SmartDashboard.putNumber("setpoint right", right_inches_per_sec * scale);
            Robot.driveTrain.setVelocityIPS(left_inches_per_sec * scale, right_inches_per_sec * scale);
        } else {
            finished = true;
        }
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
