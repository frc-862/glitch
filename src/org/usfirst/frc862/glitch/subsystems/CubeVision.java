// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc862.glitch.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc862.glitch.Constants;
import org.usfirst.frc862.glitch.Robot;
import org.usfirst.frc862.glitch.vision.CubeNotFoundException;
import org.usfirst.frc862.glitch.vision.PowerCube;
import org.usfirst.frc862.util.Logger;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class CubeVision extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	
	
	private long framesSinceContact;
	private long lastGoodFrameNum;
	private SerialPort serialIn;
	private ArrayList<PowerCube> cubes, cubesIn;
	private boolean visionInit = true, enableTracking = false;
	private int idCounter = 0;
	private double angle;
	private double leftDist, rightDist;
	private long numUpdates = 0;
	private double lastVisionRead;

	public CubeVision() {
		super();
		cubes = new ArrayList<PowerCube>();
		cubesIn = new ArrayList<PowerCube>();
		try {
			serialIn = new SerialPort(115200, SerialPort.Port.kUSB);
			serialIn.writeString("streamon\n");
		} catch(RuntimeException e) {
			Logger.error(e.getMessage());
			visionInit = false;
		}
		framesSinceContact = 0;
		angle = Robot.core.getGyroAngle() + 180;
		leftDist = Robot.driveTrain.getLeftDistanceInches();
		rightDist = Robot.driveTrain.getRightDistanceInches();
		SmartDashboard.putBoolean("enable vision tracking", enableTracking);
		
	}

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
//    	enableTracking = SmartDashboard.getBoolean("enable vision tracking", false);
        if (serialIn != null) {
			SmartDashboard.putString("Vision Period is here", serialIn.toString());
			try {
				if(enableTracking) {
					collectData();
					makePredictions();
					updateCubeList();
					checkConfidence();
				}
				else {
					collectDataWithoutTracking();
				}
			} catch (Exception err) {
				Logger.error("Vision loop error: " + err);
			}
		}
    }
    
    private void collectDataWithoutTracking() {
    	cubes = new ArrayList<PowerCube>();
    	String inData = serialIn.readString();
    	//Logger.info(inData);
		SmartDashboard.putString("dataIn", inData);
    	if(inData.indexOf("=") != -1) {
	    	SmartDashboard.putString("step", "dataIn");
	    	String lastFrame = inData.substring(inData.lastIndexOf("Frame"));
	    	SmartDashboard.putString("lastFrame", lastFrame);
	    	SmartDashboard.putString("step", "lastFrame");
	    	long frameNum = Long.parseLong(lastFrame.substring(lastFrame.indexOf("Frame:") + 6, lastFrame.indexOf(",")));
	    	SmartDashboard.putNumber("frameNum", frameNum);
	    	int numObjects = Integer.parseInt(lastFrame.substring(lastFrame.indexOf("Objects:") + 8, lastFrame.indexOf("=")));
	    	SmartDashboard.putNumber("numObjects", numObjects);
	    	SmartDashboard.putString("step", "getNums");
	    	if(numObjects > 0 && lastFrame.endsWith("]\r\n")) {
	    		lastVisionRead = Timer.getFPGATimestamp();
	    		SmartDashboard.putString("step", "enteredDataInIf");
	    		framesSinceContact = 0;
	    		String currentCube;
	    		for(int i = 0; i < numObjects; i++) {
	    			currentCube = lastFrame.substring(lastFrame.indexOf("Object:" + i + "["));
	    	    	SmartDashboard.putString("step", "currentCube" + i);
	    			SmartDashboard.putString("currentCube", currentCube);
	    			int x, y, w, h;
	    			x = Integer.parseInt(currentCube.substring(currentCube.indexOf("x:") + 2, currentCube.indexOf(",", currentCube.indexOf("x:"))));
	    			y = Integer.parseInt(currentCube.substring(currentCube.indexOf("y:") + 2, currentCube.indexOf(",", currentCube.indexOf("y:"))));
	    			w = Integer.parseInt(currentCube.substring(currentCube.indexOf("w:") + 2, currentCube.indexOf(",", currentCube.indexOf("w:"))));
	    			h = Integer.parseInt(currentCube.substring(currentCube.indexOf("h:") + 2, currentCube.indexOf(",", currentCube.indexOf("h:"))));
	    			SmartDashboard.putString("step", "m");
	    			double m = Double.parseDouble(currentCube.substring(currentCube.indexOf("m:") + 2, currentCube.indexOf("]", currentCube.indexOf("m:"))));
	    	    	SmartDashboard.putString("step", "xywh");
	    			
	    	    	SmartDashboard.putNumber("m", m);
	    			if(!(w / h >= Constants.MAX_WIDTH_TO_HEIGHT_RATIO || h / w >= Constants.MAX_HEIGHT_TO_WIDTH_RATIO || w >= Constants.MAX_WIDTH || h >= Constants.MAX_HEIGHT || (w * h) >= Constants.MAX_AREA || m > Constants.MAX_MOMENT)) {
	    				SmartDashboard.putString("step", "willPopulate");
	    				cubes.add(new PowerCube(idCounter, x, y, w, h));
	    				idCounter++;
	    				SmartDashboard.putNumber("cube 0 angle", cubes.get(0).getAngle());
	    				SmartDashboard.putNumber("cube 0 long", cubes.get(0).getLongitudal());
	    				SmartDashboard.putNumber("cube 0 lat", cubes.get(0).getLateral());
	    				SmartDashboard.putNumber("cubes.size()", cubes.size());
	    			}
	    	    	SmartDashboard.putString("step", "push");
	    		}
	    		lastGoodFrameNum = frameNum;
	    	}
	    	else {
	    		framesSinceContact = frameNum - lastGoodFrameNum;
	    		SmartDashboard.putNumber("framesSinceContact", framesSinceContact);
	        	SmartDashboard.putString("step", "noObjects");
	    	}
    	}
    	
    }
    
    private void collectData() {
    	String inData = serialIn.readString();
    	cubesIn = new ArrayList<PowerCube>();
    	//Logger.info(inData);
    	if(inData.indexOf("=") != -1) {
	    	SmartDashboard.putString("dataIn", inData);
	    	SmartDashboard.putString("step", "dataIn");
	    	String lastFrame = inData.substring(inData.lastIndexOf("Frame"));
	    	SmartDashboard.putString("lastFrame", lastFrame);
	    	SmartDashboard.putString("step", "lastFrame");
	    	long frameNum = Long.parseLong(lastFrame.substring(lastFrame.indexOf("Frame:") + 6, lastFrame.indexOf(",")));
	    	SmartDashboard.putNumber("frameNum", frameNum);
	    	int numObjects = Integer.parseInt(lastFrame.substring(lastFrame.indexOf("Objects:") + 8, lastFrame.indexOf("=")));
	    	SmartDashboard.putNumber("numObjects", numObjects);
	    	SmartDashboard.putString("step", "getNums");
	    	if(numObjects > 0 && lastFrame.endsWith("]\r\n")) {
	    		SmartDashboard.putString("step", "enteredDataInIf");
				lastVisionRead = Timer.getFPGATimestamp();
	    		framesSinceContact = 0;
	    		String currentCube;
	    		for(int i = 0; i < numObjects; i++) {
	    			currentCube = lastFrame.substring(lastFrame.indexOf("Object:" + i + "["));
	    	    	SmartDashboard.putString("step", "currentCube" + i);
	    			SmartDashboard.putString("currentCube", currentCube);
	    			int x, y, w, h;
	    			x = Integer.parseInt(currentCube.substring(currentCube.indexOf("x:") + 2, currentCube.indexOf(",", currentCube.indexOf("x:"))));
	    			y = Integer.parseInt(currentCube.substring(currentCube.indexOf("y:") + 2, currentCube.indexOf(",", currentCube.indexOf("y:"))));
	    			w = Integer.parseInt(currentCube.substring(currentCube.indexOf("w:") + 2, currentCube.indexOf(",", currentCube.indexOf("w:"))));
	    			h = Integer.parseInt(currentCube.substring(currentCube.indexOf("h:") + 2, currentCube.indexOf(",", currentCube.indexOf("h:"))));
	    			SmartDashboard.putString("step", "m");
	    			double m = Double.parseDouble(currentCube.substring(currentCube.indexOf("m:") + 2, currentCube.indexOf("]", currentCube.indexOf("m:"))));
	    	    	SmartDashboard.putString("step", "xywh");
	    			
	    	    	SmartDashboard.putNumber("m", m);
	    			if(!(w / h >= Constants.MAX_WIDTH_TO_HEIGHT_RATIO || h / w >= Constants.MAX_HEIGHT_TO_WIDTH_RATIO || w >= Constants.MAX_WIDTH || h >= Constants.MAX_HEIGHT || (w * h) >= Constants.MAX_AREA || m > Constants.MAX_MOMENT)) {
	    				cubesIn.add(new PowerCube(idCounter, x, y, w, h));
	    				idCounter++;
	    				SmartDashboard.putNumber("cubein 0 angle", cubesIn.get(0).getAngle());
	    				SmartDashboard.putNumber("cubein 0 long", cubesIn.get(0).getLongitudal());
	    				SmartDashboard.putNumber("cubein 0 lat", cubesIn.get(0).getLateral());
	    				SmartDashboard.putNumber("cubesIn.size()", cubesIn.size());
	    			}
	    			if(cubesIn.size() >= 1) SmartDashboard.putNumber("currentAngle", cubesIn.get(0).getAngle());
	    	    	SmartDashboard.putString("step", "push");
	    		}
	    		lastGoodFrameNum = frameNum;
	    	}
	    	else {
	    		framesSinceContact = frameNum - lastGoodFrameNum;
	    		SmartDashboard.putNumber("framesSinceContact", framesSinceContact);
	        	SmartDashboard.putString("step", "noObjects");
	    	}
    	}
    	
    }
    
    private void makePredictions() {
    	double newAngle = Robot.core.getGyroAngle() + 180;
    	SmartDashboard.putNumber("gyro angle", Robot.core.getGyroAngle());
    	SmartDashboard.putNumber("newAngle", newAngle);
    	double deltaAngle = newAngle - angle;
    	angle = newAngle;
    	SmartDashboard.putNumber("deltaAngle", deltaAngle);
    	
    	//http://ttuadvancedrobotics.wikidot.com/odometry
    	double newLeftDist = Robot.driveTrain.getLeftDistanceInches();
    	double newRightDist = Robot.driveTrain.getRightDistanceInches();
    	double deltaLeftDist = newLeftDist - leftDist;
    	double deltaRightDist = newRightDist - rightDist;
    	double deltaDistance = (deltaLeftDist + deltaRightDist) / 2 / 12;
    	leftDist = newLeftDist;
    	rightDist = newRightDist;
    	SmartDashboard.putString("step", "getSensorData");
    	for(int i = 0; i < cubes.size(); i++) {
    		cubes.get(i).predict(deltaAngle, deltaDistance);
    	}
    	SmartDashboard.putString("step", "predict");
    	numUpdates++;
    	SmartDashboard.putNumber("number of iterations", numUpdates);
    	
    }
    
    private void updateCubeList() {
    	PowerCube best = null;
    	int bestIndex = -1;
    	for(PowerCube cubeIn : cubesIn) {
    		best = null;
    		bestIndex = -1;
    		for(int j = 0; j < cubes.size(); j++) {
    			PowerCube current = cubes.get(j);
    			if(
    					(bestIndex == -1 
    					&& Math.abs(cubeIn.getAngle() - current.getAngle()) < Constants.MAX_ANGLE_DISCREPANCY 
    					&& Math.abs(cubeIn.getLateral() - current.getLateral()) < Constants.MAX_LATERAL_DISCREPANCY 
    					&& Math.abs(cubeIn.getLongitudal() - current.getLongitudal()) < Constants.MAX_LONGITUDAL_DISCREPANCY) 
    					
    					|| (bestIndex != -1 && Math.abs(cubeIn.getAngle() - current.getAngle()) < best.getAngle() 
    					&& Math.abs(cubeIn.getLateral() - current.getLateral()) < best.getLateral() 
    					&& Math.abs(cubeIn.getLongitudal() - current.getLongitudal()) < best.getLongitudal())) {
    				best = current;
    				bestIndex = j;
    			}
    			//Best cube match
    		}
    		SmartDashboard.putString("step", "pre update");
    		if(bestIndex != -1) { 
    			cubes.get(bestIndex).update(cubeIn.getX(), cubeIn.getY(), cubeIn.getWidth(), cubeIn.getHeight());
    		}    		
    		else {
    			cubes.add(cubeIn);
    		}
    	}
    	SmartDashboard.putString("step", "update");
    	
    }
    
    private void checkConfidence() {
    	for(int i = 0; i < cubes.size(); i++) {
    		SmartDashboard.putNumber("cube 0 confidence", cubes.get(0).getConfidence());
    		SmartDashboard.putNumber("cube 0 angle", cubes.get(0).getAngle());
    		SmartDashboard.putNumber("cube 0 long", cubes.get(0).getLongitudal());
    		SmartDashboard.putNumber("cube 0 lat", cubes.get(0).getLateral());
    		if(cubes.get(i).getConfidence() < Constants.MIN_CONFIDENCE_FOR_KEEP_CUBE) {
    			cubes.remove(i);
    			i--;
    		}
    	}
    	SmartDashboard.putString("step", "checkConfidence");
    	SmartDashboard.putNumber("cubes.size()", cubes.size());

    }
    
    /**
     * 
     * @return PowerCube object with index in cubes list defined ()
     * @throws CubeNotFoundException throws if no cubes have been seen for a number of frames
     */
    public PowerCube getBestCube() throws CubeNotFoundException {
    	//if(!visionInit) return null;
    	if(cubes.size() == 0) throw new CubeNotFoundException();
    	
    	double smallestAngle = 999;
    	int bestCubeIndex = -1;
    	for(int i = 0; i < cubes.size(); i++) {
    		if(Math.abs(cubes.get(i).getAngle()) < smallestAngle) {
    			bestCubeIndex = i;
    			smallestAngle = Math.abs(cubes.get(i).getAngle());
    		}
    	}
    	
    	if(bestCubeIndex == -1) throw new CubeNotFoundException();

    	SmartDashboard.putNumber("best cube angle", cubes.get(bestCubeIndex).getAngle());
    	//Index will only be stable if tracking is enabled, so leave it as -1 if tracking is off.
    	if(enableTracking) return cubes.get(bestCubeIndex).returnCube(bestCubeIndex);
    	return cubes.get(bestCubeIndex);
    	
    }
    
    public int getCubeListSize() {
    	return cubes.size();
    }
    
    public PowerCube getCubeFromIndex(int index) {
    	return cubes.get(index);
    }
    
    public ArrayList<PowerCube> getCubeList() {
    	return cubes;
    }
    
    public boolean isTracking() {
    	return enableTracking;
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    

	public double getLastVisionRead() {
    	return lastVisionRead;
	}
}

