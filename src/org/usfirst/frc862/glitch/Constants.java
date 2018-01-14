package org.usfirst.frc862.glitch;

import java.io.File;

import org.usfirst.frc862.util.ConstantsBase;
import org.usfirst.frc862.util.LightningMath;

public class Constants extends ConstantsBase {
    // Note this is final, it will not be
    // in the config file, and if you put
    // it there, the value will be ignored
    // as the value here in the code is 
    // immutable
        
	//Logging
    public static int logDepth = 1000;

    public static double WheelCircumference = LightningMath.in2ft(4) * Math.PI;
    public static double dead_band = 0.05;
  public static int TALON_TIMEOUT = 10; //ms

  //PID
  public static double PHYSICAL_MAX_SPEED_TICKS = 850;
  public static double MAX_SPEED_TICKS = 600;

  // NOTE we are going to want to tune for high/low gear with
  // at the very least a different feed forward...
  public static double DRIVE_P = 0;
  public static double DRIVE_I = 0;
  public static double DRIVE_D = 0;
  public static double DRIVE_F = 1024.0 / PHYSICAL_MAX_SPEED_TICKS;

  public static double TICS_PER_ROTATION = 360;  // might be wrong!!!

    // Cheesy Constants
    public static double kSegmentCompletionTolerance = 0.1; // inches
    public static double kPathFollowingMaxAccel = 120.0; // inches per second^2
    
    public String getFileName() {
        return "~/glitch.yaml";
    }

    static {
//        Constants.lookupTable = new InterpolatedMap();
//        Constants.lookupTable.put(1.0, 1.0);
//        Constants.lookupTable.put(50.0, 2500.0);
//        Constants.lookupTable.put(100.0, 10000.0);
        
        File file = new File("/home/lvuser/echo");
        if (file.exists()) {
            // echo specific over-rides can go here
        }

        // new Constants().readFromFile();
    }
}
