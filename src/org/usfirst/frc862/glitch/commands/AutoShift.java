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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.util.CommandLogger;
import org.usfirst.frc862.util.LightningTimer;
import org.usfirst.frc862.util.Logger;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShift extends Command {
    private enum State {
        LOW_GEAR, UP_SHIFTING, DOWN_SHIFTING, HIGH_GEAR, HYSTERESIS_DELAY
    }
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private State state;
    private final CommandLogger logger;

    @Override
    protected void end() {
        logger.drain();
        logger.flush();
    }

    @Override
    protected void interrupted() {
        end();
    }

    private final LightningTimer lowGearUpshiftTimer;
    private final LightningTimer highGearDownshiftTimer;
    private final LightningTimer lastShiftTimer;
    private final LightningTimer overtakeTimer;
    private final LightningTimer currentTimer;
    private final LightningTimer loggerTimer;
    
    private double originalLDistance, originalRDistance;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutoShift() {

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shifter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        lowGearUpshiftTimer = new LightningTimer();
        highGearDownshiftTimer = new LightningTimer();
        lastShiftTimer = new LightningTimer();
        overtakeTimer = new LightningTimer();
        currentTimer = new LightningTimer();
        loggerTimer = new LightningTimer();
        
        logger = new CommandLogger(getName());
        logger.addDataElement("left_velocity");
        logger.addDataElement("right_velocity");
        logger.addDataElement("ave_vel");
        logger.addDataElement("ave_req");
//        logger.addDataElement("left_encoder");
//        logger.addDataElement("right_encoder");
//        logger.addDataElement("straighten");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        state = (Robot.shifter.isHighGear()) ? State.HIGH_GEAR : State.LOW_GEAR;
        resetTimers();
        originalLDistance = Robot.driveTrain.getLeftDistanceInches();
        originalRDistance = Robot.driveTrain.getRightDistanceInches();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        logger.set("left_velocity", Robot.driveTrain.getLeftVelocityInchesPerSec());
        logger.set("right_velocity", Robot.driveTrain.getRightVelocityInchesPerSec());
        logger.set("ave_vel", Robot.driveTrain.getAbsVelocity());
        logger.set("ave_req", Robot.driveTrain.getRequestedVelocity());
        logger.write();

        SmartDashboard.putString("auto shift state",state.toString());
        switch (state) {
            case HIGH_GEAR:
                high_gear();
                break;

            case HYSTERESIS_DELAY:
                if (lastShiftTimer.hasPeriodPassed(Constants.shiftHysteresis)) {
                    state = (Robot.shifter.isHighGear()) ? State.HIGH_GEAR : State.LOW_GEAR;
                    logger.drain();
                    logger.flush();
                    resetTimers();
                }
                break;

            case LOW_GEAR:
                low_gear();
                break;

            case UP_SHIFTING:
                originalRDistance = Robot.driveTrain.getRightDistanceInches();
                originalLDistance = Robot.driveTrain.getLeftDistanceInches();
                Robot.shifter.upshift();
                resetTimers();
                overtakeTimer.reset();
                state = State.HYSTERESIS_DELAY;
                break;

            case DOWN_SHIFTING:
                Robot.shifter.downshift();
                state = State.HYSTERESIS_DELAY;
                resetTimers();
                break;
        }

        if (loggerTimer.hasPeriodPassed(5)) {
            logger.drain();
            logger.flush();
            loggerTimer.reset();
        }
    }

    private void resetTimers() {
        lowGearUpshiftTimer.reset();
        highGearDownshiftTimer.reset();
        lastShiftTimer.reset();
        currentTimer.reset();
        //loggerTimer.reset();
    }

    private void low_gear() {
        SmartDashboard.putNumber("abs velocity", Robot.driveTrain.getAbsVelocity());
        SmartDashboard.putNumber("min up shift velocity",Constants.MinUpshiftVelocity);
        SmartDashboard.putNumber("requested velocity",Robot.driveTrain.getRequestedVelocity());
        SmartDashboard.putNumber("min requsted velocity for up shift",Constants.MinRequestedVelocityForUpshift);
        if (Robot.driveTrain.getAbsVelocity() > Constants.MinUpshiftVelocity &&
            Robot.driveTrain.getRequestedVelocity() > Constants.MinRequestedVelocityForUpshift) {
                state = State.UP_SHIFTING;
        }
    }

    private void high_gear() {
        if (Robot.core.getTotalCurrent() > Constants.HighCurrentThreshold) {
            if (currentTimer.hasPeriodPassed(Constants.highCurrentTimeout)) {
                Logger.info("Stall current downshift: " + Robot.core.getTotalCurrent());
                state = State.DOWN_SHIFTING;
                return;
            }
        } else {
            currentTimer.reset();
        }

        double crash = RobotMap.navx.getWorldLinearAccelY();
        if (crash < Constants.CrashDeacceleration) {
            Logger.info("Crash Downshift: " + crash);
            state = State.DOWN_SHIFTING;
            return;
        }

        if (Robot.driveTrain.getAbsVelocity() < Constants.CoastVelocity) {
            if (highGearDownshiftTimer.hasPeriodPassed(Constants.CoastTriggerTime)) {
                state = State.DOWN_SHIFTING;
            }
        } else {
            highGearDownshiftTimer.reset();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }


}
