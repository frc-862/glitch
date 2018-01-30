package org.usfirst.frc862.glitch.paths;
  
import java.util.ArrayList;

import org.usfirst.frc862.glitch.paths.PathBuilder.Waypoint;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;

public class HardCurve implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(50,50,0,0));
        sWaypoints.add(new Waypoint(100,50,10,60));
        sWaypoints.add(new Waypoint(125,75,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(50, 50), Rotation2d.fromDegrees(0.0));
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":50,"y":50},"speed":0,"radius":0,"comment":""},{"position":{"x":100,"y":50},"speed":60,"radius":10,"comment":""},{"position":{"x":125,"y":75},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: HardCurve
}