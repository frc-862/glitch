package org.usfirst.frc862.glitch.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.RobotMap;
import org.usfirst.frc862.util.StatefulCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SystemTest extends StatefulCommand {
    private boolean complete = false;
    private boolean ispressed = false;
    private double encoderStart;
    private double startingValue;
    
    enum States {
        TEST_LEFT_MOTOR1, TEST_LEFT_MOTOR2, TEST_LEFT_MOTOR3,
        TEST_RIGHT_MOTOR1, TEST_RIGHT_MOTOR2, TEST_RIGHT_MOTOR3,

        TEST_LEFT_ENCODER, TEST_RIGHT_ENCODER,
        TEST_UPSHIFT, TEST_DOWNSHIFT,
        TEST_NAVX,

        TEST_POWER_CUBE_DETECTOR,
        TEST_LEFT_COLLECTOR,
        TEST_RIGHT_COLLECTOR,

        TEST_ELEVATOR_MOTOR,
        TEST_FOUR_BAR_MOTOR,
        TEST_EVELVATOR_BOTTOM_LIMIT, TEST_ELEVATOR_TOP_LIMIT,
        TEST_FOUR_BAR_LOW_POS, TEST_FOUR_BAR_HIGH_POS,

        TEST_START_POSITION,
        TEST_VISION_SYSTEM,
        TEST_LED_MODES,

        TEST_COMPRESSOR,

        TESTS_ARE_DONE;
        
        States next() {
            // No bounds checking required here, because the last instance overrides
            return values()[ordinal() + 1];
        }
    }
    
    public SystemTest() {
        super(States.TEST_LEFT_MOTOR1);
        requires(Robot.driveTrain);
        requires(Robot.shifter);
        requires(Robot.collector);
        requires(Robot.lift);
        requires(Robot.cubeVision);
        requires(Robot.shineBois);
        requires(Robot.core);

        this.setDefaultAction(() -> next());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
        Robot.driveTrain.setTestMode();
        Robot.core.enterTestMode();

        complete = false;

        status("initalized");
        setState(States.TEST_LEFT_MOTOR1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return complete;
    }

    @Override
    protected void execute() {
        super.execute();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.setVelocityMode();
        Robot.driveTrain.stop();
        Robot.shifter.downshift();
        Robot.core.exitTestMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    private boolean buttonReleased() {
        boolean button = Robot.oi.getCopilotController().getRawButton(1);
        if (ispressed && !button) {
            ispressed = false;
            return true;
        } else if (button) {
            ispressed = true;
        }
        
        return false;
    }

    private boolean next() {
        if (buttonReleased()) {
            setState(((States) getState()).next());
            return true;
        }
        
        return false;
    }
    
    private void success() {
        SmartDashboard.putBoolean(getCallingState().toString(), true);
    }
    private void fail() {
        SmartDashboard.putBoolean(getCallingState().toString(), false);
    }
    private double nextWithValue(double fValue, double tValue) {
        return next() ? tValue : fValue;
    }
    
    public void testLeftMotor1Enter() {
        encoderStart = Robot.driveTrain.getLeftDistanceInches();
    }
    
    public void testLeftMotor1() {
        RobotMap.driveTrainLeft1.set(ControlMode.PercentOutput, nextWithValue(Constants.MotorTestPower, 0));
    }
    
    public void testLeftMotor1Exit() {
        if (Math.abs(Robot.driveTrain.getLeftDistanceInches() - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testLeftMotor2Enter() {
        encoderStart = Robot.driveTrain.getLeftDistanceInches();
    }
    
    public void testLeftMotor2() {
        RobotMap.leftSlave1.set(ControlMode.PercentOutput, nextWithValue(Constants.MotorTestPower, 0.0));
    }
    
    public void testLeftMotor2Exit() {
        if (Math.abs(Robot.driveTrain.getLeftDistanceInches() - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testLeftMotor3Enter() {
        encoderStart = Robot.driveTrain.getLeftDistanceInches();
    }
    
    public void testLeftMotor3Exit() {
        if (Math.abs(Robot.driveTrain.getLeftDistanceInches() - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testLeftMotor3() {
        RobotMap.leftSlave2.set(ControlMode.PercentOutput, nextWithValue(Constants.MotorTestPower, 0.0));
    }
    
    public void testRightMotor1Enter() {
        encoderStart = Robot.driveTrain.getRightDistanceInches();
    }
    
    public void testRightMotor1Exit() {
        if (Math.abs(Robot.driveTrain.getRightDistanceInches() - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testRightMotor1() {
        RobotMap.driveTrainRight1.set(nextWithValue(Constants.MotorTestPower, 0.0));
    }
    
    public void testRightMotor2Enter() {
        encoderStart = Robot.driveTrain.getRightDistanceInches();
    }
    
    public void testRightMotor2Exit() {
        if (Math.abs(Robot.driveTrain.getRightDistanceInches() - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testRightMotor2() {
        RobotMap.rightSlave1.set(ControlMode.PercentOutput, nextWithValue(Constants.MotorTestPower, 0.0));
    }
    
    public void testRightMotor3Enter() {
        encoderStart = Robot.driveTrain.getRightDistanceInches();
    }
    
    public void testRightMotor3Exit() {
        if (Math.abs(Robot.driveTrain.getRightDistanceInches() - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testRightMotor3() {
        RobotMap.rightSlave2.set(ControlMode.PercentOutput, nextWithValue(Constants.MotorTestPower, 0.0));
    }

    public void testCompressor() {
        RobotMap.coreCompressor.setClosedLoopControl(false);
        RobotMap.coreCompressor.enabled();
        if (next()) {
            RobotMap.coreCompressor.setClosedLoopControl(true);            
        }
    }
    
    public void testCompressorExit() {
        success();
    }
    
    public void testUpshift() {
        Robot.shifter.upshift();
        next();
    }
    
    public void testUpshiftExit() {
        success();
    }
    
    public void testDownshift() {
        Robot.shifter.downshift();
        next();
    }
    
    public void testDownshiftExit() {
        success();
    }
    
    public void testLeftEncoderEnter() {
        encoderStart = Robot.driveTrain.getLeftDistanceInches();
        Robot.driveTrain.setPower(Constants.MotorTestPower, 0);
    }

    public void testLeftEncoderExit() {
        Robot.driveTrain.stop();        
        double encoderStop = Robot.driveTrain.getLeftDistanceInches();
        
        if ((encoderStop - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testRightEncoderEnter() {
        encoderStart = Robot.driveTrain.getRightDistanceInches();
        Robot.driveTrain.setPower(0, Constants.MotorTestPower);
    }
    
    public void testRightEncoderExit() {
        Robot.driveTrain.stop();        
        double encoderStop = Robot.driveTrain.getRightDistanceInches();
        
        if ((encoderStop - encoderStart) > Constants.MotorTestDistance) {
            success();
        } else {
            fail();
        }
    }
    
    public void testNavxEnter() {
        status("Rotate the robot at least 5 degrees");
        startingValue = Robot.core.getGyroAngle();
    }
    
    public void testNavxExit() {
        if (Math.abs(Robot.core.getGyroAngle() - startingValue) > 5) {
            success();
        } else {
            fail();
        }
    }

    public void testPowerCubeDetectorEnter() {
        if (Robot.collector.hasCube()) {
            fail();
            next();
        } else {
            status("Place a cube into the collector");
        }
    }

    public void testPowerCubeDetectorExit() {
        if (Robot.collector.hasCube()) {
            success();
        } else {
            fail();
        }
    }

    public void testLeftCollector() {

    }

    public void testRightCollector() {

    }

    public void testElevatorMotor() {

    }

    public void testFourBarMotor() {

    }

    public void testElevatorBottomLimit() {

    }

    public void testElevatorTopLimit() {

    }

    public void testFourBarLowPos() {

    }

    public void testFourBarHighPos() {

    }

    public void testStartPosition() {

    }

    public void testVisionSystem() {

    }

    public void testLedModes() {

    }

    public void testsAreDone() {
        status("Tests complete");
        complete = true;
    }

    private void status(String msg) {
        SmartDashboard.putString("System Test Instruction", msg);    
    }
    
    public static void update() {
        SmartDashboard.putString("System Test Instruction", "Run the system test!");
        for (States s : States.values()) {
            SmartDashboard.putBoolean(s.toString(), false);
        }
    }
}
