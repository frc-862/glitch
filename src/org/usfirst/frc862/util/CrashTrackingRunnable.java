package org.usfirst.frc862.util;

/**
 * Runnable class with reports all uncaught throws to CrashTracker
 */
abstract class CrashTrackingRunnable implements Runnable {

    @Override
    public final void run() {
        try {
            runCrashTracked();
        } catch (Throwable t) {
            CrashTracker.logThrowableCrash(t);
            throw t;
        }
    }

    protected abstract void runCrashTracked();
}
