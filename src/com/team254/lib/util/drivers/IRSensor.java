package com.team254.lib.util.drivers;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Counter;

/**
 * Driver for an analog Sharp IR sensor (or any distance sensor where output voltage is a function of range, really).
 */
class IRSensor {
    private final AnalogInput mAnalogInput;
    private final AnalogTrigger mAnalogTrigger;
    private final Counter mCounter;

    public IRSensor(int port, double min_trigger_voltage, double max_trigger_voltage) {
        mAnalogInput = new AnalogInput(port);
        mAnalogInput.setAverageBits(6);
        mAnalogTrigger = new AnalogTrigger(mAnalogInput);
        mAnalogTrigger.setAveraged(true);
        mAnalogTrigger.setFiltered(false);
        mAnalogTrigger.setLimitsVoltage(min_trigger_voltage, max_trigger_voltage);
        mCounter = new Counter(mAnalogTrigger.createOutput(AnalogTriggerType.kState));
    }

    public int getCount() {
        return mCounter.get();
    }

    public double getVoltage() {
        return mAnalogInput.getAverageVoltage();
    }

    public boolean seesBall() {
        return mAnalogTrigger.getTriggerState();
    }

    public void resetCount() {
        mCounter.reset();
    }
}
