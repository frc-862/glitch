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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.util.DataLogger;

import com.team254.lib.util.SmartDashboardUtil;

/**
 *
 */
public class CalbratedRun extends Command {

    private double kP = 0;
	private double kI = 0;
	private double kD = 0;
	private double kF = 0;
	private double time = 5.0;
	private double velocity = 600;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public CalbratedRun() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
		kP = SmartDashboard.getNumber("Calibrated Run kP", 0);
		kI = SmartDashboard.getNumber("Calibrated Run kP", 0);
		kD = SmartDashboard.getNumber("Calibrated Run kP", 0);
		kF = SmartDashboard.getNumber("Calibrated Run kP", 0);
		time = SmartDashboard.getNumber("Calibrated Run kP", 5.0);
		velocity = SmartDashboard.getNumber("Calibrated Run kP", 600);
		
		DataLogger.setBaseFileName(String.format("calibrated-%06d-%06d-%06d-%06d", 
				Math.round(kP * 1000), Math.round(kI * 1000), 
				Math.round(kD * 1000), Math.round(kF * 1000)));
		
		this.setTimeout(time);
		Robot.driveTrain.setPIDF(kP, kI, kD, kF);
		Robot.driveTrain.setVelocity(velocity, velocity);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    		// do nothing
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    		Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    		end();
    }
}
