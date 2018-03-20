package org.usfirst.frc862.util;

import java.util.function.DoubleSupplier;

class LoggerValue implements DoubleSupplier {
    private double value;
    
    public LoggerValue() {
        this.value = 0.0;
    }
    
    public LoggerValue(double value) {
        this.value = value;
    }

    public void set(double value) {
        this.value = value;
    }
    
    public double get() {
        return value;
    }
    
    @Override
    public double getAsDouble() {
        return value;
    }
    
}
