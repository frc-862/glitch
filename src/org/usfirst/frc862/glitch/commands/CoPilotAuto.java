// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc862.glitch.commands;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.Logger;
import org.usfirst.frc862.util.XBoxController;

/**
 *
 */
public class CoPilotAuto extends Command {
    private XBoxController copilot;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public CoPilotAuto() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.lift);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.gripper);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize(){
        Logger.info("Start copilot auto");
        copilot = Robot.oi.copilot;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        boolean cubed = Robot.gripper.hasCube();

        if (copilot.getRightTrigger() > Constants.COLLECT_TRIGGER_DEADZONE) {
            Robot.gripper.holdCube();
        } else if (copilot.startButton.get() || copilot.isDPadUp()) {
            Robot.gripper.setPower(Constants.DEFAULT_COLLECT_POWER, -Constants.DEFAULT_COLLECT_POWER * 0.5);
        } else {
            double power = -copilot.getLeftTrigger() * Constants.EJECT_MAX_FEATHER;

            if (Math.abs(power) < Constants.COLLECT_TRIGGER_DEADZONE) {
                power = 0;
            }

            if (copilot.leftBumper.get()) {
                power = Constants.DEFAULT_EJECT_POWER;
            }
            else if(copilot.rightBumper.get()) {
                power = Constants.DEFAULT_COLLECT_POWER;
            }

//            if (copilot.getRightStickY() < -0.5) {
//                Robot.gripper.setPulsePower(1);
//            } else {
                Robot.gripper.setPower(power);
//            }
        }

        // Y
        //X B
        // A
        if(copilot.aButton.get()) {
            Robot.lift.moveToCollect();
        } else if(copilot.xButton.get()) {
            Robot.lift.moveToBottom();
        } else if(copilot.bButton.get()) {
            Robot.lift.moveToSwitch();
        } else if(copilot.yButton.get()) {
          Robot.lift.moveToScale();
        } else if (Robot.lift.atSwitch() || Robot.lift.atPortal()) {
            if (copilot.getLeftStickY() < -0.5) {
                Robot.lift.moveToPortal();
            } else if (copilot.getLeftStickY() > 0.5) {
                Robot.lift.moveToSwitch();
            }
        } else if (Robot.lift.atScale()) {
            if(copilot.getLeftStickY() > .5) {
                Robot.lift.moveScaleUp();
            }
            else if(copilot.getLeftStickY() < -.5) {
                Robot.lift.moveScaleDown();
            }
        } else if (Robot.lift.atCollect()) {
            if (copilot.getLeftStickY() < -0.5) {
                Robot.lift.dropMode();
            } else if (Robot.lift.isDropMode() && Robot.lift.readyToDrop()){
                Robot.lift.exitManualPosition();
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Logger.info("Stop copilot auto");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
