package org.usfirst.frc862.glitch.vision;

public class CubeNotFoundException extends Exception {
	public CubeNotFoundException() {
		super("No cubes are currently in the list.");
	}
	public CubeNotFoundException(long framesSinceContact) {
		super("A cube has not been seen in " + framesSinceContact + " frames.");
	}

}
