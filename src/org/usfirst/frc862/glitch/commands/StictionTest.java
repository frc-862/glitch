package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Robot;


public class StictionTest extends Command {
    final private static double STICTION_DISTANCE = 3.0;
    final private static double STICTION_PERIOD = 1.0;
    private static final double STICTION_INCREMENT = 0.1;
    private double speed;
    private double stopPosition;
    private double nextIncrement;

    public StictionTest() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }


    private double getPosition() {
        return (Robot.driveTrain.getLeftDistanceInches() + Robot.driveTrain.getRightDistanceInches()) / 2;
    }

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        stopPosition = getPosition() + STICTION_DISTANCE;
        speed = 0;
        nextIncrement = timeSinceInitialized() + STICTION_PERIOD;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        if (timeSinceInitialized() >= nextIncrement) {
            speed += STICTION_INCREMENT;
        }
        Robot.driveTrain.setVelocityIPS(speed, speed);
        SmartDashboard.putNumber("Stiction Speed", speed);
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
        return getPosition() > stopPosition;
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
