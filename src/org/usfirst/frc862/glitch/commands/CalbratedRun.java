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
    private double ramp_time = 0;
    private double wave_period = 2;
    private double wave_amplitude = 0.5;

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
        SmartDashboard.putNumber("Calibrated Run kP", 0);
        SmartDashboard.putNumber("Calibrated Run kI", 0);
        SmartDashboard.putNumber("Calibrated Run kD", 0);
        SmartDashboard.putNumber("Calibrated Run kF", 0);
        SmartDashboard.putNumber("Calibrated Run Time", 5.0);
        SmartDashboard.putNumber("Calibrated Run Velocity", 1);
        SmartDashboard.putNumber("Calibrated Run Ramp time", 0);
        SmartDashboard.putNumber("Calibrated Run wave period", 2);
        SmartDashboard.putNumber("Calibrated Run wave amplitude", 0.5);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
		kP = SmartDashboard.getNumber("Calibrated Run kP", 1);
		kI = SmartDashboard.getNumber("Calibrated Run kI", 0);
		kD = SmartDashboard.getNumber("Calibrated Run kD", 0);
		kF = SmartDashboard.getNumber("Calibrated Run kF", 0.57);
		time = SmartDashboard.getNumber("Calibrated Run Time", 5.0);
		velocity = SmartDashboard.getNumber("Calibrated Run Velocity", 700);
		ramp_time = SmartDashboard.getNumber("Calibrated Run Ramp time", 0);
		wave_period = SmartDashboard.getNumber("Calibrated Run wave period", 2);
		wave_amplitude = SmartDashboard.getNumber("Calibrated Run wave amplitude", 0.5);

		DataLogger.setBaseFileName(String.format("calibrated-%06d-%06d-%06d-%06d", 
				Math.round(kP * 1000), Math.round(kI * 1000), 
				Math.round(kD * 1000), Math.round(kF * 1000)));
		
		this.setTimeout(time);
		Robot.driveTrain.setPIDF(kP, kI, kD, kF);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (timeSinceInitialized() < ramp_time) {
            double percent = timeSinceInitialized() / ramp_time;
            double power = velocity * percent;
            Robot.driveTrain.setVelocity(power, power);
        } else {
            double time_since_ramp = timeSinceInitialized() - ramp_time;
            double period = (time_since_ramp / (2 * Math.PI)) * wave_period;
            double percent = 1 - (Math.sin(period) * wave_amplitude);
            double power = velocity * percent;
            Robot.driveTrain.setVelocity(power, power);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        DataLogger.flush();
        DataLogger.setBaseFileName("done");
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    		end();
    }
}
