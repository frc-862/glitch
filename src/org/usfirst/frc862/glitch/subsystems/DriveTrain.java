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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team254.lib.util.DriveSignal;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc862.glitch.commands.Arcade;
import org.usfirst.frc862.glitch.commands.TankDrive;
import org.usfirst.frc862.util.DataLogger;
import org.usfirst.frc862.util.LightningMath;


/**
 *
 */
public class DriveTrain extends Subsystem {

    private double slowUntil = 0;

    enum Mode { voltage, velocity, magic, test }

    Mode mode;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX left1 = RobotMap.driveTrainLeft1;
    private final WPI_TalonSRX right1 = RobotMap.driveTrainRight1;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    BaseMotorController left2 = RobotMap.leftSlave1;
    BaseMotorController left3 = RobotMap.leftSlave2;
    BaseMotorController right2 = RobotMap.rightSlave1;
    BaseMotorController right3 = RobotMap.rightSlave2;

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
    }

    @Override
    public void periodic() {
        // in ticks / 100ms
        double left_vel = left1.getSelectedSensorVelocity(0);
        double right_vel = right1.getSelectedSensorVelocity(0);

        SmartDashboard.putNumber("Left Error", left1.getClosedLoopError(0));
        SmartDashboard.putNumber("left", left_vel);
        SmartDashboard.putNumber("left error", left1.getClosedLoopError(0));
        SmartDashboard.putNumber("right", right_vel);
        SmartDashboard.putNumber("right error", right1.getClosedLoopError(0));

        SmartDashboard.putNumber("left pos", left1.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("right pos", right1.getSelectedSensorPosition(0));
    }

    protected void eachMotor(Consumer<BaseMotorController> func) {
        func.accept(left1);
        func.accept(left2);
        func.accept(left3);
        func.accept(right1);
        func.accept(right2);
        func.accept(right3);
    }

    protected void setFollowMode(ControlMode mode) {
        eachMaster((m) -> m.set(mode, 0));
        eachSlave((m,s) -> s.follow(m));
    }

    protected void eachMaster(Consumer<TalonSRX> func) {
        func.accept(left1);
        func.accept(right1);
    }

//    protected void eachSlave(Consumer<BaseMotorController> func) {
//        func.accept(left2);
//        func.accept(left3);
//        func.accept(right2);
//        func.accept(right3);
//    }

    protected void eachSlave(BiConsumer<TalonSRX, BaseMotorController> func) {
        func.accept(left1, left2);
        func.accept(left1, left3);
        func.accept(right1, right2);
        func.accept(right1, right3);
    }

    protected void unsetFollowMode() {
        eachMotor((motor) -> motor.set(ControlMode.Current, 0));
    }

    public DriveTrain() {
        super();

        DataLogger.addDataElement("Left error",() -> left1.getClosedLoopError(0));
        DataLogger.addDataElement("Right error",() -> right1.getClosedLoopError(0));
        DataLogger.addDataElement("Left velocity",() -> left1.getSelectedSensorVelocity(0));
        DataLogger.addDataElement("Right velocity",() -> right1.getSelectedSensorVelocity(0));
        DataLogger.addDataElement("Left command",() -> left1.getClosedLoopTarget(0));
        DataLogger.addDataElement("Right command",() -> right1.getClosedLoopTarget(0));
        DataLogger.addDataElement("Left percent",() -> left1.getMotorOutputPercent());
        DataLogger.addDataElement("Right percent",() -> right1.getMotorOutputPercent());
        DataLogger.addDataElement("Ave Speed",() -> this.getAverageSpeed());
        DataLogger.addDataElement("Req Speed",() -> this.getRequestedVelocity());
        DataLogger.addDataElement("Cmd Rotation", () ->
                LightningMath.talon2ips(Robot.oi.getRotation() * Constants.PHYSICAL_MAX_LOW_SPEED_TICKS));

        eachMaster((m) -> m.selectProfileSlot(0, 0));

        if (Robot.isOBot()) {
            left1.setInverted(true);
            left1.setSensorPhase(true);

            right2.setInverted(true);
            right3.setInverted(true);
        } else if (Robot.isEcho()){
            left1.setInverted(false);
            left1.setSensorPhase(false);
            left2.setInverted(true);
            left3.setInverted(false);

            right1.setInverted(true);
            right1.setSensorPhase(true);
            right2.setInverted(false);
            right3.setInverted(true);
        } else {
            left1.setInverted(false);
            left1.setSensorPhase(false);
            left2.setInverted(true);
            left3.setInverted(false);

            right1.setInverted(true);
            right1.setSensorPhase(false);
            right2.setInverted(false);
            right3.setInverted(true);
        }

        eachMaster((m) -> {
            m.configOpenloopRamp(Constants.openLoopRamp, Constants.TALON_TIMEOUT);
            m.configClosedloopRamp(Constants.closedLoopRamp, Constants.TALON_TIMEOUT);
        });

        /* set the peak and nominal outputs, 12V means full */
        eachMotor((baseMotorController) -> {
            baseMotorController.configNominalOutputForward(0, Constants.TALON_TIMEOUT);
            baseMotorController.configNominalOutputReverse(0, Constants.TALON_TIMEOUT);
            baseMotorController.configPeakOutputForward(1, Constants.TALON_TIMEOUT);
            baseMotorController.configPeakOutputReverse(-1, Constants.TALON_TIMEOUT);
        });

        setVelocityMode();
    }

    public Mode getMode() {
        return mode;
    }

    public void setVelocityMode() {
        setVelocityMode(Mode.velocity);
    }

    public void setVelocityMode(final Mode vmode) {
        setFollowMode(ControlMode.Velocity);
        configureVelocityPID();

        // select slot based on current gear..
        setSlotBasedOnGear();

        mode = vmode;
    }

    public void setVoltageMode() {
        setFollowMode(ControlMode.PercentOutput);
        mode = Mode.voltage;
    }

    public void setTestMode() {
        unsetFollowMode();
        mode = Mode.test;
    }

    public void stop() {
        left1.stopMotor();
        right1.stopMotor();
    }

    public void upShiftBegin() {
        // reduce motor power...
        slowForSeconds(Constants.UPSHIFT_SLOW_TIME_SECONDS);
    }

    public void upShiftEnd() {
        left1.selectProfileSlot(Constants.HIGHGEAR_IDX, 0);
        right1.selectProfileSlot(Constants.HIGHGEAR_IDX, 0);
//        SmartDashboard.putString("DT PID", "upshifted");
//        eachMaster((m) -> {
//            m.config_kP(Constants.LOWGEAR_IDX, Constants.HIGHGEAR_DRIVE_P,Constants.TALON_TIMEOUT);
//            m.config_kI(Constants.LOWGEAR_IDX, Constants.HIGHGEAR_DRIVE_I, Constants.TALON_TIMEOUT);
//            m.config_kD(Constants.LOWGEAR_IDX, Constants.HIGHGEAR_DRIVE_D,Constants.TALON_TIMEOUT);
//            m.config_kF(Constants.LOWGEAR_IDX, Constants.HIGHGEAR_DRIVE_F,Constants.TALON_TIMEOUT);
//            m.configAllowableClosedloopError(Constants.LOWGEAR_IDX, Constants.HIGHGEAR_ALLOWED_DRIVE_ERROR, Constants.TALON_TIMEOUT);
//        });
    }

    public void downShiftBegin() {
        // reduce motor power...
        slowForSeconds(Constants.DOWNSHIFT_SLOW_TIME_SECONDS);
    }

    public void downShiftEnd() {
        left1.selectProfileSlot(Constants.LOWGEAR_IDX, 0);
        right1.selectProfileSlot(Constants.LOWGEAR_IDX, 0);
//        SmartDashboard.putString("DT PID", "downshifted");
//        eachMaster((m) -> {
//            m.config_kP(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_P,Constants.TALON_TIMEOUT);
//            m.config_kI(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_I, Constants.TALON_TIMEOUT);
//            m.config_kD(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_D,Constants.TALON_TIMEOUT);
//            m.config_kF(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_F,Constants.TALON_TIMEOUT);
//            m.configAllowableClosedloopError(Constants.LOWGEAR_IDX, Constants.LOWGEAR_ALLOWED_DRIVE_ERROR, Constants.TALON_TIMEOUT);
//        });
    }

    public void setPower(double left, double right) {
        left1.set(ControlMode.PercentOutput, left);
        right1.set(ControlMode.PercentOutput, right);
        SmartDashboard.putNumber("drive left", left);
        SmartDashboard.putNumber("drive right", right);
    }

//    public void set(double left, double right) {
//        left1.set(mode == Mode.velocity ? ControlMode.Velocity : ControlMode.PercentOutput, left);
//        right1.set(mode == Mode.velocity ? ControlMode.Velocity : ControlMode.PercentOutput, right);
//        SmartDashboard.putNumber("drive left", left);
//        SmartDashboard.putNumber("drive right", right);
//    }

    public double getLeftDistanceInches() {
        return LightningMath.ticks2inches(left1.getSelectedSensorPosition(0));
    }

    public double getRightDistanceInches() {
        return LightningMath.ticks2inches(right1.getSelectedSensorPosition(0));
    }

    public double getLeftVelocityInchesPerSec() {
        return LightningMath.talon2ips(left1.getSelectedSensorVelocity(0));
    }

    public double getRightVelocityInchesPerSec() {
        return LightningMath.talon2ips(right1.getSelectedSensorVelocity(0));
    }

    public void setPower(DriveSignal drive) {
        setPower(drive.getLeft(), drive.getRight());
    }

    public void setVelocity(DriveSignal drive) {
        setVelocity(drive.getLeft() * Constants.PHYSICAL_MAX_HIGH_SPEED_TICKS,
                drive.getRight() * Constants.PHYSICAL_MAX_HIGH_SPEED_TICKS);
    }

    public void setVelocityPercent(double left, double right) {
        setVelocity(left * Constants.PHYSICAL_MAX_HIGH_SPEED_TICKS,
                right * Constants.PHYSICAL_MAX_HIGH_SPEED_TICKS);
    }

    public void setVelocity(double left, double right) {
        SmartDashboard.putNumber("slowuntil", slowUntil);
        SmartDashboard.putNumber("timer", Timer.getFPGATimestamp());
        if (Timer.getFPGATimestamp() < slowUntil) {
            SmartDashboard.putString("slowing", "true");
            left1.set(ControlMode.Velocity, getAverageSpeed() * Constants.slowDownRate);
            right1.set(ControlMode.Velocity, getAverageSpeed() * Constants.slowDownRate);
        } else {
            SmartDashboard.putString("slowing", "false");
            SmartDashboard.putNumber("left target vel", left1.getClosedLoopTarget(0));
            SmartDashboard.putNumber("right target vel", right1.getClosedLoopTarget(0));
            left1.set(ControlMode.Velocity, left);
            right1.set(ControlMode.Velocity, right);
        }
    }

    public void setVelocityIPS(double left, double right) {
        setVelocity(LightningMath.ips2talon(left), LightningMath.ips2talon(right));
    }

    public void setPIDF(double kP, double kI, double kD, double kF) {
        eachMaster((m) -> {
            m.config_kP(Constants.LOWGEAR_IDX, kP, Constants.TALON_TIMEOUT);
            m.config_kI(Constants.LOWGEAR_IDX, kI, Constants.TALON_TIMEOUT);
            m.config_kD(Constants.LOWGEAR_IDX, kD, Constants.TALON_TIMEOUT);
            m.config_kF(Constants.LOWGEAR_IDX, kF, Constants.TALON_TIMEOUT);
            
            m.config_kP(Constants.HIGHGEAR_IDX, kP, Constants.TALON_TIMEOUT);
            m.config_kI(Constants.HIGHGEAR_IDX, kI, Constants.TALON_TIMEOUT);
            m.config_kD(Constants.HIGHGEAR_IDX, kD, Constants.TALON_TIMEOUT);
            m.config_kF(Constants.HIGHGEAR_IDX, kF, Constants.TALON_TIMEOUT);
        });
	}

    public void slowForSeconds(double seconds) {
        slowUntil = Timer.getFPGATimestamp() + seconds;
        left1.set(getAverageSpeed() * Constants.slowDownRate);
        right1.set(getAverageSpeed() * Constants.slowDownRate);
    }

    public double getAbsVelocity() {
        return Math.abs((getRightVelocityInchesPerSec()+getLeftVelocityInchesPerSec())/2);
    }

    public double getAverageSpeed() {
        int pidIdx = (Robot.shifter.isHighGear() ? Constants.HIGHGEAR_IDX : Constants.LOWGEAR_IDX);
        double leftSpeed = left1.getSelectedSensorVelocity(pidIdx);
        double rightSpeed = right1.getSelectedSensorVelocity(pidIdx);
        return (leftSpeed  + rightSpeed) / 2;
    }

    public double getRequestedVelocity() {
        return Math.abs(LightningMath.talon2ips((
                left1.getClosedLoopTarget(0) + right1.getClosedLoopTarget(0)) / 2));
    }

    public void resetDistance() {
        left1.setSelectedSensorPosition(0, 0, Constants.TALON_TIMEOUT);
        right1.setSelectedSensorPosition(0, 0, Constants.TALON_TIMEOUT);
    }

    public void setMotionMagic(double left, double right) {
        setFollowMode(ControlMode.MotionMagic);
        configureVelocityPID();

        // select slot based on current gear..
        setSlotBasedOnGear();

        left1.set(ControlMode.MotionMagic, left);
        right1.set(ControlMode.MotionMagic, right);
        mode = Mode.magic;
    }

    private void setSlotBasedOnGear() {
        if (Robot.shifter == null || !Robot.shifter.isHighGear()) {
            left1.selectProfileSlot(Constants.LOWGEAR_IDX, 0);
            right1.selectProfileSlot(Constants.LOWGEAR_IDX, 0);
        } else {
            left1.selectProfileSlot(Constants.HIGHGEAR_IDX, 0);
            right1.selectProfileSlot(Constants.HIGHGEAR_IDX, 0);
        }
    }

    private void configureVelocityPID() {
        eachMaster((m) -> {
            m.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.TALON_TIMEOUT);

            m.config_kP(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_P,Constants.TALON_TIMEOUT);
            m.config_kI(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_I, Constants.TALON_TIMEOUT);
            m.config_kD(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_D,Constants.TALON_TIMEOUT);
            m.config_kF(Constants.LOWGEAR_IDX, Constants.LOWGEAR_DRIVE_F,Constants.TALON_TIMEOUT);
            m.configAllowableClosedloopError(Constants.LOWGEAR_IDX, Constants.LOWGEAR_ALLOWED_DRIVE_ERROR, Constants.TALON_TIMEOUT);
            m.setSelectedSensorPosition(0,0, Constants.TALON_TIMEOUT);

            m.config_kP(Constants.HIGHGEAR_IDX, Constants.HIGHGEAR_DRIVE_P,Constants.TALON_TIMEOUT);
            m.config_kI(Constants.HIGHGEAR_IDX, Constants.HIGHGEAR_DRIVE_I, Constants.TALON_TIMEOUT);
            m.config_kD(Constants.HIGHGEAR_IDX, Constants.HIGHGEAR_DRIVE_D,Constants.TALON_TIMEOUT);
            m.config_kF(Constants.HIGHGEAR_IDX, Constants.HIGHGEAR_DRIVE_F,Constants.TALON_TIMEOUT);
            m.setSelectedSensorPosition(0,0, Constants.TALON_TIMEOUT);
            m.configAllowableClosedloopError(Constants.HIGHGEAR_IDX, Constants.HIGHGEAR_ALLOWED_DRIVE_ERROR, Constants.TALON_TIMEOUT);

            m.configOpenloopRamp(Constants.openLoopRamp, Constants.TALON_TIMEOUT);
            m.configClosedloopRamp(Constants.closedLoopRamp, Constants.TALON_TIMEOUT);
            m.configMotionAcceleration((int)Constants.PHYSICAL_MAX_LOW_SPEED_TICKS, Constants.TALON_TIMEOUT);
            m.configMotionCruiseVelocity((int) Constants.PHYSICAL_MAX_LOW_SPEED_TICKS * 30, Constants.TALON_TIMEOUT);
        });
    }

    public void updateMotionMagic(double left, double right) {
        left1.set(ControlMode.MotionMagic, left);
        right1.set(ControlMode.MotionMagic, right);
    }

}

