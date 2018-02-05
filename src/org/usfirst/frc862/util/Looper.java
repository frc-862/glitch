package org.usfirst.frc862.util;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This code runs all of the robot's loops. Loop objects are stored in a List
 * object. They are started when the robot powers up and stopped after the
 * match.
 */
public class Looper {
    public double period;

    private boolean looperRunning;

    private final Notifier notifier;
    private final List<Loop> loops;
    private final Object taskRunningLock = new Object();
    private double timestamp = 0;
    private double dt = 0;

    public Looper(double period) {
        this.period = period;
        CrashTrackingRunnable runnable = new CrashTrackingRunnable() {
            @Override
            public void runCrashTracked() {
                synchronized (taskRunningLock) {
                    if (looperRunning) {
                        double start = Timer.getFPGATimestamp();
                        dt = start - timestamp;
                        timestamp = start;
                        for (Loop loop : loops) {
                            loop.onLoop();
                        }
                        double duration = Timer.getFPGATimestamp() - start;
                        timestamp = start;

                        if (duration > period) {
                            FaultCode.write(FaultCode.Codes.SLOW_LOOPER,
                                    "expected <" + period + " had " + dt);
                        }
                    }
                }
            }
        };
        notifier = new Notifier(runnable);
        looperRunning = false;
        loops = new ArrayList<>();
    }

    public synchronized void register(Loop loop) {
        synchronized (taskRunningLock) {
            loops.add(loop);
        }
    }

    public synchronized void start() {
        if (!looperRunning) {
            Logger.info("Starting loops");
            synchronized (taskRunningLock) {
                timestamp = Timer.getFPGATimestamp();
                for (Loop loop : loops) {
                    loop.onStart();
                }
                
                // reset the timestamp, to minimize faults,
                // especially when our onStarts are slow
                timestamp = Timer.getFPGATimestamp();
                looperRunning = true;
            }
            notifier.startPeriodic(period);
        }
    }

    public synchronized void stop() {
        if (looperRunning) {
            Logger.info("Stopping loops");
            notifier.stop();
            synchronized (taskRunningLock) {
                looperRunning = false;
                for (Loop loop : loops) {
                    Logger.debug("Stopping " + loop);
                    loop.onStop();
                }
            }
        }
    }

    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("looper_dt", dt);
    }
}
