package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;


public class GentleCollectCube extends Command {
    public GentleCollectCube() {
        // eg. requires(chassis);
    }


    @Override
    protected void initialize() {
        Robot.gripper.collectCube();
        Robot.driveTrain.setVelocityIPS(4,4);
    }


    @Override
    protected void execute() { }


    @Override
    protected boolean isFinished() {
        return Robot.gripper.hasCube();
    }


    @Override
    protected void end() {
        if (Robot.gripper.hasCube()) {
            Robot.gripper.holdCube();
        } else {
            Robot.gripper.stopIntake();
        }

        Robot.driveTrain.stop();
    }
}
