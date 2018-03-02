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
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.paths.*;
import org.usfirst.frc862.glitch.subsystems.ShineBois;
import org.usfirst.frc862.util.DynamicPathCommand;

/**
 *
 */
public class SmartAuton extends Command {
    private boolean go_for_it;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SmartAuton() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shifter);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        go_for_it = false;
        ShineBois.rainbow();

        Robot.shifter.forceDownShift();

        CommandGroup cmd = new CommandGroup();
        cmd.addSequential(new DownShift());
        cmd.addParallel(new HoldCube());

        if (Robot.startOnLeft()) {
            if (Robot.switchOnLeft()) {
                go_for_it = true;
                cmd.addParallel(new MoveCollectorToSwitch());
                cmd.addSequential(new LeftPointsSwitch());
            } else if (Robot.scaleOnLeft()) {
                DynamicPathCommand path = new LeftScaleNear();

                CommandGroup riseUp = new CommandGroup();
                riseUp.addSequential(new TimedCommand(path.duration() - 3));
                riseUp.addSequential(new MoveCollectorToScale());
                cmd.addParallel(riseUp);

                cmd.addSequential(path);
            } else {
                DynamicPathCommand path = new LeftScaleFar();

                CommandGroup riseUp = new CommandGroup();
                riseUp.addSequential(new TimedCommand(path.duration() - 3));
                riseUp.addSequential(new MoveCollectorToScale());

                cmd.addParallel(riseUp);
                cmd.addSequential(path);
            }
        } else {
            if (!Robot.switchOnLeft()) {
                go_for_it = true;
                cmd.addParallel(new MoveCollectorToSwitch());
                cmd.addSequential(new RightPointsSwitch());
            } else if (!Robot.scaleOnLeft()) {
                go_for_it = true;
                DynamicPathCommand path = new RightScaleNear();
                cmd.addSequential(path);
                CommandGroup raiseUp = new CommandGroup();
//                raiseUp.addSequential(new TimedCommand(path.duration() - 3.5));
                raiseUp.addSequential(new MoveCollectorToScale());
                cmd.addParallel(raiseUp);
            } else {
                DynamicPathCommand path = new RightScaleFar();
                cmd.addSequential(path);
                CommandGroup raiseUp = new CommandGroup();
                raiseUp.addSequential(new TimedCommand(path.duration() - 2.8));
                cmd.addParallel(raiseUp);
            }
        }

        if (go_for_it) {
            cmd.addSequential(new EjectCube(), 1);
            cmd.addSequential(new BackupSlow());
            cmd.addSequential(new MoveCollectorToGround());
        }

        cmd.start();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
