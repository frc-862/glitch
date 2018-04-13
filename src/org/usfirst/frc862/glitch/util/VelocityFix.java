package org.usfirst.frc862.glitch.util;

import com.team254.lib.trajectory.Trajectory;
import org.usfirst.frc862.glitch.paths.VelocityTest;

public class VelocityFix {
    public static void main(String[] args) {
        final double max_vel = 110;

        System.out.println("Velocity Fix");
        VelocityTest vt = new VelocityTest();

        Trajectory left = vt.getPath().getPair().left;
        Trajectory right = vt.getPath().getPair().right;
        
        for(int i = 0; i < left.getNumSegments(); ++i) {
            Trajectory.Segment lseg = left.getSegment(i);
            Trajectory.Segment rseg = right.getSegment(i);

            if (lseg.vel > max_vel || rseg.vel > max_vel) {
                System.out.println("Left: " + lseg.vel);
                System.out.println("Right: " + rseg.vel);
            }
        }
    }
}
