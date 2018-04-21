package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.LightningMath;
import org.usfirst.frc862.util.Logger;


public class SmartRotate extends Command {
    protected double degrees;
    private double startAngle;
    private boolean done = false;
    private boolean reverseRotate = false;
    private boolean is_magic = true;

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
    private boolean startedMoving = false;

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        // Important to set this for getRelativeHeading and therefore getError to work correctly
        startAngle = Robot.core.getGyroAngle();
        is_magic = Math.abs(getError()) > 15;

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
        Robot.driveTrain.setMotionMagic(estimatedWheelDistance, -estimatedWheelDistance);

        Logger.debug("SmartRotate to " + getGoal());
        startedMoving = false;
        done = false;
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        final double error = getError();
        final double estimatedWheelDistance = error * Constants.angleToTick;
        Logger.debug("SR  new: " + estimatedWheelDistance);
//        Logger.debug("SR old: " + Robot.driveTrain.getError());
        Logger.debug("SR lold: " + Robot.driveTrain.getLeftError());
        Logger.debug("SR rold: " + Robot.driveTrain.getRightError());

//        Robot.driveTrain.updateMotionMagic(estimatedWheelDistance, -estimatedWheelDistance);

        SmartDashboard.putNumber("Rotate Error ", error);
        SmartDashboard.putString("Rotate Mode ", is_magic ? "magic" : "not magic");
        SmartDashboard.putNumber("Rotate Power ", Robot.driveTrain.drivePowerMagnitude());

        if (!is_magic) {
            double pwr = 20;
            if (Math.abs(error) < 5) pwr = 15;
            if (error < 0) pwr = -pwr;
            if (reverseRotate) pwr = -pwr;
            Robot.driveTrain.setVelocityIPS(pwr, -pwr);
        }
        // TODO done should probably also verify that velocity is below some threshold too...
        Logger.debug("SmartRotate Error " + getError() + " driveMode " + Robot.driveTrain.getMode() + " error " + Robot.driveTrain.driveErrorMagnitude() + " power " + Robot.driveTrain.drivePowerMagnitude());

        if (!startedMoving && Robot.driveTrain.drivePowerMagnitude() > 0.2) {
            startedMoving = true;
        }

        if (startedMoving && Robot.driveTrain.drivePowerMagnitude() < 0.15) {
            Logger.debug("Done rotating");
            Robot.driveTrain.setVelocityMode();
            is_magic = false;
        } else if (Math.abs(error) < 15) {
            is_magic = false;
        } else if (Math.abs(error) < Constants.ANGLE_TOLERANCE_DEGREES) {
            Logger.debug("Done rotating");
            done = true;
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
    protected boolean isFinished() {
        return done;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        Robot.driveTrain.setVelocityMode();
        Robot.driveTrain.stop();
    }
}
