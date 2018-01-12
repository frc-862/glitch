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
        
    //Autoshifting
    public static double HCScaryTriggerTime = 1.5;
    public static double CrashDuration = 0.1;
    public static double LostBattleTriggerTime = 0.75;
    public static double CompressorPauseTime = 1;
    public static double HCTriggerTime = 0.25;
    public static double HighCurrentThreshold = 200;
    public static double highCurrentTimeout = 0.150;
    public static double LowVoltage = 9;
    public static double CrashDeacceleration = -12;
    public static double CoastTriggerTime = 0.1;
    public static double CoastVelocity = 175;
    public static double VelocityUpshiftTime = 0.002;
    public static double MinRequestedPowerForUpshift = 0.70;
    public static double LVTriggerTime = 0.25;
    public static double MaxCoastPower = 0.35;
    public static double MinUpshiftVelocity = 225;
    public static double shiftHysteresis = 0.5;
    public static double shiftDelay = 0.25;
    public static double minimumShiftDelay = 0.75;
    public static double maxAirUnits = 20; //amount of air in tank
    public static double shiftAirUnit = 1; //amount of air used per actuation of the shifter
    public static double minimumAirReserve = 3;
    public static double compressorDisabledDelay = 0.5;
    public static double seriousCurrentDrawTimer = 0.15;
	public static double VeerDifference = 57.0; //idk how this works though
	public static double autoshightStraightenP = -1.5;
    public static double autoshightStraightenPeriod = 0.2;
    public static double ArcadeAngleP = 0.6;
    public static double autoshiftEpsilon = 1;
    
    //Robot Physical Specs
    public static double wheelBase = 22.0 / 12.0;  // in feet
    public static double WheelDiameter = 4.0 / 12.0;  // in feet
    public static double WheelCircumference = WheelDiameter * Math.PI;  // in feet
    
    //Joystick
    public static double deadband = 0.11;

    //DriveTrain
	public static double MinRotatePower = 35;
    public static double rotateIGain = MinRotatePower / 3.0;

    public static double fastLoopRate = 0.01;
    public static double mediumLoopRate = 0.1;
    public static double slowLoopRate = 0.5;
    public static double maxRampRate = 0.75;
    public static double dataLoggerPeriod = 0.05;  // 20 times a second by default
    public static double maxVelocityLow = 350;
    public static double maxVelocityHigh = 850;
    public static double driveRampRate = 50.0;
    public static int encoderTicksPerRev = 360;
    public static double velocityFeedForwardRLow = 4.6 / 4;
    public static double velocityFeedForwardLLow = 4.5 / 4;
    public static double velocityPTermLow = 0.64;
    public static double velocityPTermHigh = 0.2;
    public static double velocityFeedForwardRHigh = 0.523617647;
    public static double velocityFeedForwardLHigh = 0.452647059;
	public static double MotorOffTime = 1.0;
    public static double rotateEpsilon = 1.5;
    public static double straightCommandDelta = 0.19;
    public static double straightenPGain = 1 / 90.0;
    public static double MinCommandedPower = 0.05;


	//Brake mode (not in use)
    public static double brakeP = 0.5;
    public static double brakeI = 0;
    public static double brakeD = 0;
    public static double brakeF = 0;
    public static int brakeRampRate = 0;
    public static double brakeIZone = 0;
    public static int brakeSlot = 1;
    
	
	//Logging
    public static int logDepth = 1000;

    
    //Mechanisms
    public static double winchRampTime = 3;
    

    //Motion Profile
    public static double pathP = LightningMath.fps2rpm(1.5);
    public static double pathI = 0;
    public static double pathD = 0;
    public static double pathV = LightningMath.fps2rpm(1);
    public static double pathA = 0; //pathV / 2;
    public static double pathTurn = -3.0 / 80;  // borrowed from Poofs :)
    public static double Path_dt = 0.02;
    public static double Path_max_acc = 8/2;
    public static double Path_max_jerk = 45;
    public static double Path_max_vel = 5.5;
    //public static InterpolatedMap lookupTable = new InterpolatedMap(new Double[]{1.0,2.1,3.2,4.3}); //interpolation example

    
    //vision
	public static double DMPKP = 0.0;
	public static double DMPKI = 0.0;
	public static double DMPKD = 0.0;
	public static double DMPKV = 300;
	public static double DMPKA = 0.0;
    public static int kCameraPitchAngleDegrees = 0;
    public static int kCameraYawAngleDegrees = 0;
    public static int kCenterOfTargetHeight = 0;
    public static int kCameraZOffset = 0;
    public static double kTrackScrubFactor = 1.0;
    public static double kTrackEffectiveDiameter = 4.0;
    public static double kPathFollowingMaxAccel = 3.6;
    public static double kLooperDt = 0.05;
    public static double kPathFollowingLookahead = 3.0;
    public static double kPathFollowingMaxVel = 16.0;
    public static double kDriveBaseLockKp = 0.5;
    public static double kDriveBaseLockKi = 0;
    public static double kDriveBaseLockKd = 0;
    public static double kDriveBaseLockKf = 0;
    public static int kDriveBaseLockIZone = 0;
    public static double kDriveBaseLockRampRate = 0;
    public static int kDriveBaseLockAllowableError = 10;
    public static double WallFollowDistance = 300;
    public static double WallFollowSpeed = 2;
    public static double WallFollowP = 0.01;

    //test mode constants  
    public static double MotorTestPower = 0.4;
    public static double WinchTestPower = 0.4;
    public static double MotorTestDistance = 3;

    // Power channels
    public static int winch1PowerChannel = 4;
    public static int winch2PowerChannel = 11;
    // public static double slowWinchCurrent = 35;
    public static double stopWinchCurrent = 110;
    
    // Vision
    public static int kAndroidAppTcpPort = 8254;
    public static double kMaxGoalTrackAge = 2;
    public static double kMaxTrackerDistance = 10;
    public static double kCameraFrameRate = 8;
    public static double kCameraDeadband = 0;
    
    // Autoshut eject door
    public static double notACrawl = 1.5;
    public static double backupPower = LightningMath.fps2rpm(-2.5);
    public static double backupDuration = 1.0;
    public static double autonSpeed = 2.5;
    
    // Magic Motion Configs
    public static double magicVelocity = maxVelocityLow;
    public static double magicAcceleration = maxVelocityLow / 1.5;
    // 1/4 inch
    public static double distanceEpsilon = (1.0 / 12.0) / 4.0;
    public static double MinSpinSpeed = 330;
    public static double slowDownRate = 0.95;
    
    public String getFileName() {
        return "~/valkyrie.yaml";
    }

    static {
//        Constants.lookupTable = new InterpolatedMap();
//        Constants.lookupTable.put(1.0, 1.0);
//        Constants.lookupTable.put(50.0, 2500.0);
//        Constants.lookupTable.put(100.0, 10000.0);
        
        File file = new File("/home/lvuser/obot");
        if (file.exists()) {
            // obot specific over-rides can go here
        }

        // new Constants().readFromFile();
    }
}
