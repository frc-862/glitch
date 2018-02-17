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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.paths.CurveLeftSwitch;
import org.usfirst.frc862.glitch.paths.CurveRightSwitch;
import org.usfirst.frc862.glitch.paths.StraightSwitch;
import org.usfirst.frc862.glitch.paths.TestPath;
import org.usfirst.frc862.util.XBoxController;

import static org.usfirst.frc862.glitch.JoystickConstants.JOYSTICK_LEFT_Y_AXIS;
import static org.usfirst.frc862.glitch.JoystickConstants.JOYSTICK_RIGHT_Y_AXIS;


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
    public Joystick driverLeft;
    public Joystick driverRight;
    public JoystickButton cubeIntakeButton;
    public JoystickButton cubeEjectButton;
    public JoystickButton collect2Switch;
    public JoystickButton collect2Scale;
    public JoystickButton collect2Ground;
    public Joystick gamePad;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public XBoxController driver;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        gamePad = new Joystick(2);
        
        collect2Ground = new JoystickButton(gamePad, 3);
        collect2Ground.whenPressed(new MoveCollectorToGround());
        collect2Scale = new JoystickButton(gamePad, 2);
        collect2Scale.whenPressed(new MoveCollectorToScale());
        collect2Switch = new JoystickButton(gamePad, 1);
        collect2Switch.whenPressed(new MoveCollectorToSwitch());
        cubeEjectButton = new JoystickButton(gamePad, 6);
        cubeEjectButton.whileHeld(new EjectCube());
        cubeIntakeButton = new JoystickButton(gamePad, 5);
        cubeIntakeButton.whileHeld(new IntakeCube());
        driverRight = new Joystick(1);
        
        driverLeft = new Joystick(0);
        


        // SmartDashboard Buttons
        SmartDashboard.putData("Arcade", new Arcade());
        SmartDashboard.putData("Tank Drive", new TankDrive());
        SmartDashboard.putData("Up Shift", new UpShift());
        SmartDashboard.putData("Down Shift", new DownShift());
        SmartDashboard.putData("AutoShift", new AutoShift());
        SmartDashboard.putData("CalbratedRun", new CalbratedRun());
        SmartDashboard.putData("TurnDegrees: right", new TurnDegrees(90));
        SmartDashboard.putData("TurnDegrees: reverse", new TurnDegrees(180));
        SmartDashboard.putData("TurnDegrees: left", new TurnDegrees(-90));
        SmartDashboard.putData("TurnToAbsolutePosition: Forward", new TurnToAbsolutePosition(0));
        SmartDashboard.putData("TurnToAbsolutePosition: Reverse", new TurnToAbsolutePosition(180));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        SmartDashboard.putData("System Test", new SystemTest());
        SmartDashboard.putData("Switch Straight", new StraightSwitch());
        SmartDashboard.putData("Curve Left", new CurveLeftSwitch());
        SmartDashboard.putData("Curve Right", new CurveRightSwitch());
        SmartDashboard.putData("Test Path", new TestPath());

        driver = new XBoxController(0);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverLeft() {
        return driverLeft;
    }

    public Joystick getDriverRight() {
        return driverRight;
    }

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
        return driver.getThrottle();
    }

    public boolean getQuickTurn() {
        return driver.leftStickButton.get() || driver.startButton.get();
    }
}

