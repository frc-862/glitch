package org.usfirst.frc862.glitch.paths;
  
import java.util.ArrayList;

import org.usfirst.frc862.glitch.paths.PathBuilder.Waypoint;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;

public class Curves implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(0,0,0,0));
        sWaypoints.add(new Waypoint(49,41,30,60));
        sWaypoints.add(new Waypoint(93,6,30,60));
        sWaypoints.add(new Waypoint(159,44,30,60));
        sWaypoints.add(new Waypoint(201,15,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(0, 0), Rotation2d.fromDegrees(180.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":0,"y":0},"speed":0,"radius":0,"comment":""},{"position":{"x":49,"y":41},"speed":60,"radius":30,"comment":""},{"position":{"x":93,"y":6},"speed":60,"radius":30,"comment":""},{"position":{"x":159,"y":44},"speed":60,"radius":30,"comment":""},{"position":{"x":201,"y":15},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: Curves
}