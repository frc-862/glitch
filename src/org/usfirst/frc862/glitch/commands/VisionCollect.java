package org.usfirst.frc862.glitch.commands;
import com.team254.lib.util.DriveSignal;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.subsystems.Gripper;
import org.usfirst.frc862.glitch.subsystems.ShineBois;
import org.usfirst.frc862.glitch.vision.CubeNotFoundException;
import org.usfirst.frc862.glitch.vision.PowerCube;
import org.usfirst.frc862.util.CurvatureDrive;
import org.usfirst.frc862.util.ValueFilter;
import org.usfirst.frc862.util.ExponentialSmoothingFilter;
import org.usfirst.frc862.util.Logger;

/**
 *
 */
public class VisionCollect extends Command {
	
	private double toggleTime = 0;
	private boolean collecting = false;
	
    public VisionCollect() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        Logger.info("Enter vision collect");
        ShineBois.purple();
        Robot.driveTrain.setVelocityMode();
        prevArea = 0;
    }

    private ValueFilter angleFilter = new ExponentialSmoothingFilter(0.4);

    private double angle = 0;
    private double dist = 0;
    private double area = 0;
    private double prevArea = 0;
    private double lastSeen = 0;

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	
        try {
        	SmartDashboard.putString("vision collect step", "try start");
            PowerCube cube = Robot.cubeVision.getBestCube();
            angle = cube.getAngle();
            area = cube.getArea();
            dist = cube.getLongitudal();


        	SmartDashboard.putString("vision collect step", "got cube data");

            boolean runThisCycle = false;

            double areadiff = .1;
            if(prevArea != 0) areadiff = Math.abs(prevArea - area) / prevArea;

            if (areadiff < 0.3) {
                runThisCycle = true;
            }
            else {
                //do not update
            }

            SmartDashboard.putBoolean("runCycle", runThisCycle);
            SmartDashboard.putNumber("area", area);
            SmartDashboard.putNumber("prevArea", prevArea);

            if (runThisCycle) {
                prevArea = area;
                lastSeen = Timer.getFPGATimestamp();

                angleFilter.filter(angle);
                Logger.info("Have cube: " + angle + " -- " + dist);
            }




        } catch (CubeNotFoundException err) {
            Logger.error("Cube not found: " + err);
            angleFilter.filter(0);
        }

        double power = 0;
        double rotation = 0;
        boolean qturn = false;

        double age = Timer.getFPGATimestamp() - lastSeen;
        if (age < 2) {
        	if(toggleTime == 0) {
        		toggleTime = Timer.getFPGATimestamp();
        		collecting = true;
        		Robot.gripper.collectCube();
        	}
        	double time = Timer.getFPGATimestamp();
        	if(time - toggleTime >= .3) {
        		if(collecting) Robot.gripper.stopIntake();
        		else Robot.gripper.collectCube();
        		collecting = !collecting;
        		toggleTime = time;
        	}
            if (Robot.shifter.isHighGear()) {
               // rotation = angleFilter.get() * Constants.StraightenKpHighGear;
            	rotation = angle * Constants.StraightenKpHighGear;
            } else {
                //rotation = angleFilter.get() * Constants.StraightenKpLowGear;
            	rotation = angle * Constants.StraightenKpLowGear;
            }
            power = 0.2;
            SmartDashboard.putNumber("angleFilter.get()", angleFilter.get());

            Logger.info("VisionCollect: " + rotation + ", " + power + ", " + qturn);
        }
        else {
        	Robot.gripper.stopIntake();
        	collecting = false;
        	toggleTime = 0;
        }
        SmartDashboard.putNumber("rotation", rotation);
        DriveSignal signal = CurvatureDrive.curvatureDrive(power, rotation / 2, qturn);
        Logger.info("VisionCollect drive: " + signal.toString());
        Robot.driveTrain.setVelocity(signal);

        // TODO run the collector
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
    	ShineBois.chase();
    	Logger.info("Exit vision collect");
    }
}
