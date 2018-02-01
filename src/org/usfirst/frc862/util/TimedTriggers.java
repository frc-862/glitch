package org.usfirst.frc862.util;

import edu.wpi.first.wpilibj.command.Command;

import java.util.*;

public class TimedTriggers extends Command {
    class TimedCommandEntry implements Comparator<TimedCommandEntry> {
        private final Command command;
        private final double at;

        TimedCommandEntry(Command cmd, double at) {
            this.command = cmd;
            this.at = at;
        }

        public boolean check(double elapsed) {
            if (elapsed >= at) {
                command.start();
                return true;
            }

            return false;
        }

        @Override
        public int compare(TimedCommandEntry o1, TimedCommandEntry o2) {
            return (int) Math.round(o1.at - o2.at);
        }
    }

    SortedSet<TimedCommandEntry> actions = new TreeSet<>();

    public void addAction(Command cmd, double at) {
        actions.add(new TimedCommandEntry(cmd, at));
    }

    @Override
    protected void execute() {
        if (actions.first().check(this.timeSinceInitialized())) {
            actions.remove(actions.first());
        }
    }

    @Override
    protected boolean isFinished() {
        return actions.isEmpty();
    }
}
