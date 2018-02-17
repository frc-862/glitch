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

import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.RobotMap;
import org.usfirst.frc862.glitch.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc862.util.DataLogger;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Lift extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX fourbar = RobotMap.liftfourbar;
    private final WPI_TalonSRX elevator = RobotMap.liftelevator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private final AnalogInput armPos = new AnalogInput(1);

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public Lift() {

        fourbar.setNeutralMode(NeutralMode.Brake);
        elevator.setNeutralMode(NeutralMode.Brake);

        fourbar.configPeakOutputForward(0.5, Constants.TALON_TIMEOUT);
        fourbar.configPeakOutputReverse(-0.5, Constants.TALON_TIMEOUT);
        elevator.configPeakOutputForward(0.5, Constants.TALON_TIMEOUT);
        elevator.configPeakOutputReverse(-0.5, Constants.TALON_TIMEOUT);

        fourbar.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.Disabled, Constants.TALON_TIMEOUT);
        fourbar.configReverseLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.Disabled, Constants.TALON_TIMEOUT);

//        fourbar.configReverseSoftLimitThreshold(1133, Constants.TALON_TIMEOUT);
//        fourbar.configForwardSoftLimitThreshold(1363, Constants.TALON_TIMEOUT);
//        fourbar.configForwardSoftLimitEnable(true, Constants.TALON_TIMEOUT);
//        fourbar.configReverseSoftLimitEnable(true, Constants.TALON_TIMEOUT);

        fourbar.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, Constants.TALON_TIMEOUT);
        fourbar.configAllowableClosedloopError(0, Constants.FOURBAR_ALLOWABLE_ERROR, Constants.TALON_TIMEOUT);
        fourbar.config_kP(0, Constants.FOURBAR_P, Constants.TALON_TIMEOUT);
        fourbar.config_kI(0, Constants.FOURBAR_I, Constants.TALON_TIMEOUT);
        fourbar.config_kD(0, Constants.FOURBAR_D, Constants.TALON_TIMEOUT);
        fourbar.config_kF(0, Constants.FOURBAR_F, Constants.TALON_TIMEOUT);

        fourbar.configMotionAcceleration(Constants.FOURBAR_ACC, Constants.TALON_TIMEOUT);
        fourbar.configMotionCruiseVelocity(Constants.FOURBAR_VEL, Constants.TALON_TIMEOUT);

        elevator.setSelectedSensorPosition(0, 0, Constants.TALON_TIMEOUT);
//        elevator.configReverseSoftLimitThreshold(-5500, Constants.TALON_TIMEOUT);
//        elevator.configForwardSoftLimitThreshold(1800, Constants.TALON_TIMEOUT);
//        elevator.configForwardSoftLimitEnable(true, Constants.TALON_TIMEOUT);
//        elevator.configReverseSoftLimitEnable(true, Constants.TALON_TIMEOUT);

        elevator.setInverted(true);
        elevator.setSensorPhase(true);
        elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.TALON_TIMEOUT);
        elevator.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.Disabled, Constants.TALON_TIMEOUT);
        elevator.configReverseLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.Disabled, Constants.TALON_TIMEOUT);

        elevator.configAllowableClosedloopError(0, Constants.ELEVATOR_ALLOWABLE_ERROR, Constants.TALON_TIMEOUT);
        elevator.config_kP(0, Constants.ELEVATOR_P, Constants.TALON_TIMEOUT);
        elevator.config_kI(0, Constants.ELEVATOR_I, Constants.TALON_TIMEOUT);
        elevator.config_kD(0, Constants.ELEVATOR_D, Constants.TALON_TIMEOUT);
        elevator.config_kF(0, Constants.ELEVATOR_F, Constants.TALON_TIMEOUT);

        elevator.configMotionAcceleration(Constants.ELEVATOR_ACC, Constants.TALON_TIMEOUT);
        elevator.configMotionCruiseVelocity(Constants.ELEVATOR_VEL, Constants.TALON_TIMEOUT);

        SmartDashboard.putNumber("Fourbar Allowable Error", Constants.FOURBAR_ALLOWABLE_ERROR);
        SmartDashboard.putNumber("Fourbar P", Constants.FOURBAR_P);
        SmartDashboard.putNumber("Fourbar I", Constants.FOURBAR_I);
        SmartDashboard.putNumber("Fourbar D", Constants.FOURBAR_D);
        SmartDashboard.putNumber("Fourbar F", Constants.FOURBAR_F);

        SmartDashboard.putNumber("Fourbar ACC", Constants.FOURBAR_ACC);
        SmartDashboard.putNumber("Fourbar VEL", Constants.FOURBAR_VEL);

        SmartDashboard.putNumber("Elevator Allowable Error", Constants.ELEVATOR_ALLOWABLE_ERROR);
        SmartDashboard.putNumber("Elevator P", Constants.ELEVATOR_P);
        SmartDashboard.putNumber("Elevator I", Constants.ELEVATOR_I);
        SmartDashboard.putNumber("Elevator D", Constants.ELEVATOR_D);
        SmartDashboard.putNumber("Elevator F", Constants.ELEVATOR_F);

        SmartDashboard.putNumber("Elevator ACC", Constants.ELEVATOR_ACC);
        SmartDashboard.putNumber("Elevator VEL", Constants.ELEVATOR_VEL);

        DataLogger.addDataElement("FourbarGoal", () -> fourbar.getClosedLoopTarget(0));
        DataLogger.addDataElement("ElevatorGoal", () -> elevator.getClosedLoopTarget(0));
        DataLogger.addDataElement("FourbarPos", () -> fourbar.getSelectedSensorPosition(0));
        DataLogger.addDataElement("ElevatorPos", () -> elevator.getSelectedSensorPosition(0));
        DataLogger.addDataElement("FourbarError", () -> fourbar.getClosedLoopError(0));
        DataLogger.addDataElement("ElevatorError", () -> elevator.getClosedLoopError(0));
        DataLogger.addDataElement("FourbarPower", () -> fourbar.getMotorOutputPercent());
        DataLogger.addDataElement("ElevatorPower", () -> elevator.getMotorOutputPercent());
    }

    @Override
    public void periodic() {
        int err = (int) SmartDashboard.getNumber("Fourbar Allowable Error", Constants.FOURBAR_ALLOWABLE_ERROR);
        fourbar.configAllowableClosedloopError(0, err, Constants.TALON_TIMEOUT);
        double kP = SmartDashboard.getNumber("Fourbar P", Constants.FOURBAR_P);
        fourbar.config_kP(0, kP, Constants.TALON_TIMEOUT);
        double kI = SmartDashboard.getNumber("Fourbar I", Constants.FOURBAR_I);
        fourbar.config_kI(0, kI, Constants.TALON_TIMEOUT);
        double kD = SmartDashboard.getNumber("Fourbar D", Constants.FOURBAR_D);
        fourbar.config_kD(0, kD, Constants.TALON_TIMEOUT);
        double kF = SmartDashboard.getNumber("Fourbar F", Constants.FOURBAR_F);
        fourbar.config_kF(0, kF, Constants.TALON_TIMEOUT);

        int acc = (int) Math.round(SmartDashboard.getNumber("Fourbar Acc", Constants.FOURBAR_ACC));
        fourbar.configMotionAcceleration(acc, Constants.TALON_TIMEOUT);
        int vel = (int) Math.round(SmartDashboard.getNumber("Fourbar Vel", Constants.FOURBAR_VEL));
        fourbar.configMotionCruiseVelocity(vel, Constants.TALON_TIMEOUT);

        SmartDashboard.putNumber("FourBar Encoder", fourbar.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("FourBar AnalogIn", fourbar.getSensorCollection().getAnalogIn());
        SmartDashboard.putNumber("FourBar AnalogIn Raw", fourbar.getSensorCollection().getAnalogInRaw());
        SmartDashboard.putNumber("Analog 1", armPos.getVoltage());

        err = (int) SmartDashboard.getNumber("Elevator Allowable Error", Constants.ELEVATOR_ALLOWABLE_ERROR);
        elevator.configAllowableClosedloopError(0, err, Constants.TALON_TIMEOUT);
        kP = SmartDashboard.getNumber("Elevator P", Constants.ELEVATOR_P);
        elevator.config_kP(0, kP, Constants.TALON_TIMEOUT);
        kI = SmartDashboard.getNumber("Elevator I", Constants.ELEVATOR_I);
        elevator.config_kI(0, kI, Constants.TALON_TIMEOUT);
        kD = SmartDashboard.getNumber("Elevator D", Constants.ELEVATOR_D);
        elevator.config_kD(0, kD, Constants.TALON_TIMEOUT);
        kF = SmartDashboard.getNumber("Elevator F", Constants.ELEVATOR_F);
        elevator.config_kF(0, kF, Constants.TALON_TIMEOUT);

        acc = (int) Math.round(SmartDashboard.getNumber("Elevator Acc", Constants.ELEVATOR_ACC));
        elevator.configMotionAcceleration(acc, Constants.TALON_TIMEOUT);
        vel = (int) Math.round(SmartDashboard.getNumber("Elevator Vel", Constants.ELEVATOR_VEL));
        elevator.configMotionCruiseVelocity(vel, Constants.TALON_TIMEOUT);

        SmartDashboard.putNumber("Elevator Encoder", elevator.getSelectedSensorPosition(0));

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

//    public int getPositionEnc() {
//        return motor.getSelectedSensorPosition(0);
//    }

    // TODO add state engine and move out of "snug position safely...
    public void moveToScale() {
        fourbar.set(ControlMode.MotionMagic, Constants.FOURBAR_SCALE_POS);
        elevator.set(ControlMode.MotionMagic, Constants.ELEVATOR_SCALE_POS);
    }

    public void moveToSwitch() {
        fourbar.set(ControlMode.MotionMagic, Constants.FOURBAR_SWITCH_POS);
        elevator.set(ControlMode.MotionMagic, Constants.ELEVATOR_SWITCH_POS);
    }

    public void moveToBottom() {
        fourbar.set(ControlMode.MotionMagic, Constants.FOURBAR_BOTTOM_POS);
        elevator.set(ControlMode.MotionMagic, Constants.ELEVATOR_BOTTOM_POS);
    }

    public void moveToCollect() {
        fourbar.set(ControlMode.MotionMagic, Constants.FOURBAR_COLLECT_POS);
        elevator.set(ControlMode.MotionMagic, Constants.ELEVATOR_COLLECT_POS);
    }
}

