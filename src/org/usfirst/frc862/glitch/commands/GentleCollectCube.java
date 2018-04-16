package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.Logger;


public class GentleCollectCube extends Command {
    public GentleCollectCube() {
        // eg. requires(chassis);
    }


    @Override
    protected void initialize() {
        Logger.info("Starting GentleCollectCube");
        Robot.gripper.collectCube();
        Robot.driveTrain.setVelocityIPS(15,15);
    }


    @Override
    protected void execute() {
        Robot.driveTrain.setVelocityIPS(15, 15);
        Logger.debug("Gentle collect " + Robot.driveTrain.drivePowerMagnitude());
    }


    @Override
    protected boolean isFinished() {
        return Robot.gripper.hasCube();
    }


    @Override
    protected void end() {
        Logger.info("Ending GentleCollectCube");
        if (Robot.gripper.hasCube()) {
            Robot.gripper.holdCube();
        } else {
            Robot.gripper.stopIntake();
        }

        Robot.driveTrain.stop();
    }
}
