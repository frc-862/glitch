package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Robot;

/**
 * Collects a cube by toggling the collector on and off repeatedly
 */
public class PulseCollectCommand extends Command {
  public PulseCollectCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }


  /**
   * The initialize method is called just before the first time
   * this Command is run after being started.
   */
  @Override
  protected void initialize() {
  }


  /**
   * The execute method is called repeatedly when this Command is
   * scheduled to run until this Command either finishes or is canceled.
   */
  @Override
  protected void execute() {
    // We'll have to do something fancier if we want more than a second to work with
    double inSecond = Timer.getFPGATimestamp() - Math.floor(Timer.getFPGATimestamp());
    if(inSecond < 0.75) {
      Robot.gripper.collectCube();
    } else {
      Robot.gripper.stopIntake(); // TODO should this be holdCube?
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
    return Robot.gripper.hasCube();
  }


  /**
   * Called once when the command ended peacefully; that is it is called once
   * after {@link #isFinished()} returns true. This is where you may want to
   * wrap up loose ends, like shutting off a motor that was being used in the
   * command.
   */
  @Override
  protected void end() {
    Robot.gripper.holdCube();
  }
}
