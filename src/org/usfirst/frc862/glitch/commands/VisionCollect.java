package org.usfirst.frc862.glitch.commands;
import com.team254.lib.util.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.vision.CubeNotFoundException;
import org.usfirst.frc862.util.CurvatureDrive;
import org.usfirst.frc862.util.MovingAverageFilter;

/**
 *
 */
public class VisionCollect extends Command {
    private boolean close, qturn = true;
    private double pwr = 0, rot = 0;

    public VisionCollect() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        close = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        try {
            double[] cube = Robot.cubeVision.getBestCube();
            double angle = cube[0];
            double area = cube[1];
            double dist = cube[2];

            pwr = Math.min(dist * Constants.VisionSpeedP, Constants.VisionMinSpeed);
            if (Robot.shifter.isHighGear()) {
                rot = (rot * .8 + angle * .2) * Constants.StraightenKpHighGear;
            } else {
                rot = (rot * .8 + angle * .2) * Constants.StraightenKpLowGear;
            }
            qturn = pwr <= Constants.VisionMinSpeed;

            if (dist > Constants.VisionCloseThreshold) {
                close = true;
            }

            if (close) {
                Robot.gripper.collectCube();
            }

            DriveSignal power = CurvatureDrive.curvatureDrive(pwr, rot, qturn);
            Robot.driveTrain.setVelocity(power);
        } catch (CubeNotFoundException e) {
            // TODO either do something smart - rotate?
            // or quit

        	//Decrease angle on a decay curve.
        	//TODO Maybe do something different when we know tracking works.
        	DriveSignal power = CurvatureDrive.curvatureDrive(pwr, rot, qturn);
        	rot *= .8;
            Robot.driveTrain.setVelocity(power);
        	
        }

        if (Robot.gripper.hasCube()) {
            Robot.shineBois.cubeCollected();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.gripper.hasCube();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.gripper.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
