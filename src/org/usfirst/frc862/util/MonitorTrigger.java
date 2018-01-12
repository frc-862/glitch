package org.usfirst.frc862.util;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Created by phurley on 12/7/16.
 */

public class MonitorTrigger extends Trigger {
    BooleanSupplier test;
    double duration;
    Timer timer = new Timer();

    public MonitorTrigger(double duration, BooleanSupplier test) {
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
