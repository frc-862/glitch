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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.RobotMap;
import org.usfirst.frc862.glitch.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.usfirst.frc862.util.DataLogger;
import org.usfirst.frc862.util.LightningMath;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


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

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        DataLogger.addDataElement("heading", () -> navx.getFusedHeading());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("Heading", navx.getFusedHeading());
//        SmartDashboard.putNumber("x", navx.getVelocityX());
//        SmartDashboard.putNumber("y", navx.getVelocityY());
//        SmartDashboard.putNumber("z", navx.getVelocityZ());
    }

    public void enterTestMode() {
        compressor.setClosedLoopControl(false);
    }

    public void exitTestMode() {
        compressor.setClosedLoopControl(true);
    }

    public double getGyroAngle() {
        return LightningMath.boundThetaNeg180to180(navx.getFusedHeading());
    }

    public double getTotalCurrent() {
        return powerDistributionPanel.getTotalCurrent();
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

