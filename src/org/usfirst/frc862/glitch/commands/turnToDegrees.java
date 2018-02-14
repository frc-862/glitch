// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc862.glitch.commands;
import com.team254.lib.util.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.RobotMap;
import org.usfirst.frc862.util.LightningMath;
import org.usfirst.frc862.util.MovingAverageFilter;

/**
 *
 */
public class turnToDegrees extends Command {

    private final CheesyDriveHelper drive;
    private double degrees = 0;
    private double heading = 0;
    private double lastTime = 0;
    private double lastHeading;
    private double targetAngle = 0;
    private MovingAverageFilter velocityAverage = new MovingAverageFilter(10);
    private boolean done = false;

    private enum State { turning, straight, turn_hysterisis }

    State state = State.turning;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public turnToDegrees() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        drive = new CheesyDriveHelper();
    }

    public turnToDegrees(double d) {
        lastHeading = LightningMath.boundThetaNeg180to180(Robot.core.getGyroAngle() - heading);
        degrees = d;
        drive = new CheesyDriveHelper();
        targetAngle = d + lastHeading;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.driveTrain.setVelocityMode();
        lastHeading = Robot.core.getGyroAngle();
        targetAngle = degrees + lastHeading;
        state = State.turning;
        done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

//        double start = Timer.getFPGATimestamp();
//
//        double rot = Robot.oi.getRotation();
//        rot = rot * rot * rot;
//        double pwr = Robot.oi.getThrust();
//        boolean quickTurn = Robot.oi.getQuickTurn();
//
//        double dt = lastTime - start;
//        lastTime = start;
//
//        double newHeading = Robot.core.getGyroAngle();
//        velocityAverage.filter((lastHeading - newHeading) / dt);
//        lastHeading = newHeading;
//
//        SmartDashboard.putNumber("angular velocity", velocityAverage.get());
//
//        switch (state) {
//            case turning:
//                if (Math.abs(rot) < Constants.NotStraight) {
//                    state = State.turn_hysterisis;
//                }
//
//                if (Math.abs(pwr) < Constants.dead_band) {
//                    quickTurn = true;
//                }
//                break;
//
//            case straight:
//                if (Math.abs(rot) > Constants.NotStraight) {
//                    state = State.turning;
//                } else {
//                    double err = LightningMath.boundThetaNeg180to180(Robot.core.getGyroAngle() - heading);
//                    if (Math.abs(err) > Constants.StraightMarginOfError) {
//                        SmartDashboard.putNumber("Theta Error", err);
//
//                        if (Robot.shifter.isHighGear()) {
//                            rot = err * Constants.StraightenKpHighGear;
//                        } else {
//                            rot = err * Constants.StraightenKpLowGear;
//                        }
//                        SmartDashboard.putNumber("Theta Correct", rot);
//                        quickTurn = true;
//                    }
//                }
//                break;
//
//            case turn_hysterisis:
//                if (Math.abs(velocityAverage.get()) < Constants.NotTurning) {
//                    state = State.straight;
//                    heading = Robot.core.getGyroAngle();
//                }
//                break;
//        }
//
//        SmartDashboard.putBoolean("quickturn", quickTurn);
//        SmartDashboard.putString("arcade mode", state.toString());
//        DriveSignal power = drive.cheesyDrive(pwr, rot, quickTurn, Robot.shifter.isHighGear());
//        Robot.driveTrain.setVelocity(power);

        double newHeading = LightningMath.boundThetaNeg180to180(Robot.core.getGyroAngle() - heading);

        if (!LightningMath.isInRange(newHeading, targetAngle, Constants.ANGLE_TOLERANCE_DEGREES)) {
            if (newHeading < targetAngle) {
                Robot.driveTrain.setPower(0.5, -1 * 0.5);
            } else {
                Robot.driveTrain.setPower(-1 * 0.5, 0.5);
            }
        } else
            done = true;

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }


}
