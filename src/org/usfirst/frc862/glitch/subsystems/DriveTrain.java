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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.RobotMap;
import org.usfirst.frc862.glitch.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc862.util.LightningMath;

import java.util.function.Consumer;

import static org.usfirst.frc862.glitch.subsystems.DriveTrain.Mode.test;
import static org.usfirst.frc862.glitch.subsystems.DriveTrain.Mode.velocity;
import static org.usfirst.frc862.glitch.subsystems.DriveTrain.Mode.voltage;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends Subsystem {

    enum Mode { voltage, velocity, test };
    Mode mode;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX left1 = RobotMap.driveTrainLeft1;
    private final WPI_TalonSRX left2 = RobotMap.driveTrainLeft2;
    private final WPI_TalonSRX left3 = RobotMap.driveTrainLeft3;
    private final WPI_TalonSRX right1 = RobotMap.driveTrainRight1;
    private final WPI_TalonSRX right2 = RobotMap.driveTrainRight2;
    private final WPI_TalonSRX right3 = RobotMap.driveTrainRight3;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Teleop());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        setDefaultCommand(new Teleop());
    }

    @Override
    public void periodic() {
        // in ticks / 100ms
        double left_vel = left1.getSelectedSensorVelocity(0);
        // in ticks / sec
        left_vel = left_vel * 10;
        // in ft / sec
        left_vel = left_vel / 360 * Constants.WheelCircumference;

        double right_vel = right1.getSelectedSensorVelocity(0);
//        right_vel = right_vel * 10 / 360 * Constants.WheelCircumference;
        right_vel = LightningMath.in2ft(2) * right_vel * 10;

        SmartDashboard.putNumber("left", left_vel);
        SmartDashboard.putNumber("right", right_vel);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    protected void eachMotor(Consumer<BaseMotorController> func) {
        func.accept(left1);
        func.accept(left2);
        func.accept(left3);
        func.accept(right1);
        func.accept(right2);
        func.accept(right3);
    }

    protected void setFollowMode(ControlMode mode) {
        left1.set(mode, 0);
        left2.follow(left1);
        left3.follow(left1);
        right1.set(mode, 0);
        right2.follow(right1);
        right3.follow(right1);
    }

    protected void unsetFollowMode() {
        eachMotor((motor) -> {
            motor.set(ControlMode.Current, 0);
        });
    }

    public DriveTrain() {
        super();
        left1.setInverted(true);
        right2.setInverted(true);
        right3.setInverted(true);
        setVoltageMode();
    }

    public Mode getMode() {
        return mode;
    }

    public void setVelocityMode() {
        setFollowMode(ControlMode.Velocity);
        left1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.TALON_TIMEOUT);
        left1.config_kP(0, Constants.DRIVE_P,Constants.TALON_TIMEOUT);
        left1.config_kI(0, Constants.DRIVE_I, Constants.TALON_TIMEOUT);
        left1.config_kD(0, Constants.DRIVE_D,Constants.TALON_TIMEOUT);
        left1.config_kF(0, Constants.DRIVE_F,Constants.TALON_TIMEOUT);
        right1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.TALON_TIMEOUT);
        right1.config_kP(0, Constants.DRIVE_P,Constants.TALON_TIMEOUT);
        right1.config_kI(0, Constants.DRIVE_I, Constants.TALON_TIMEOUT);
        right1.config_kD(0, Constants.DRIVE_D,Constants.TALON_TIMEOUT);
        right1.config_kF(0, Constants.DRIVE_F,Constants.TALON_TIMEOUT);

        mode = velocity;
    }

    public void setVoltageMode() {
        setFollowMode(ControlMode.PercentOutput);
        mode = voltage;
    }

    public void setTestMode() {
        unsetFollowMode();
        mode = test;
    }

    public void stop() {
        left1.set(0);
        right1.set(0);
    }

    public void setPower(double left, double right) {
        left1.set(left);
        right1.set(right);
        SmartDashboard.putNumber("drive left", left);
        SmartDashboard.putNumber("drive right", right);
    }
}

