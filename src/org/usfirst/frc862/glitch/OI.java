// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc862.glitch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc862.glitch.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.paths.SwitchCurveLeftPath;
import org.usfirst.frc862.glitch.paths.SwitchCurveRightPath;
import org.usfirst.frc862.glitch.paths.SwitchStraightPath;
import org.usfirst.frc862.util.XBoxController;

import static org.usfirst.frc862.glitch.JoystickConstants.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton cubeIntakeButton;
    public JoystickButton cubeEjectButton;
    public Joystick gamePad;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public XBoxController driver;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        gamePad = new Joystick(0);
        
        cubeEjectButton = new JoystickButton(gamePad, 6);
        cubeEjectButton.whileHeld(new EjectCube());
        cubeIntakeButton = new JoystickButton(gamePad, 5);
        cubeIntakeButton.whileHeld(new IntakeCube());


        // SmartDashboard Buttons
        SmartDashboard.putData("Arcade", new Arcade());
        SmartDashboard.putData("Tank Drive", new TankDrive());
        SmartDashboard.putData("Up Shift", new UpShift());
        SmartDashboard.putData("Down Shift", new DownShift());
        SmartDashboard.putData("AutoShift", new AutoShift());
        SmartDashboard.putData("SystemTest", new SystemTest());
        SmartDashboard.putData("CalbratedRun", new CalbratedRun());
        SmartDashboard.putData("WaitForCube", new WaitForCube());
        SmartDashboard.putData("WaitWithCube", new WaitWithCube());
        SmartDashboard.putData("EjectCube", new EjectCube());
        SmartDashboard.putData("Control Four Bar", new ControlFourBar());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        SmartDashboard.putData("Switch Straight", new SwitchStraightPath());
        SmartDashboard.putData("Curve Left", new SwitchCurveLeftPath());
        SmartDashboard.putData("Curve Right", new SwitchCurveRightPath());

        driver = new XBoxController(0);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getGamePad() {
        return gamePad;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    public double getLeftPower() {
        return -gamePad.getRawAxis(JOYSTICK_LEFT_Y_AXIS);
    }

    public double getRightPower() {
        return -gamePad.getRawAxis(JOYSTICK_RIGHT_Y_AXIS);
    }

    public double getRotation() {
        return driver.getLeftStickX();
    }

    public double getThrust() {
        return driver.getThrottleSquared();
    }

    public boolean getQuickTurn() {
        return driver.leftStickButton.get() || driver.startButton.get();
    }
}

