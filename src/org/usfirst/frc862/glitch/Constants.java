package org.usfirst.frc862.glitch;

import org.usfirst.frc862.util.ConstantsBase;
import org.usfirst.frc862.util.LightningMath;

public class Constants extends ConstantsBase {
    // Note this is, it will not be
    // in the config file, and if you put
    // it there, the value will be ignored
    // as the value here in the code is 
    // immutable

    //Logging
    public static int logDepth = 1000;

    // Vision
    public static double MAX_WIDTH_TO_HEIGHT_RATIO = 2.2;
    public static double MAX_HEIGHT_TO_WIDTH_RATIO = 2.2;
    public static int MAX_WIDTH = 320;
    public static int MAX_HEIGHT = 250;
    public static int MAX_AREA = 40000;
    public static double MAX_MOMENT = 3.0;
    public static double DEGREES_PER_PIXEL = .1846590;
    public static int CAMERA_CENTER_X = 176;
    public static double MAX_ANGLE_DISCREPANCY = 5.0;
    public static double MAX_LATERAL_DISCREPANCY = 1.0;
    public static double MAX_LONGITUDAL_DISCREPANCY = 1.0;
    public static double MIN_CONFIDENCE_FOR_KEEP_CUBE = 0.5;



    /*DRIVE TRAIN*/
    // NOTE we are going to want to tune for high/low gear with
    // at the very least a different feed forward...
    // ~ 95ips
    public static double PHYSICAL_MAX_LOW_SPEED_TICKS = 700;
    // ~ 177ips
    public static double PHYSICAL_MAX_HIGH_SPEED_TICKS = 1300;

    // Lowest input that moves the robot
    public static double STICKTION_SPEED_IPS = LightningMath.ips2talon(7) / PHYSICAL_MAX_HIGH_SPEED_TICKS;
    public static double VEL_COMMAND_RAMP = 6 / (1.0 / 0.02);  // in 1/3 of sec allow joystick to read full change
    public static double VEL_COMMAND_RAMP_HIGH_CG = 3 / (1.0 / 0.02);  // in 1/3 of sec allow joystick to read full change
    public static double ROT_COMMAND_RAMP = 6 / (1.0 / 0.02);

    public static double dead_band = 0.025;
    public static int TALON_TIMEOUT = 10; //ms

    //turnToDegrees
    public static double ANGLE_TOLERANCE_DEGREES = 3;

    // not currently functional
    public static int LOWGEAR_IDX = 0;
    public static int HIGHGEAR_IDX = 1;

    // low gear
    public static double LOWGEAR_DRIVE_P = 1.55;  // 2 oscillated a little in testing
    public static double LOWGEAR_DRIVE_I = 0;
    public static double LOWGEAR_DRIVE_D = 0;
    public static double LOWGEAR_DRIVE_F = 1023 / PHYSICAL_MAX_LOW_SPEED_TICKS;
    public static int LOWGEAR_ALLOWED_DRIVE_ERROR = 20;
    public static double DOWNSHIFT_SLOW_TIME_SECONDS = 0.04;

    // high gear
    public static double HIGHGEAR_DRIVE_P = 0.75;
    public static double HIGHGEAR_DRIVE_I = 0;
    public static double HIGHGEAR_DRIVE_D = 0;
    public static double HIGHGEAR_DRIVE_F = 1023 / PHYSICAL_MAX_HIGH_SPEED_TICKS;
    public static int HIGHGEAR_ALLOWED_DRIVE_ERROR = 40;
    public static double TICS_PER_ROTATION = 360 * 4;
    public static double UPSHIFT_SLOW_TIME_SECONDS = 0.08;


    //Drive PID
    public static double pathP = 7.5;
    public static double pathI = 0;
    public static double pathD = 0;
    public static double pathV = 1;  // Velocity is in IPS, we command IPS
    public static double pathA = 0;
    public static double pathTurn = 10; // 1; // 2; // 1.2; // 0.862;
    public static double pathFeedF = 4; // 4;

  /* ROBOT PHYSICAL CONSTANTS */
    //TODO tune for glitch

    // Wheels
    // in inches
    private static double WheelDiameter = 6.2715;  // 6.2715 END OF GAYLORD NEW WHEELS
    public static double WheelCircumference = (Robot.isOBot() ? 4 : WheelDiameter) * Math.PI;
    public static double kDriveWheelDiameterInches = WheelDiameter;
    public static double kTrackWidthInches = 26.655;
    public static double kTrackScrubFactor = 0.924;

    // Pose of the camera frame w.r.t. the robot frame
    public static double kCameraXOffset = -3.3211;
    public static double kCameraYOffset = 0.0;
    public static double kCameraPitchAngleDegrees = 29.56; // Measured on 4/26
    public static double kCameraYawAngleDegrees = 0.0;
    public static double kCameraDeadband = 0.0;

    /*PATH FOLLOWER*/
    public static double kSegmentCompletionTolerance = 0.1; // inches
    public static double kPathFollowingMaxAccel = 120.0; // inches per second^2


    // Goal tracker constants
    public static double kMaxGoalTrackAge = 1.0;
    public static double kMaxTrackerDistance = 18.0;
    public static double kCameraFrameRate = 30.0;

    // Self test values
    public static double MotorTestPower = 0.3;
    public static double MotorTestDistance = 3;

    public static double reallySlowLoopRate = 2;
    public static double slowLoopRate = 0.5;
    public static double fastLoopRate = 0.01;
    public static double openLoopRamp = 0.2;
    // Note moving ramping to acrade/tank commands as this does not
    // seem very smooth and impacts closed loop tracking
    public static double closedLoopRamp = 0;



    /*AUTOSHIFTER*/
    public static double MinUpshiftVelocity = LightningMath.talon2ips(600);  // TODO calibrate me
    public static double MinRequestedVelocityForUpshift = LightningMath.talon2ips(800); // TODO calibrate me
    public static double shiftHysteresis = 1.0;
    public static double HighCurrentThreshold = 200;
    public static double highCurrentTimeout = 0.150;
    public static double upshiftHighCurrentTimeout = 3.5;
    public static double CrashDeacceleration = -12;
    public static double CoastVelocity = LightningMath.talon2ips(400);
    public static double CoastTriggerTime = 0.1;
    public static double slowDownRate = 0.95;



    /*AUTO STRAIGHTEN*/
    public static double NotTurning = 1;
    public static double NotStraight = 0.025;
    public static double StraightMarginOfError = 1.5;
    public static double StraightenKpLowGear = 0.9 / 30.0;
    public static double StraightenKpHighGear = 0.9 / 30.0;



    /*FOURBAR*/
    //PID
    // TODO Calibrate
    public static int FOURBAR_ALLOWABLE_ERROR = 10;
    public static double FOURBAR_P = 0;
    public static double FOURBAR_I = 0;
    public static double FOURBAR_D = 0;
    public static double FOURBAR_F = 2;
    public static int FOURBAR_ACC = 24 / 2;
    public static int FOURBAR_VEL = 24;
    public static double FOURBAR_VELOCITY_P = 4.0;
    public static double FOURBAR_HOLD_POWER = 0.15;
    public static double FOURBAR_VELOCITY_F = 1023 * FOURBAR_HOLD_POWER / FOURBAR_F;

    public static int ELEVATOR_ALLOWABLE_ERROR = 10;
    public static double ELEVATOR_P = 0;
    public static double ELEVATOR_I = 0;
    public static double ELEVATOR_D = 0;
    public static double ELEVATOR_F = 2;
    public static int ELEVATOR_ACC = 24 / 2;
    public static int ELEVATOR_VEL = 24;
    public static double ELEVATOR_VELOCITY_P = 0.4;
    public static double ELEVATOR_HOLD_POWER = 0.03;
    public static double ELEVATOR_VELOCITY_F = 1023 * ELEVATOR_HOLD_POWER / ELEVATOR_F;
    public static double ELEVATOR_VELOCITY_D = 0.0;

    public static int FOURBAR_SCALE_POS = 370;
    public static int FOURBAR_SCALE_POS_HIGH = 413;
    public static int FOURBAR_SCALE_POS_LOW = 300;

    public static int FOURBAR_SWITCH_POS = 270;
    public static int FOURBAR_COLLECT_POS = 156;
    public static int FOURBAR_SAFE_TO_COLLECT_POS = 100;
    public static int FOURBAR_BOTTOM_POS = 73;
    public static int FOURBAR_EPSILON = 5;

    public static int ELEVATOR_SCALE_POS = 2100;
    public static int ELEVATOR_SCALE_POS_HIGH = 2200;
    public static int ELEVATOR_SCALE_POS_LOW = 1900;
    public static int ELEVATOR_SWITCH_POS = -5800;
    public static int ELEVATOR_COLLECT_POS = -5500;
    public static int ELEVATOR_CAN_DROP_COLLECT_POS = ELEVATOR_COLLECT_POS + 200;
    public static int ELEVATOR_BOTTOM_POS = 900;
    public static int ELEVATOR_EPSILON = 100;

    public static double ALERT_DURATION = 3;
    public static double PRotate = 0.02;
    public static double MinRotatePower = 0.4;
    public static double AutonScaleTime = 5;

    //Collector
    public static double DEFAULT_HOLD_POWER = 0.5;
    public static double DEFAULT_COLLECT_POWER = 0.862;
    public static double DEFAULT_EJECT_POWER = -0.862;
    public static double COLLECT_TRIGGER_DEADZONE = 0;
    public static double EJECT_MAX_FEATHER = .7;
    public static double COLLECTOR_SMOOTHING_FACTOR = 0.09;



  // PDP Slots
    public static int PDP_DRIVE_RIGHT_1 = 0;
    public static int PDP_DRIVE_RIGHT_2 = 1;
    public static int PDP_DRIVE_RIGHT_3 = 2;
    public static int PDP_ARM = 3;
    public static int PDP_COLLECTOR_RIGHT = 4;
    public static int PDP_RAMP = 5;
    public static int PDP_LEDS = 8;
    public static int PDP_COLLECTOR_LEFT = 11;
    public static int PDP_ELEVATOR = 12;
    public static int PDP_DRIVE_LEFT_1 = 13;
    public static int PDP_DRIVE_LEFT_2 = 14;
    public static int PDP_DRIVE_LEFT_3 = 15;
    public static double ELEVATOR_UP_POWER = 5;
    public static double FOURBAR_UP_POWER = 2;
    public static double ELEVATOR_DOWN_POWER = -5;
    public static double FOURBAR_DOWN_POWER = -2;

  public String getFileName() {
        return "~/glitch.yaml";
    }

    static {
        if (Robot.isEcho()) {
            // echo specific over-rides can go here
            FOURBAR_SCALE_POS = 300;
            FOURBAR_SCALE_POS_HIGH = 355;
            FOURBAR_SCALE_POS_LOW = 275;

            FOURBAR_SWITCH_POS = 208;
            FOURBAR_COLLECT_POS = 114;
            FOURBAR_BOTTOM_POS = 25;
            FOURBAR_EPSILON = 5;

            ELEVATOR_SCALE_POS = 1900;
            ELEVATOR_SCALE_POS_HIGH = 2000;
            ELEVATOR_SCALE_POS_LOW = 1800;
            ELEVATOR_SWITCH_POS = -5500;
            ELEVATOR_COLLECT_POS = -5500;
            ELEVATOR_BOTTOM_POS = 200;
            ELEVATOR_EPSILON = 50;
        }

        // new Constants().readFromFile();
    }

}
