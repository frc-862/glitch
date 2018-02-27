// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc862.glitch.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc862.util.DataLogger;
import org.usfirst.frc862.util.LightningMath;

/**
 *
 */
public class Core extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Compressor compressor = RobotMap.coreCompressor;
    private final PowerDistributionPanel powerDistributionPanel = RobotMap.corePowerDistributionPanel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private AHRS navx = RobotMap.navx;
    private double headingOffset;
    private AnalogInput pressureGauge = new AnalogInput(0);

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        navx.zeroYaw();
        headingOffset = navx.getYaw();
    }

    public Core() {
        super();
        exitTestMode();
        DataLogger.addDataElement("heading", () -> navx.getFusedHeading());
        DataLogger.addDataElement("current", () -> powerDistributionPanel.getTotalCurrent());
        DataLogger.addDataElement("voltage", () -> powerDistributionPanel.getVoltage());
        DataLogger.addDataElement("pressure", () -> getPressure());

        DataLogger.addDataElement("left_collect_current", () -> powerDistributionPanel.getCurrent(Constants.PDP_COLLECTOR_LEFT));
        DataLogger.addDataElement("right_collect_current", () -> powerDistributionPanel.getCurrent(Constants.PDP_COLLECTOR_RIGHT));
        DataLogger.addDataElement("PDP_DRIVE_RIGHT_1", () -> powerDistributionPanel.getCurrent(Constants.PDP_DRIVE_RIGHT_1));
        DataLogger.addDataElement("PDP_DRIVE_RIGHT_2", () -> powerDistributionPanel.getCurrent(Constants.PDP_DRIVE_RIGHT_2));
        DataLogger.addDataElement("PDP_DRIVE_RIGHT_3", () -> powerDistributionPanel.getCurrent(Constants.PDP_DRIVE_RIGHT_3));
        DataLogger.addDataElement("PDP_ARM", () -> powerDistributionPanel.getCurrent(Constants.PDP_ARM));
//        DataLogger.addDataElement("PDP_RAMP", () -> powerDistributionPanel.getCurrent(Constants.PDP_RAMP));
        DataLogger.addDataElement("PDP_LEDS", () -> powerDistributionPanel.getCurrent(Constants.PDP_LEDS));
        DataLogger.addDataElement("PDP_ELEVATOR", () -> powerDistributionPanel.getCurrent(Constants.PDP_ELEVATOR));
        DataLogger.addDataElement("PDP_DRIVE_LEFT_1", () -> powerDistributionPanel.getCurrent(Constants.PDP_DRIVE_LEFT_1));
        DataLogger.addDataElement("PDP_DRIVE_LEFT_2", () -> powerDistributionPanel.getCurrent(Constants.PDP_DRIVE_LEFT_2));
        DataLogger.addDataElement("PDP_DRIVE_LEFT_3", () -> powerDistributionPanel.getCurrent(Constants.PDP_DRIVE_LEFT_3));
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("Heading", navx.getFusedHeading());
        SmartDashboard.putNumber("Pressure", getPressure());
    }

    public double getPressure() { return pressureGauge.getVoltage() / 5 * 250 - 25; }
    public void enterTestMode() {
        compressor.setClosedLoopControl(false);
    }

    public void exitTestMode() {
        compressor.setClosedLoopControl(true);
    }

    public double getGyroAngle() {
        return LightningMath.boundThetaNeg180to180(navx.getYaw() - this.headingOffset);
    }

    public double getTotalCurrent() {
        return powerDistributionPanel.getTotalCurrent();
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

