package org.usfirst.frc862.util;

/**
 * Created by phurley on 12/7/16.
 */
public interface ValueFilter {
    public void reset();
    public double filter(double value);
    public double get();
}
