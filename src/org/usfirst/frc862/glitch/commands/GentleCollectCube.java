package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.Logger;


public class GentleCollectCube extends Command {
    final double speed = 20;
    public GentleCollectCube() {
        // eg. requires(chassis);
    }


    @Override
    protected void initialize() {
        Logger.info("Starting GentleCollectCube");
        Robot.gripper.collectCube();
        Robot.driveTrain.setVelocityIPS(speed,speed);
    }


    @Override
    protected void execute() {
        Robot.driveTrain.setVelocityIPS(speed, speed);
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
