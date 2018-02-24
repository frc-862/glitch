package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Robot;


public class HoldCube extends Command {
    public HoldCube() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.gripper);
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        Robot.gripper.holdCube();
    }


    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.gripper.stopIntake();
    }
}
