package org.usfirst.frc862.glitch.vision;

/**
 * A container class for Targets detected by the vision system, containing the location in three-dimensional space.
 */
public class TargetInfo {
    private final double x = 1.0;
    private final double y;
    private final double z;

    public TargetInfo(double y, double z) {
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}