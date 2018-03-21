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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.paths.*;
import org.usfirst.frc862.glitch.subsystems.ShineBois;
import org.usfirst.frc862.util.DynamicPathCommandBase;
import org.usfirst.frc862.util.Logger;

/**
 *
 */
public class VisionTestAuton extends Command {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public VisionTestAuton() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        ShineBois.rainbow();

        CommandGroup cmd;

        Robot.shifter.forceDownShift();

        cmd = new CommandGroup();

        // TODO verify rotation angle (for correct side)
        cmd.addParallel(new RotateAwayFromScale(Robot.scaleOnLeft() ? 160 : 200));
        cmd.addSequential(new MoveCollectorToGround());

        if (Robot.attemptMultiCubeAuton()) {
            cmd.addSequential(new VisionCollect());

            CommandGroup switchDeploy = new CommandGroup();
            switchDeploy.addSequential(new MoveCollectorToSwitch());
            // TODO move forward a bit?

            CommandGroup scaleDeploy = new CommandGroup();
            // TODO use turn to absolute angle (and adjust for left/right scale)
            scaleDeploy.addSequential(new TurnToAbsolutePosition(Robot.scaleOnLeft() ? 265 : 15));
            scaleDeploy.addParallel(new MoveCollectorToScale());
            // TODO write this path
            scaleDeploy.addSequential(new VisionPathSelectCommand());
            scaleDeploy.addParallel(new CollectCube());
            scaleDeploy.addSequential(Robot.scaleOnLeft() ? new SecondCubeLeft() : new SecondCubeRight());

            cmd.addSequential(new ConditionalCommand(switchDeploy, scaleDeploy) {
                @Override
                protected boolean condition() {
                    return Robot.switchOnLeft() == Robot.scaleOnLeft() && Robot.autonTimeRemaining() < Constants.AutonScaleTime;
                }
            });
            cmd.addSequential(new EjectCube());
            scaleDeploy.addSequential(new TurnToAbsolutePosition(Robot.scaleOnLeft() ? 245 : 30));
            cmd.addSequential(new MoveCollectorToCollect());
            cmd.addSequential(new VisionCollect(), 10);
            cmd.addSequential(new TurnToAbsolutePosition(0));
            if (Robot.scaleOnLeft()) {
                cmd.addSequential(new LeftSecondCube());
            } else {
                cmd.addSequential(new RightSecondCube());
            }

            cmd.addSequential(new EjectCube(), 1);
            cmd.addSequential(new TurnToAbsolutePosition(Robot.scaleOnLeft() ? 265 : 15));
            cmd.addSequential(new MoveCollectorToScale());
        }

        cmd.start();
    }

    private CommandGroup buildScale() {
        boolean leftStart = Robot.startOnLeft();
        boolean leftScale = Robot.scaleOnLeft();

        CommandGroup cmd = new CommandGroup();

        cmd.addParallel(new HoldCube());

        DynamicPathCommandBase path;

        if (leftStart && leftScale) {
            Logger.info("Using Left Scale Near");
            path = new LeftScaleNearLG();
        } else if (leftStart && !leftScale) {
            Logger.info("Using Left Scale Far");
            path = new RightScaleNearLG();
        } else if (!leftScale) {
            Logger.info("Using Right Scale Near");
            // If we made it this far, we have to be on the right side
            path = new RightScaleNearLG();
        } else {
            // Must be right far
            Logger.info("Using Right Scale Far");
            path = new RightScaleFarLG();
        }

        CommandGroup riseUp = new CommandGroup();
        if (path.duration() > 4) {
            riseUp.addSequential(new TimedCommand(path.duration() - 4));
        }
        riseUp.addSequential(new MoveCollectorToScale());
        cmd.addParallel(riseUp);

        cmd.addSequential(path);

        cmd.addSequential(new EjectCube(), 1);

        return cmd;
    }


    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

}
