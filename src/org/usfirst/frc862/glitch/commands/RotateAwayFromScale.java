package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.LightningMath;


public class RotateAwayFromScale extends Command {
    private final double degrees;
    private double start;
    private boolean scaleOnLeft;
    private double targetDegree;

    public RotateAwayFromScale(double degrees) {
        this.degrees=degrees;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }


    private double relativeTheta() {
        double result = LightningMath.boundThetaNeg180to180(Robot.core.getGyroAngle() - start);

        if (result > 0 && scaleOnLeft) {
            result -= 360;
        } else if (result < 0 && !scaleOnLeft) {
            result += 360;
        }

        return result;
    }

    private double degreeError() {
        return targetDegree - relativeTheta();
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        scaleOnLeft = Robot.scaleOnLeft();
        start = Robot.core.getGyroAngle();

        if (scaleOnLeft)
            targetDegree = -degrees;
        else
            targetDegree = degrees;
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        double rotpwr = degreeError() * Constants.PRotate;
        boolean isNeg = rotpwr < 0;
        SmartDashboard.putNumber("degree error: ", degreeError());
        rotpwr = Math.max(Math.abs(rotpwr), Constants.MinRotatePower);
        rotpwr = Math.min(rotpwr, 1);
        if (isNeg) {
            rotpwr = -rotpwr;
        }
        Robot.driveTrain.setPower(rotpwr, -rotpwr);
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
        return Math.abs(degreeError()) < Constants.ANGLE_TOLERANCE_DEGREES;
    }

    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }
}
