package com.team254.lib.util.drivers;

import edu.wpi.first.wpilibj.AnalogInput;

import java.util.LinkedList;

/**
 * Driver for an analog Ultrasonic Sensor (mainly to help smooth out noise).
 */
class UltrasonicSensor {
    private final AnalogInput mAnalogInput;
    private final LinkedList<Double> cache;
    double mScalingFactor = 512.0 / 5.0;

    private static final int kCacheSize = 5;

    UltrasonicSensor(int port) {
        mAnalogInput = new AnalogInput(port);
        cache = new LinkedList<Double>();
        cache.add(getRawDistance());
    }

    public void update() {
        cache.add(getRawDistance());
        if (cache.size() > kCacheSize)
            cache.removeFirst();
    }

    private double getRawDistance() {
        return mAnalogInput.getVoltage() * mScalingFactor;
    }

    double getAverageDistance() {
        double total = 0;
        for (Double d : cache) {
            total += d;
        }
        return total / cache.size();
    }

    double getLatestDistance() {
        return cache.getLast();
    }
}
