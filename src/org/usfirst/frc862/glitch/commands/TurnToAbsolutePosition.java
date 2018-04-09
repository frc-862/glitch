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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.subsystems.SmartRotate;
import org.usfirst.frc862.util.LightningMath;
import org.usfirst.frc862.util.Logger;

/**
 *
 */
public class TurnToAbsolutePosition extends SmartRotate {
    private double m_position;

    public TurnToAbsolutePosition(double position) {
        super(position);
        m_position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Logger.debug("Absolute start " + Robot.core.getGyroAngle());
        Logger.debug("Desired angle " + m_position);
        this.degrees = -LightningMath.boundThetaNeg180to180(Robot.core.getGyroAngle() + m_position);
        Logger.debug("degree rotate " + degrees);
        super.initialize();
    }
}
