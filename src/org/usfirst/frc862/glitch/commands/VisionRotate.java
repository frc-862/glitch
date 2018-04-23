package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.vision.CubeNotFoundException;
import org.usfirst.frc862.glitch.vision.PowerCube;


public class VisionRotate extends Command {
    private int side = 0;

    enum State { initialize, execute };
    State state;
    private double start;
    private boolean done;
    private SmartRotate rotate;

    public VisionRotate(int side) {
        this.side = side;
    }

    public VisionRotate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.side = 0;
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        start = Timer.getFPGATimestamp();
        done = false;
        state = State.initialize;
        rotate = null;
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        switch (state) {
            case initialize:
                try {
                    PowerCube cube;
                    if (side < 0) {
                        cube = Robot.cubeVision.getLeftCube();
                    } else if (side > 0) {
                        cube = Robot.cubeVision.getRightCube();
                    } else {
                        cube = Robot.cubeVision.getBestCube();
                    }
                    if (cube.getTimestamp() > start) {
                        rotate = new SmartRotate(cube.getAngle());
                        rotate.initialize();
                        state = State.execute;
                    }
                } catch (CubeNotFoundException e) { }
                break;

            case execute:
                rotate.execute();
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return rotate != null && rotate.isFinished();
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        if (rotate != null) {
            rotate.end();
        }
    }
}
