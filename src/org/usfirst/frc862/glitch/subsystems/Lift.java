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
import com.team254.lib.util.MovingAverage;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc862.glitch.commands.CoPilotAuto;
import org.usfirst.frc862.util.DataLogger;


/**
 *
 */
public class Lift extends Subsystem {
    enum State { PowerUp, InitialFramePerimeter, InitialGroundCollect, Collect, Drive, Switch, Scale, ManualControl };
    State previousState;
    State state;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX fourbar = RobotMap.liftfourbar;
    private final WPI_TalonSRX elevator = RobotMap.liftelevator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private final AnalogInput armPos = new AnalogInput(1);
    private double fourbarPosition, elevatorPosition;
    private double elevatorError, elevatorErrorRate;
    private MovingAverage errorFilter = new MovingAverage(3);

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new CoPilotAuto());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public Lift() {

        fourbar.setNeutralMode(NeutralMode.Brake);
        elevator.setNeutralMode(NeutralMode.Brake);

        fourbar.configPeakOutputForward(0.862, Constants.TALON_TIMEOUT);
        fourbar.configPeakOutputReverse(-0.254, Constants.TALON_TIMEOUT);
        fourbar.configClosedloopRamp(1.0, Constants.TALON_TIMEOUT);
        fourbar.configPeakCurrentDuration(0, Constants.TALON_TIMEOUT);

        elevator.configPeakOutputForward(1.0, Constants.TALON_TIMEOUT);
        elevator.configPeakOutputReverse(-1.0, Constants.TALON_TIMEOUT);
        elevator.configClosedloopRamp(0.5, Constants.TALON_TIMEOUT);
        elevator.configPeakCurrentDuration(0, Constants.TALON_TIMEOUT);

        fourbar.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.Disabled, Constants.TALON_TIMEOUT);
        fourbar.configReverseLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.Disabled, Constants.TALON_TIMEOUT);

        fourbar.configReverseSoftLimitEnable(false, Constants.TALON_TIMEOUT);
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

        previousState = State.PowerUp;
        if (fourbar.getSelectedSensorPosition(0) <= Constants.FOURBAR_COLLECT_POS * 0.8) {
            elevator.setSelectedSensorPosition(0, 0, Constants.TALON_TIMEOUT);
            state = State.InitialFramePerimeter;
        } else {
            elevator.setSelectedSensorPosition(Constants.ELEVATOR_COLLECT_POS, 0, Constants.TALON_TIMEOUT);
            state = State.InitialGroundCollect;
        }

//        elevator.configReverseSoftLimitThreshold(-5500, Constants.TALON_TIMEOUT);
//        elevator.configForwardSoftLimitThreshold(1800, Constants.TALON_TIMEOUT);
//        elevator.configForwardSoftLimitEnable(true, Constants.TALON_TIMEOUT);
//        elevator.configReverseSoftLimitEnable(true, Constants.TALON_TIMEOUT);
        elevator.configReverseSoftLimitEnable(false, Constants.TALON_TIMEOUT);

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

//        SmartDashboard.putNumber("Fourbar Allowable Error", Constants.FOURBAR_ALLOWABLE_ERROR);
//        SmartDashboard.putNumber("Fourbar P", Constants.FOURBAR_P);
//        SmartDashboard.putNumber("Fourbar I", Constants.FOURBAR_I);
//        SmartDashboard.putNumber("Fourbar D", Constants.FOURBAR_D);
//        SmartDashboard.putNumber("Fourbar F", Constants.FOURBAR_F);
//
//        SmartDashboard.putNumber("Fourbar ACC", Constants.FOURBAR_ACC);
//        SmartDashboard.putNumber("Fourbar VEL", Constants.FOURBAR_VEL);
//
//        SmartDashboard.putNumber("Elevator Allowable Error", Constants.ELEVATOR_ALLOWABLE_ERROR);
//        SmartDashboard.putNumber("Elevator P", Constants.ELEVATOR_P);
//        SmartDashboard.putNumber("Elevator I", Constants.ELEVATOR_I);
//        SmartDashboard.putNumber("Elevator D", Constants.ELEVATOR_D);
//        SmartDashboard.putNumber("Elevator F", Constants.ELEVATOR_F);
//
//        SmartDashboard.putNumber("Elevator ACC", Constants.ELEVATOR_ACC);
//        SmartDashboard.putNumber("Elevator VEL", Constants.ELEVATOR_VEL);

        DataLogger.addDataElement("FourbarGoal", () -> fourbarPosition);
        DataLogger.addDataElement("ElevatorGoal", () -> elevator.getClosedLoopTarget(0));
        DataLogger.addDataElement("FourbarPos", () -> fourbar.getSelectedSensorPosition(0));
        DataLogger.addDataElement("ElevatorPos", () -> elevator.getSelectedSensorPosition(0));
        DataLogger.addDataElement("FourbarError", () -> fourbarPosition - fourbar.getSelectedSensorPosition(0));
        DataLogger.addDataElement("ElevatorError", () -> elevator.getClosedLoopError(0));
        DataLogger.addDataElement("FourbarPower", () -> fourbar.getMotorOutputPercent());
        DataLogger.addDataElement("ElevatorPower", () -> elevator.getMotorOutputPercent());
        DataLogger.addDataElement("FourbarVelocity", () -> {
            double error = fourbarPosition - fourbar.getSelectedSensorPosition(0);
            return error * Constants.FOURBAR_VELOCITY_P + Constants.FOURBAR_VELOCITY_F;
        });
        DataLogger.addDataElement("ElevatorVelocity", () -> {
            double error = elevatorPosition - elevator.getSelectedSensorPosition(0);
            return error * Constants.ELEVATOR_VELOCITY_P + Constants.ELEVATOR_VELOCITY_F;
        });
        DataLogger.addDataElement("ElevatorErrorRate", () -> {
            double previousElevatorError = elevatorError;
            elevatorError = elevatorPosition - elevator.getSelectedSensorPosition(0);
            return elevatorError - previousElevatorError;
        });
        DataLogger.addDataElement("ElevatorErrorRateFiltered", () -> {
            double previousElevatorError = elevatorError;
            elevatorError = elevatorPosition - elevator.getSelectedSensorPosition(0);
            errorFilter.addNumber(elevatorError - previousElevatorError);
            return errorFilter.getAverage();
        });

        fourbarPosition = fourbar.getSelectedSensorPosition(0);
        elevatorPosition = elevator.getSelectedSensorPosition(0);

        elevatorError = 0;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("FourBar Encoder", fourbar.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Elevator Encoder", elevator.getSelectedSensorPosition(0));

        SensorCollection elevatorInfo = elevator.getSensorCollection();
        if (elevatorInfo.isFwdLimitSwitchClosed()) {
            elevator.setSelectedSensorPosition(Constants.ELEVATOR_SCALE_POS + Constants.ELEVATOR_EPSILON, 0, Constants.TALON_TIMEOUT);
        } else if (elevatorInfo.isRevLimitSwitchClosed()) {
            elevator.setSelectedSensorPosition(Constants.ELEVATOR_COLLECT_POS - Constants.ELEVATOR_EPSILON, 0, Constants.TALON_TIMEOUT);
        }

        boolean controlFourBar = false;
        boolean controlElevator = false;

        switch (state) {
            case Collect:
                // either moving to, or at ground collect position
                controlElevator = fourbar.getSelectedSensorPosition(0) >= Constants.FOURBAR_COLLECT_POS - Constants.FOURBAR_EPSILON;
                controlFourBar = true;
                break;

            case Drive:
                controlElevator = true;
                controlFourBar = elevator.getSelectedSensorPosition(0) >= Constants.ELEVATOR_COLLECT_POS - Constants.ELEVATOR_EPSILON;
                break;

            case Switch:
            case Scale:
                controlElevator = true;
                controlFourBar = true;
                break;

            // do nothing states
            case InitialFramePerimeter:
            case InitialGroundCollect:
            case ManualControl:
                controlElevator = false;
                controlFourBar = false;
                break;
        }

        if (controlFourBar) {
            double fourbarError = fourbarPosition - fourbar.getSelectedSensorPosition(0);
            double fourbarVelocity = fourbarError * Constants.FOURBAR_VELOCITY_P + Constants.FOURBAR_VELOCITY_F;
            fourbar.set(ControlMode.Velocity, fourbarVelocity);
        }

        if (controlElevator) {
            elevatorError = elevatorPosition - elevator.getSelectedSensorPosition(0);
            double elevatorVelocity = elevatorError * Constants.ELEVATOR_VELOCITY_P + Constants.ELEVATOR_VELOCITY_F;
            elevator.set(ControlMode.Velocity, elevatorVelocity);
        }
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

//    public int getPositionEnc() {
//        return motor.getSelectedSensorPosition(0);
//    }

    public void setFourbarPosition(double pos) {
        fourbarPosition = pos;
    }

    public void setElevatorPosition(double pos) {
        elevatorError = elevatorPosition - pos;
        elevatorPosition = pos;
    }

    // TODO add state engine and move out of "snug position safely...
    public void moveToScale() {
        state = State.Scale;
        setFourbarPosition(Constants.FOURBAR_SCALE_POS);
        setElevatorPosition(Constants.ELEVATOR_SCALE_POS);
    }

    public void moveToSwitch() {
        state = State.Switch;
        setFourbarPosition(Constants.FOURBAR_SWITCH_POS);
        setElevatorPosition(Constants.ELEVATOR_SWITCH_POS);
    }

    public void moveToBottom() {
        state = State.Drive;
        setFourbarPosition(Constants.FOURBAR_BOTTOM_POS);
        setElevatorPosition(Constants.ELEVATOR_BOTTOM_POS);
    }

    public void moveToCollect() {
        state = State.Collect;
        setFourbarPosition(Constants.FOURBAR_COLLECT_POS);
        setElevatorPosition(Constants.ELEVATOR_COLLECT_POS);
    }

    public void setElevatorPower(double power){
        elevator.set(ControlMode.PercentOutput, power);
    }

    public void setFourbarPower(double power){
        fourbar.set(ControlMode.PercentOutput, power);
    }

    public void setManualPosition() {
        state = State.ManualControl;
    }
}

