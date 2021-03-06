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

import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc862.glitch.commands.AutoShift;
import org.usfirst.frc862.util.DataLogger;


/**
 *
 */
public class Shifter extends Subsystem {
    int state = -1;
    boolean highGear = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DoubleSolenoid shift = RobotMap.shifterShift;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Shifter() {
        DataLogger.addDataElement("Shifter",() -> state);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new AutoShift());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    public void upshift() {
        state = 20;
        Robot.driveTrain.upShiftBegin();
        forceUpShift();
    }

    public void forceUpShift() {
        state = 20;
        shift.set(DoubleSolenoid.Value.kForward);
        Robot.driveTrain.upShiftEnd();
        state = 2;
        highGear = true;
    }

    public void downshift() {
        state = 10;
        Robot.driveTrain.downShiftBegin();
        forceDownShift();
    }

    public void forceDownShift() {
        state = 10;
        shift.set(DoubleSolenoid.Value.kReverse);
        Robot.driveTrain.downShiftEnd();
        state = 1;
        highGear = false;
    }

    public boolean isHighGear() {
        return highGear;
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

