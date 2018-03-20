package org.usfirst.frc862.util;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import edu.wpi.first.wpilibj.command.Command;

public class DynamicPathCommandBase extends Command {

    DynamicPathCommandBase() {
        super();
    }

    DynamicPathCommandBase(String name) {
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
