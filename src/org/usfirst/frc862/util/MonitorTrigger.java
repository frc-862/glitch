package org.usfirst.frc862.util;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Created by phurley on 12/7/16.
 */

class MonitorTrigger extends Trigger {
    private final BooleanSupplier test;
    private final double duration;
    private final Timer timer = new Timer();

    private MonitorTrigger(double duration, BooleanSupplier test) {
        this.test = test;
        this.duration = duration;
        timer.start();
    }

    public MonitorTrigger(double duration) {
        this(duration, () -> false);
        timer.start();
    }

    public boolean get() {
        if (!test.getAsBoolean()) {
            timer.reset();
        }
        return timer.get() >= duration;
    }
}
