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
import com.team254.lib.util.DriveSignal;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.subsystems.ShineBois;
import org.usfirst.frc862.util.CurvatureDrive;
import org.usfirst.frc862.util.JoystickFilter;
import org.usfirst.frc862.util.LightningMath;
import org.usfirst.frc862.util.MovingAverageFilter;

/**
 *
 */
public class Arcade extends Command {
    private final JoystickFilter thrustFilter = new JoystickFilter(Constants.dead_band, Constants.STICKTION_SPEED_IPS, 1, JoystickFilter.Mode.SQUARED);
    private final JoystickFilter rotFilter = new JoystickFilter(Constants.dead_band, 0, 1, JoystickFilter.Mode.CUBED);
    private double heading = 0;
    private double lastTime = 0;
    private double lastHeading;
    private final MovingAverageFilter velocityAverage = new MovingAverageFilter(10);

    private enum State { turning, straight, turn_hysterisis }
    private State state = State.turning;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Arcade() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        thrustFilter.setRampDelta(Constants.VEL_COMMAND_RAMP);
        rotFilter.setRampDelta(Constants.ROT_COMMAND_RAMP);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        ShineBois.chase();

        Robot.driveTrain.setVelocityMode();
        lastHeading = Robot.core.getGyroAngle();
        state = State.turning;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//        double left= Robot.oi.getThrust();
//        double right = Robot.oi.getThrust();
//        left-=Robot.oi.getRotation();
//        right+=Robot.oi.getRotation();
//        left= LightningMath.limit(left,-1,1);
//        right = LightningMath.limit(right,-1,1);
//        Robot.driveTrain.setPower(left,right);

        double start = Timer.getFPGATimestamp();
        double rot = rotFilter.filter(Robot.oi.getRotation());
        double pwr = thrustFilter.filter(Robot.oi.getThrust());
        boolean quickTurn = Robot.oi.getQuickTurn();

        double dt = lastTime - start;
        lastTime = start;

        double newHeading = Robot.core.getGyroAngle();
        velocityAverage.filter(Math.abs(lastHeading - newHeading) / dt);
        lastHeading = newHeading;

        SmartDashboard.putNumber("angular velocity", velocityAverage.get());

        switch (state) {
            case turning:
                if (Math.abs(rot) < Constants.NotStraight) {
                    state = State.turn_hysterisis;
                }
                heading = Robot.core.getGyroAngle();

                if (Math.abs(pwr) < Constants.dead_band) {
                    quickTurn = true;
                }
                break;

            case straight:
                if (Math.abs(rot) > Constants.NotStraight) {
                    state = State.turning;
                } else {
                    double err = LightningMath.boundThetaNeg180to180(heading - Robot.core.getGyroAngle());
                    SmartDashboard.putNumber("Theta Error", err);
                    if (Math.abs(err) > Constants.StraightMarginOfError) {
                        if (Robot.shifter.isHighGear()) {
                            rot += err * Constants.StraightenKpHighGear;
                        } else {
                            rot += err * Constants.StraightenKpLowGear;
                        }
                        SmartDashboard.putNumber("Theta Correct", rot);
                        quickTurn = true;
                    } else {
                        SmartDashboard.putNumber("Theta Correct", rot);
                    }
                }
                break;

            case turn_hysterisis:
                if (Math.abs(velocityAverage.get()) < Constants.NotTurning) {
                    state = State.straight;
                } else if (Math.abs(rot) > Constants.NotStraight) {
                    state = State.turning;
                }
                heading = Robot.core.getGyroAngle();

                if (Math.abs(pwr) < Constants.dead_band) {
                    quickTurn = true;
                }
                break;
        }

        SmartDashboard.putNumber("rot vel ave", velocityAverage.get());
        SmartDashboard.putBoolean("quickturn", quickTurn);
        SmartDashboard.putString("arcade mode", state.toString());
//        DriveSignal power = drive.cheesyDrive(pwr, rot, quickTurn, Robot.shifter.isHighGear());
        DriveSignal power = CurvatureDrive.curvatureDrive(pwr, rot, quickTurn);
        Robot.driveTrain.setVelocity(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
