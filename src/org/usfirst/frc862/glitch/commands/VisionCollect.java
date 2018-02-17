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
    private boolean close;

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

            double pwr = Math.min(area * Constants.VisionSpeedP, Constants.VisionMinSpeed);
            double rot;
            if (Robot.shifter.isHighGear()) {
                rot = angle * Constants.StraightenKpHighGear;
            } else {
                rot = angle * Constants.StraightenKpLowGear;
            }
            boolean qturn = pwr <= Constants.VisionMinSpeed;

            if (area > Constants.VisionCloseThreshold) {
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
