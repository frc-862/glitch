package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Robot;


public class StartVisionRecord extends Command {
    public StartVisionRecord() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }


    @Override
    protected void initialize() {
        Robot.cubeVision.startVisionRecord();
    }


    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }
}
