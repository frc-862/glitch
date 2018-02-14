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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.paths.*;
import org.usfirst.frc862.glitch.subsystems.ShineBois;
import org.usfirst.frc862.util.TimedTriggers;

/**
 *
 */
public class ScaleAuton extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ScaleAuton() {

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

        DriverStation.Alliance alliance=DriverStation.getInstance().getAlliance();
        CommandGroup cmd;

        if (alliance == DriverStation.Alliance.Blue) {
            cmd = blueAlliance();
        } else {
            cmd = redAlliance();
        }

        cmd.addSequential(new EjectCube());
        cmd.start();
    }

    private CommandGroup blueAlliance() {
        boolean leftStart = SmartDashboard.getBoolean("start side", false);
        String fieldConfig = DriverStation.getInstance().getGameSpecificMessage();

        CommandGroup cmd = new CommandGroup();
        TimedTriggers triggers = new TimedTriggers();

        if (leftStart && fieldConfig.substring(1,1).equalsIgnoreCase("L")) {
            cmd.addParallel(new LeftScaleNear());
            triggers.addAction(new MoveCollectorToScale(), 4.0);
        } else if (leftStart && fieldConfig.substring(1,1).equalsIgnoreCase("R")) {
            cmd.addParallel(new LeftScaleFar());
            triggers.addAction(new MoveCollectorToScale(), 6.0);
        } else if (fieldConfig.substring(1,1).equalsIgnoreCase("R")) {
            // If we made it this far, we have to be on the right side
            cmd.addParallel(new RightScaleNear());
            triggers.addAction(new MoveCollectorToScale(), 4.0);
        } else {
            // Must be right far
            cmd.addParallel(new RightScaleFar());
            triggers.addAction(new MoveCollectorToScale(), 6.0);
        }

        cmd.addParallel(triggers);
        return cmd;
    }

    private CommandGroup redAlliance() {
        boolean leftStart = SmartDashboard.getBoolean("start side", false);
        String fieldConfig = DriverStation.getInstance().getGameSpecificMessage();

        CommandGroup cmd = new CommandGroup();
        TimedTriggers triggers = new TimedTriggers();

        if (leftStart && fieldConfig.substring(1,1).equalsIgnoreCase("L")) {
            cmd.addParallel(new LeftScaleNear());
            triggers.addAction(new MoveCollectorToScale(), 4.0);
        } else if (leftStart && fieldConfig.substring(1,1).equalsIgnoreCase("R")) {
            cmd.addParallel(new LeftScaleFar());
            triggers.addAction(new MoveCollectorToScale(), 6.0);
        } else if (fieldConfig.substring(1,1).equalsIgnoreCase("R")) {
            // If we made it this far, we have to be on the right side
            cmd.addParallel(new RightScaleNear());
            triggers.addAction(new MoveCollectorToScale(), 4.0);
        } else {
            // Must be right far
            cmd.addParallel(new RightScaleFar());
            triggers.addAction(new MoveCollectorToScale(), 6.0);
        }

        cmd.addParallel(triggers);
        return cmd;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

}
