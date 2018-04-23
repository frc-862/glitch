package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.LightningMath;
import org.usfirst.frc862.util.Logger;


public class SmartRotate extends Command {
    enum State { initialize, motionMagicRampUp, motionMagicWait, finish };
    State state;

    protected double degrees;
    private double startAngle;
    private boolean reverseRotate;

    public SmartRotate(double degrees) {
        this.degrees = LightningMath.boundThetaNeg180to180(degrees);
        reverseRotate = false;

        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }


    public SmartRotate(double degrees, boolean reverse) {
        this.degrees = LightningMath.boundThetaNeg180to180(degrees);
        this.reverseRotate = reverse;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }


    protected double getRelativeHeading() {
        return LightningMath.boundThetaNeg180to180(Robot.core.getGyroAngle() - startAngle);
    }

    protected double getError() {
        return LightningMath.boundThetaNeg180to180(getRelativeHeading() - getGoal());
    }

    protected void setGoal(double goal) {
        degrees = goal;
    }

    protected double getGoal() {
        return degrees;
    }

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        // Important to set this for getRelativeHeading and therefore getError to work correctly
        startAngle = Robot.core.getGyroAngle();
        state = State.initialize;

        Logger.debug("SmartRotate to " + getGoal());
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    public void execute() {
        final double minMoveIPS = 25;
        final double minMovePowerPercent = 0.2;
        final double error = getError();
        final double magnitude = Robot.driveTrain.drivePowerMagnitude();

        Logger.debug("SR info: " + error + ", " + magnitude + ", " + state);

        switch (state) {
            case initialize:
                Logger.debug("SR init goal: " + getGoal());
                double estimatedWheelDistance = getGoal() * Constants.angleToTick;
                if (reverseRotate) {
                    Logger.info("Reversing SmartRotate angle");
                    if (estimatedWheelDistance < 0) {
                        estimatedWheelDistance = estimatedWheelDistance + (360 * Constants.angleToTick);
                    } else {
                        estimatedWheelDistance = estimatedWheelDistance - (360 * Constants.angleToTick);
                    }
                }
                Logger.debug("SR init: " + estimatedWheelDistance);
                if (Math.abs(getGoal()) > 20) {
                    Robot.driveTrain.setMotionMagic(estimatedWheelDistance, -estimatedWheelDistance);
                    state = State.motionMagicRampUp;
                } else {
                    state = State.finish;
                }
                break;

            case motionMagicRampUp:
                if (Robot.driveTrain.drivePowerMagnitude() > minMovePowerPercent) {
                    state = State.motionMagicWait;
                }
                break;

            case motionMagicWait:
                if (Robot.driveTrain.drivePowerMagnitude() < minMovePowerPercent) {
                    state = State.finish;
                    Robot.driveTrain.setVelocityMode();
                }
                break;

            case finish:
                double speed = Math.min(100, Math.abs(error) * 0.6);
                speed = Math.max(speed, minMoveIPS);
                if (error < 0) speed = -speed;
                Robot.driveTrain.setVelocityIPS(-speed, speed);
                break;
        }
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    public boolean isFinished() {
        return Math.abs(getError()) < Constants.ANGLE_TOLERANCE_DEGREES;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    public void end() {
        Robot.driveTrain.setVelocityMode();
        Robot.driveTrain.stop();
    }
}
