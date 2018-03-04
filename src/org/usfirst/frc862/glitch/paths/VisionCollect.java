package org.usfirst.frc862.glitch.commands;
import com.team254.lib.util.DriveSignal;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.vision.CubeNotFoundException;
import org.usfirst.frc862.util.CurvatureDrive;
import org.usfirst.frc862.util.ValueFilter;
import org.usfirst.frc862.util.ExponentialSmoothingFilter;
import org.usfirst.frc862.util.Logger;

/**
 *
 */
public class VisionCollect extends Command {
    public VisionCollect() {
        requires(Robot.driveTrain);
        requires(Robot.gripper);
    }

    @Override
    protected void initialize() {
        Logger.info("Enter vision collect");
        Robot.driveTrain.setVelocityMode();
    }

    private ValueFilter angleFilter = new ExponentialSmoothingFilter(0.2);

    private double angle = 0;
    private double dist = 0;
    private double lastSeen = 0;

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        try {
            double[] cube = Robot.cubeVision.getBestCube();
            angle = cube[0];
            dist = cube[2];
            lastSeen = Timer.getFPGATimestamp();

            angleFilter.filter(angle);
            Logger.info("Have cube: " + angle + " -- " + dist);
        } catch (CubeNotFoundException err) {
            Logger.error("Cube not found: " + err);
        }

        double power = 0;
        double rotation = 0;
        boolean qturn = false;

        double age = Timer.getFPGATimestamp() - lastSeen;
        if (age < 2) {
            if (Robot.shifter.isHighGear()) {
                rotation = angleFilter.get() * Constants.StraightenKpHighGear;
            } else {
                rotation = angleFilter.get() * Constants.StraightenKpLowGear;
            }
            power = 0.4;
            qturn = Math.abs(angleFilter.get()) > 9;

            Logger.info("VisionCollect: " + rotation + ", " + power + ", " + qturn);
        }

        DriveSignal signal = CurvatureDrive.curvatureDrive(power, rotation, qturn);
        Logger.info("VisionCollect drive: " + signal.toString());
        Robot.driveTrain.setVelocity(signal);

        // TODO run the collector
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.gripper.stopIntake();
    	Logger.info("Exit vision collect");
    }
}
