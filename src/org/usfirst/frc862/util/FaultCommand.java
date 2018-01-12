package org.usfirst.frc862.util;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by phurley on 12/7/16.
 */
public class FaultCommand extends Command {
    FaultCode.Codes code;

    public FaultCommand(FaultCode.Codes code) {
        this.code = code;
    }

    @Override
    public void initialize() {
        super.start();
        FaultCode.write(code);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    protected void execute() {

    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
