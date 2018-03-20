package org.usfirst.frc862.util;

/**
 * Interface for loops, which are routine that run periodically in the robot
 * code (such as periodic gyroscope calibration, etc.)
 */
interface Loop {
    default void onStart() {}

    void onLoop();

    default void onStop() {}
}
