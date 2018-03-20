package org.usfirst.frc862.glitch.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc862.util.Logger;


class LightningGroup extends CommandGroup {
    public LightningGroup() {
        super();
        Logger.info("Lightning Group - Construct");
    }

    public LightningGroup(String name) {
        super(name);
    }

    @Override
    protected boolean isFinished() {
        boolean result = super.isFinished();
        Logger.info(getName() + ".isFinished(): " + result);
        return result;
    }

    @Override
    protected void initialize() {
        Logger.info("Before " + getName() + ".initialize()");
        super.initialize();
        Logger.info("After " + getName() + ".initialize()");
    }

    @Override
    protected void execute() {
        Logger.info("Before " + getName() + ".execute()");
        super.execute();
        Logger.info("After " + getName() + ".execute()");
    }

    @Override
    protected void end() {
        Logger.info("Before " + getName() + ".end()");
        super.end();
        Logger.info("After " + getName() + ".end()");
    }

    @Override
    protected void interrupted() {
        Logger.info("Before " + getName() + ".interrupted()");
        super.interrupted();
        Logger.info("After " + getName() + ".interrupted()");
    }
}
