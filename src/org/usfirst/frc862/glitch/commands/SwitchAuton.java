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
import org.usfirst.frc862.glitch.paths.SwitchCurveLeftPath;
import org.usfirst.frc862.glitch.paths.SwitchCurveRightPath;
import org.usfirst.frc862.glitch.paths.SwitchStraightPath;
import org.usfirst.frc862.glitch.subsystems.ShineBois;

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
        ShineBois.rainbow();

        boolean leftStart = SmartDashboard.getBoolean("start side", false);
        //int powerCubes= (int)Math.round(SmartDashboard.getNumber("auton powercubes",1));
        //String mode=SmartDashboard.getString("select mode", "switch");
        String fieldConfig = DriverStation.getInstance().getGameSpecificMessage();
        //DriverStation.Alliance alliance=DriverStation.getInstance().getAlliance();

        CommandGroup cmd = new CommandGroup();
        cmd.addParallel(new MoveCollectorToSwitch());

        if(leftStart && fieldConfig.substring(0,1).equalsIgnoreCase("L"))
        {
            cmd.addParallel(new SwitchStraightPath());
        }
        else if(!leftStart && fieldConfig.substring(0,1).equalsIgnoreCase("R"))
        {
            cmd.addParallel(new SwitchStraightPath());
        }
        else if(leftStart)
        {
            //Curve right
            cmd.addParallel(new SwitchCurveRightPath());
        }
        else
        {
            //Curve left
            cmd.addParallel(new SwitchCurveLeftPath());
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
