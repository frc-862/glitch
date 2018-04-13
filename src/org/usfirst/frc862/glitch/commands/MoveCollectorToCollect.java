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
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.Logger;

/**
 *
 */
public class MoveCollectorToCollect extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private boolean dropDown;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public MoveCollectorToCollect() {
        dropDown = false;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    public MoveCollectorToCollect(boolean drop) {
        dropDown = drop;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.lift.moveToCollect();
        Logger.info("MoveCollectorToCollect");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Logger.debug("MoveCollectorToCollect: " + Robot.lift.getElevatorPosition() + " -- " + Robot.lift.getFourbarPhysicalPosition());
        Logger.debug("atCollect: " + Robot.lift.atCollect());

        if (Robot.lift.getFourbarPhysicalPosition() < 175) {
            Robot.lift.dropMode();
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
