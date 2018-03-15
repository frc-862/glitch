package org.usfirst.frc862.util;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.util.ChezyMath;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.subsystems.DriveTrain;
import org.usfirst.frc862.util.FaultCode.Codes;

public class DynamicPathCommandBase extends Command {

    public DynamicPathCommandBase() {
        super();
    }

    public DynamicPathCommandBase(String name) {
        super(name);
    }

    public Path getPath() { return null; }

    public double duration() {
        Trajectory left = getPath().getLeftWheelTrajectory();
        Trajectory.Segment point = left.getSegment(left.getNumSegments() - 1);
        return point.dt * left.getNumSegments();
    }
    
    @Override
    protected boolean isFinished() {
        return true;
    }

    public boolean isReversed() {
        return false;
    }
}
