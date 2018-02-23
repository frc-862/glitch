package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Robot;


public class TestJoySticks extends Command {
    @Override
    protected void execute() {
        SmartDashboard.putNumber("Left Power", Robot.oi.getLeftPower());
        SmartDashboard.putNumber("Right Power", Robot.oi.getRightPower());
        SmartDashboard.putNumber("Left CoPilot", Robot.oi.copilot.getLeftStickY());
        SmartDashboard.putNumber("Right CoPilot", Robot.oi.copilot.getRightStickY());
        SmartDashboard.putNumber("Left Trigger", Robot.oi.copilot.getLeftTrigger());
        SmartDashboard.putNumber("Right Trigger", Robot.oi.copilot.getRightTrigger());
    }


    @Override
    protected boolean isFinished() {
        return false;
    }
}
