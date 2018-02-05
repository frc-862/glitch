package org.usfirst.frc862.glitch.subsystems;

import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Twist2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc862.glitch.state.Kinematics;
import org.usfirst.frc862.glitch.state.RobotState;
import org.usfirst.frc862.util.DataLogger;

public class RobotStateEstimator extends Subsystem {
    RobotState robot_state_ = RobotState.getInstance();
    DriveTrain drive_;
    Core core_;
    double left_encoder_prev_distance_;
    double right_encoder_prev_distance_;

    public RobotStateEstimator(DriveTrain drive, Core core) {
        drive_ = drive;
        core_ = core;
        left_encoder_prev_distance_ = drive_.getLeftDistanceInches();
        right_encoder_prev_distance_ = drive_.getRightDistanceInches();

        DataLogger.addDataElement("robot_pose_x", () -> robot_state_.getLatestFieldToVehicle().getValue().getTranslation().x());
        DataLogger.addDataElement("robot_pose_y", () -> robot_state_.getLatestFieldToVehicle().getValue().getTranslation().y());
        DataLogger.addDataElement("robot_pose_theta", () -> robot_state_.getLatestFieldToVehicle().getValue().getRotation().getDegrees());
    }

    @Override
    protected void initDefaultCommand() {
    }

    @Override
    public synchronized void periodic() {
        double timestamp = Timer.getFPGATimestamp();
        final double left_distance = drive_.getLeftDistanceInches();
        final double right_distance = drive_.getRightDistanceInches();
        final double angle = core_.getGyroAngle();
        final Rotation2d gyro_angle = Rotation2d.fromDegrees(angle);
        final Twist2d odometry_velocity = robot_state_.generateOdometryFromSensors(
                left_distance - left_encoder_prev_distance_,
                right_distance - right_encoder_prev_distance_, gyro_angle);
        final Twist2d predicted_velocity = Kinematics.forwardKinematics(drive_.getLeftVelocityInchesPerSec(),
                drive_.getRightVelocityInchesPerSec());
        robot_state_.addObservations(timestamp, odometry_velocity, predicted_velocity);
        left_encoder_prev_distance_ = left_distance;
        right_encoder_prev_distance_ = right_distance;
        robot_state_.outputToSmartDashboard();
    }

    public void setStartPose(RigidTransform2d startPose) {
        robot_state_.reset(Timer.getFPGATimestamp(), startPose);
    }
}
