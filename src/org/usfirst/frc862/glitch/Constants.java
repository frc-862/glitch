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

    public static double WheelCircumference = LightningMath.in2ft(6) * Math.PI;

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