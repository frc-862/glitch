package org.usfirst.frc862.glitch.paths;

import java.util.ArrayList;

import org.usfirst.frc862.glitch.paths.PathBuilder.Waypoint;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;

public class Straight implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(0,0,0,0));
        sWaypoints.add(new Waypoint(120,-1,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(28, 49), Rotation2d.fromDegrees(180.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":28,"y":49},"speed":0,"radius":0,"comment":""},{"position":{"x":246,"y":48},"speed":60,"radius":50,"comment":""},{"position":{"x":245,"y":283},"speed":60,"radius":30,"comment":""},{"position":{"x":176,"y":283},"speed":60,"radius":30,"comment":""},{"position":{"x":176,"y":254},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: Right Start Left Switch


    public static void main(String argv[]) {
        System.out.println("Hi Pat!");
        Path path = (new RightStartLeftSwitch()).buildPath();
        System.out.println(path);
    }
}
