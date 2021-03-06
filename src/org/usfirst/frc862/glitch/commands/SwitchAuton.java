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
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.paths.Backup;
import org.usfirst.frc862.glitch.paths.CurveLeftSwitch;
import org.usfirst.frc862.glitch.paths.CurveRightSwitch;
import org.usfirst.frc862.glitch.paths.StraightSwitch;
import org.usfirst.frc862.glitch.subsystems.ShineBois;
import org.usfirst.frc862.util.Logger;

/**
 *
 */
public class SwitchAuton extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SwitchAuton() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        super("SwitchAuton(Builder)");
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        ShineBois.rainbow();

        // TODO consider changing this, might be a problem for tight/slow turns
        Robot.shifter.forceDownShift();

        boolean leftStart = Robot.startOnLeft();
        Logger.info("Left Start:" + leftStart);
        Logger.info("Left Switch: " + Robot.switchOnLeft());

        CommandGroup cmd = new LightningGroup("Dynamically Built Switch Auton");

        // snug/safe
        cmd.addSequential(new MoveCollectorToGround(), 0.5);

        if (Robot.getAutonDelay() > 0) {
            cmd.addSequential(new TimedCommand(Robot.getAutonDelay()));
        }

        cmd.addParallel(new HoldCube());
        cmd.addParallel(new MoveCollectorToSwitch());

        if(leftStart && Robot.switchOnLeft())
        {
            Logger.info("Trying to go straight");
            cmd.addSequential(new StraightSwitch());
        }
        else if(!leftStart && !Robot.switchOnLeft())
        {
            cmd.addSequential(new StraightSwitch());
        }
        else if (leftStart)
        {
            //Curve right
            cmd.addSequential(new CurveRightSwitch());
        }
        else
        {
            //Curve left
            cmd.addSequential(new CurveLeftSwitch());
        }

        cmd.addSequential(new EjectCube(), 1);
        cmd.addSequential(new Backup());
        cmd.addSequential(new MoveCollectorToCollect());
        cmd.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}
