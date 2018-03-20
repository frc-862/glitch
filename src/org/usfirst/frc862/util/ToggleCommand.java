package org.usfirst.frc862.util;

import edu.wpi.first.wpilibj.command.Command;

class ToggleCommand extends Command {
    private final Command cmdA;
    private final Command cmdB;
    private boolean a = true;

    public ToggleCommand(Command a, Command b) {
        cmdA = a;
        cmdB = b;
    }

    @Override
    protected void initialize() {
        if (a) {
            cmdA.start();
        } else {
            cmdB.start();
        }
        a = !a;
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}


