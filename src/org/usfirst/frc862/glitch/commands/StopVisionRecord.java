package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Robot;


public class StopVisionRecord extends Command {
    public StopVisionRecord() {
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        Robot.cubeVision.stopVisionRecord();
    }


    @Override
    protected boolean isFinished() {
        return true;
    }
}
