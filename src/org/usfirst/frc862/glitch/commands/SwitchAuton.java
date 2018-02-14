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
import org.usfirst.frc862.glitch.paths.CurveLeftSwitch;
import org.usfirst.frc862.glitch.paths.CurveRightSwitch;
import org.usfirst.frc862.glitch.paths.StraightSwitch;

/**
 *
 */
public class SwitchAuton extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SwitchAuton() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        boolean leftStart = SmartDashboard.getBoolean("start side", false);
        //int powerCubes= (int)Math.round(SmartDashboard.getNumber("auton powercubes",1));
        //String mode=SmartDashboard.getString("select mode", "switch");
        String fieldConfig = DriverStation.getInstance().getGameSpecificMessage();
        while (fieldConfig == null || fieldConfig.length() < 2) {
            fieldConfig = DriverStation.getInstance().getGameSpecificMessage();
        }
        //DriverStation.Alliance alliance=DriverStation.getInstance().getAlliance();

        CommandGroup cmd = new CommandGroup();
        cmd.addParallel(new MoveCollectorToSwitch());

        if(leftStart && fieldConfig.substring(0,1).equalsIgnoreCase("L"))
        {
            cmd.addParallel(new StraightSwitch());
        }
        else if(!leftStart && fieldConfig.substring(0,1).equalsIgnoreCase("R"))
        {
            cmd.addParallel(new StraightSwitch());
        }
        else if(leftStart)
        {
            //Curve right
            cmd.addParallel(new CurveRightSwitch());
        }
        else
        {
            //Curve left
            cmd.addParallel(new CurveLeftSwitch());
        }
        cmd.addSequential(new EjectCube());
        cmd.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }
}
