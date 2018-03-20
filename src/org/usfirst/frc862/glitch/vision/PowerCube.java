package org.usfirst.frc862.glitch.vision;

import org.usfirst.frc862.glitch.Constants;

/**
 * 
 * @author Ethan Pelkie, Darren Tas
 * Container class containing data on Power cubes
 * 
 *
 */
public class PowerCube {
	private int x;
  private int y;
  private final int id;
  private int height;
  private int width;
  private int index = -1;
	private double angle, area, lateral, longitudal, confidence;
	/**
	 * 
	 * @param x - pixels from left edge (pass directly from camera)
	 * @param y - pixels from top edge (pass directly from camera)
	 * @param width - width in pixels (pass directly from camera)
	 * @param height - height in pixels (pass directly from camera)
	 */
	public PowerCube(int id, int x, int y, int width, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//5 ft * (height @ 5 ft / height now)
		this.longitudal = 5.0 * (72.0 / height);
		this.angle = (x - Constants.CAMERA_CENTER_X) * Constants.DEGREES_PER_PIXEL;
		double absAngle = Math.toRadians(this.angle);
		lateral = this.longitudal * Math.sin(absAngle) / Math.sin(Math.PI / 2 - absAngle);
		this.area = width * height;
		this.confidence = 0.5;
		
	}
	
	public void update(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.area = width * height;
		this.angle = 0.2 * (x - Constants.CAMERA_CENTER_X) * Constants.DEGREES_PER_PIXEL + 0.8 * angle;
		longitudal = 0.2 * (5.0 * 72.0 / height) + 0.8 * longitudal;
		double absAngle = Math.toRadians(this.angle);
		lateral = 0.2 * (this.longitudal * Math.sin(absAngle) / Math.sin(Math.PI / 2 - absAngle)) + 0.8 * lateral;
		
		if(confidence < 1) confidence += 0.50;
		if(confidence > 1) confidence = 1;
		
	}
	
	public void predict(double deltaAngle, double deltaDistance) {
		lateral += Math.sin(Math.toRadians(deltaAngle)) * deltaDistance;
		longitudal += Math.cos(Math.toRadians(deltaAngle)) * deltaDistance;
		angle += deltaAngle;
		confidence -= 0.01;
		
	}

	/**
	 * Returns the number of pixels of the cube's center point from the left edge of the screen
	 * @return int x (pixels)
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the number of pixels of the cube's center point from the top edge of the screen
	 * @return int y (pixels)
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the angle from the camera to the center of the cube; left is negative, right is positive
	 * @return double angle (degrees)
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Returns the area of the cube's bounding rectangle
	 * @return double area (pixels)
	 */
	public double getArea() {
		return area;
	}
	
	/**
	 * Returns an Approximate area in feet 
	 * .5in/px accuracy at 5 feet; .8ft/px accuracy at 10 feet.
	 * @return double distance (feet)
	 */
	public double getLongitudal() {
		return longitudal;
	}
	
	public double getId() {
		return id;
	}
	
	public double getLateral() {
		return lateral;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public double getConfidence() {
		return confidence;
	}
	
	public int getIndex() {
		return index;
	}
	
	public PowerCube returnCube(int index) {
		this.index = index;
		return this;
	}

}
