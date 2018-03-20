package com.team254.lib.util.control;

import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Twist2d;
import com.team254.lib.util.motion.MotionProfileConstraints;
import com.team254.lib.util.motion.MotionProfileGoal;
import com.team254.lib.util.motion.MotionProfileGoal.CompletionBehavior;
import com.team254.lib.util.motion.MotionState;
import com.team254.lib.util.motion.ProfileFollower;

/**
 * A PathFollower follows a predefined path using a combination of feedforward and feedback control. It uses an
 * AdaptivePurePursuitController to choose a reference pose and generate a steering command (curvature), and then a
 * ProfileFollower to generate a profile (displacement and velocity) command.
 */
public class PathFollower {
    private static final double kReallyBigNumber = 1E6;

    public static class DebugOutput {
        double t;
        double pose_x;
        double pose_y;
        double pose_theta;
        double linear_displacement;
        double linear_velocity;
        double profile_displacement;
        double profile_velocity;
        double velocity_command_dx;
        double velocity_command_dy;
        double velocity_command_dtheta;
        double steering_command_dx;
        double steering_command_dy;
        double steering_command_dtheta;
        double cross_track_error;
        double along_track_error;
        double lookahead_point_x;
        double lookahead_point_y;
        double lookahead_point_velocity;
    }

    static class Parameters {
        final Lookahead lookahead;
        final double inertia_gain;
        final double profile_kp;
        final double profile_ki;
        final double profile_kv;
        final double profile_kffv;
        final double profile_kffa;
        final double profile_max_abs_vel;
        final double profile_max_abs_acc;
        final double goal_pos_tolerance;
        final double goal_vel_tolerance;
        final double stop_steering_distance;

        public Parameters(Lookahead lookahead, double inertia_gain, double profile_kp, double profile_ki,
                double profile_kv, double profile_kffv, double profile_kffa, double profile_max_abs_vel,
                double profile_max_abs_acc, double goal_pos_tolerance, double goal_vel_tolerance,
                double stop_steering_distance) {
            this.lookahead = lookahead;
            this.inertia_gain = inertia_gain;
            this.profile_kp = profile_kp;
            this.profile_ki = profile_ki;
            this.profile_kv = profile_kv;
            this.profile_kffv = profile_kffv;
            this.profile_kffa = profile_kffa;
            this.profile_max_abs_vel = profile_max_abs_vel;
            this.profile_max_abs_acc = profile_max_abs_acc;
            this.goal_pos_tolerance = goal_pos_tolerance;
            this.goal_vel_tolerance = goal_vel_tolerance;
            this.stop_steering_distance = stop_steering_distance;
        }
    }

    private final AdaptivePurePursuitController mSteeringController;
    private Twist2d mLastSteeringDelta;
    private final ProfileFollower mVelocityController;
    private final double mInertiaGain;
    private boolean overrideFinished = false;
    private boolean doneSteering = false;
    private final DebugOutput mDebugOutput = new DebugOutput();

    private final double mMaxProfileVel;
    private final double mMaxProfileAcc;
    private final double mGoalPosTolerance;
    private final double mGoalVelTolerance;
    private final double mStopSteeringDistance;
    private double mCrossTrackError = 0.0;
    private double mAlongTrackError = 0.0;

    /**
     * Create a new PathFollower for a given path.
     */
    public PathFollower(Path path, boolean reversed, Parameters parameters) {
        mSteeringController = new AdaptivePurePursuitController(path, reversed, parameters.lookahead);
        mLastSteeringDelta = Twist2d.identity();
        mVelocityController = new ProfileFollower(parameters.profile_kp, parameters.profile_ki, parameters.profile_kv,
                parameters.profile_kffv, parameters.profile_kffa);
        mVelocityController.setConstraints(
                new MotionProfileConstraints(parameters.profile_max_abs_vel, parameters.profile_max_abs_acc));
        mMaxProfileVel = parameters.profile_max_abs_vel;
        mMaxProfileAcc = parameters.profile_max_abs_acc;
        mGoalPosTolerance = parameters.goal_pos_tolerance;
        mGoalVelTolerance = parameters.goal_vel_tolerance;
        mInertiaGain = parameters.inertia_gain;
        mStopSteeringDistance = parameters.stop_steering_distance;
    }

    /**
     * Get new velocity commands to follow the path.
     * 
     * @param t
     *            The current timestamp
     * @param pose
     *            The current robot pose
     * @param displacement
     *            The current robot displacement (total distance driven).
     * @param velocity
     *            The current robot velocity.
     * @return The velocity command to apply
     */
    public synchronized Twist2d update(double t, RigidTransform2d pose, double displacement, double velocity) {
        if (!mSteeringController.isFinished()) {
            final AdaptivePurePursuitController.Command steering_command = mSteeringController.update(pose);
            mDebugOutput.lookahead_point_x = steering_command.lookahead_point.x();
            mDebugOutput.lookahead_point_y = steering_command.lookahead_point.y();
            mDebugOutput.lookahead_point_velocity = steering_command.end_velocity;
            mDebugOutput.steering_command_dx = steering_command.delta.dx;
            mDebugOutput.steering_command_dy = steering_command.delta.dy;
            mDebugOutput.steering_command_dtheta = steering_command.delta.dtheta;
            mCrossTrackError = steering_command.cross_track_error;
            mLastSteeringDelta = steering_command.delta;
            mVelocityController.setGoalAndConstraints(
                    new MotionProfileGoal(displacement + steering_command.delta.dx,
                            Math.abs(steering_command.end_velocity), CompletionBehavior.VIOLATE_MAX_ACCEL,
                            mGoalPosTolerance, mGoalVelTolerance),
                    new MotionProfileConstraints(Math.min(mMaxProfileVel, steering_command.max_velocity),
                            mMaxProfileAcc));

            if (steering_command.remaining_path_length < mStopSteeringDistance) {
                doneSteering = true;
            }
        }

        final double velocity_command = mVelocityController.update(new MotionState(t, displacement, velocity, 0.0), t);
        mAlongTrackError = mVelocityController.getPosError();
        final double curvature = mLastSteeringDelta.dtheta / mLastSteeringDelta.dx;
        double dtheta = mLastSteeringDelta.dtheta;
        if (!Double.isNaN(curvature) && Math.abs(curvature) < kReallyBigNumber) {
            // Regenerate angular velocity command from adjusted curvature.
            final double abs_velocity_setpoint = Math.abs(mVelocityController.getSetpoint().vel());
            dtheta = mLastSteeringDelta.dx * curvature * (1.0 + mInertiaGain * abs_velocity_setpoint);
        }
        final double scale = velocity_command / mLastSteeringDelta.dx;
        final Twist2d rv = new Twist2d(mLastSteeringDelta.dx * scale, 0.0, dtheta * scale);

        // Fill out debug.
        mDebugOutput.t = t;
        mDebugOutput.pose_x = pose.getTranslation().x();
        mDebugOutput.pose_y = pose.getTranslation().y();
        mDebugOutput.pose_theta = pose.getRotation().getRadians();
        mDebugOutput.linear_displacement = displacement;
        mDebugOutput.linear_velocity = velocity;
        mDebugOutput.profile_displacement = mVelocityController.getSetpoint().pos();
        mDebugOutput.profile_velocity = mVelocityController.getSetpoint().vel();
        mDebugOutput.velocity_command_dx = rv.dx;
        mDebugOutput.velocity_command_dy = rv.dy;
        mDebugOutput.velocity_command_dtheta = rv.dtheta;
        mDebugOutput.cross_track_error = mCrossTrackError;
        mDebugOutput.along_track_error = mAlongTrackError;

        return rv;
    }

    public double getCrossTrackError() {
        return mCrossTrackError;
    }

    public double getAlongTrackError() {
        return mAlongTrackError;
    }

    public DebugOutput getDebug() {
        return mDebugOutput;
    }

    public boolean isFinished() {
        return (mSteeringController.isFinished() && mVelocityController.isFinishedProfile()
                && mVelocityController.onTarget()) || overrideFinished;
    }

    public void forceFinish() {
        overrideFinished = true;
    }

    public boolean hasPassedMarker(String marker) {
        return mSteeringController.hasPassedMarker(marker);
    }
}
