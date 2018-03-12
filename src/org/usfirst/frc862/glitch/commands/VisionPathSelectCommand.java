package org.usfirst.frc862.glitch.commands;

import com.team254.lib.trajectory.Path;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.paths.*;
import org.usfirst.frc862.glitch.vision.CubeNotFoundException;
import org.usfirst.frc862.util.DynamicPathCommand;

import java.util.TreeMap;

public class VisionPathSelectCommand extends DynamicPathCommand {

    enum State { waiting_for_vision, start_path, following_path };
    private State state;
    private double startTime;
    private static TreeMap<Double, DynamicPathCommand> leftPaths = new TreeMap<>();
    private static TreeMap<Double, DynamicPathCommand> rightPaths = new TreeMap<>();
    private DynamicPathCommand path;

    static {
        leftPaths.put(-20.0, new LeftSwitchNeg20Degree());
        leftPaths.put(-10.0, new LeftSwitchNeg10Degree());
        leftPaths.put(0.0, new LeftSwitch0Degree());
        leftPaths.put(10.0, new LeftSwitch10Degree());
        leftPaths.put(20.0, new LeftSwitch20Degree());

        rightPaths.put(-20.0, new RightSwitchNeg20Degree());
        rightPaths.put(-10.0, new RightSwitchNeg10Degree());
        rightPaths.put(0.0, new RightSwitch0Degree());
        rightPaths.put(10.0, new RightSwitch10Degree());
        rightPaths.put(20.0, new RightSwitch20Degree());
    }

    public VisionPathSelectCommand() {
        super();
    }

    @Override
    public Path getPath() {
        return path.getPath();
    }

    @Override
    protected void initialize() {
       state = State.waiting_for_vision;
       startTime = Timer.getFPGATimestamp();
    }

    protected DynamicPathCommand getClosestPath(double angle) {
        TreeMap<Double, DynamicPathCommand> paths = Robot.scaleOnLeft() ? leftPaths : rightPaths;
        DynamicPathCommand path = paths.get(angle);
        if (path == null) {
            Double topBound = paths.ceilingKey(angle);
            Double bottomBound = paths.floorKey(angle);

            if (topBound == null && bottomBound == null) {
                return paths.get(0.0);
            } else if (topBound == null) {
                return paths.get(bottomBound);
            } else if (bottomBound == null) {
                return paths.get(topBound);


            // TODO we could theoretically interpolate two different paths!
            // (probably off season), for now just pick the closer one
            } else if (Math.abs(topBound - angle) < Math.abs(bottomBound - angle)) {
                return paths.get(topBound);
            } else {
                return paths.get(bottomBound);
            }
        }
        return path;
    }

    @Override
    protected  void execute() {
        switch (state) {
            case waiting_for_vision:
                double[] cube = null;

                if (Robot.cubeVision.getLastVisionRead() > startTime) {
                    try {
                        cube = Robot.cubeVision.getBestCube();
                    } catch (CubeNotFoundException e) {
                        // do nothing
                    }
                }

                if (cube != null) {
                    path = getClosestPath(cube[0]);
                    state = State.start_path;
                } else if (Timer.getFPGATimestamp() - startTime > 1.5) {
                    // The the expected path
                    path = getClosestPath(0.0);
                    state = State.start_path;
                }
                break;

            case start_path:
                super.initialize();
                state = State.following_path;
                break;

            case following_path:
                super.execute();
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished() || Robot.gripper.hasCube();
    }
}
