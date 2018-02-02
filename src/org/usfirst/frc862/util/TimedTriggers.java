package org.usfirst.frc862.util;

import edu.wpi.first.wpilibj.command.Command;

import java.util.SortedSet;
import java.util.TreeSet;

public class TimedTriggers extends Command {
    public static class TimedCommandEntry implements Comparable<TimedCommandEntry> {
        private final Command command;
        private final double at;

        public TimedCommandEntry(Command cmd, double at) {
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

        public double getAt() { return at; }

        @Override
        public int compareTo(TimedCommandEntry o) {
            return (int) Math.round(this.at - o.at);
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
