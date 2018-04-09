// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc862.glitch;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc862.glitch.commands.*;
import org.usfirst.frc862.glitch.paths.StraightSwitch;
import org.usfirst.frc862.glitch.subsystems.*;
import org.usfirst.frc862.util.CrashTracker;
import org.usfirst.frc862.util.DataLogger;
import org.usfirst.frc862.util.Logger;
import org.usfirst.frc862.util.Looper;

import java.io.File;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {


    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Core core;
    public static Shifter shifter;
    public static Gripper gripper;
    public static Lift lift;
    public static ShineBois shineBois;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CubeVision cubeVision;

    public static RobotStateEstimator robotStateEstimator;
    private static boolean autonStartOnLeft = true;
    private static boolean fmsSwitchOnLeft = true;
    private static boolean fmsScaleOnLeft = true;
    private static boolean multiCubeAuton;

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();
    SendableChooser<String> sideChooser = new SendableChooser<>();
    SendableChooser<String> cubeChooser = new SendableChooser<>();

//    private Looper fastLooper;
//    private Looper slowLooper;
//    private Looper reallySlowLooper;

    public static void resetLoggingFiles() {
        DriverStation ds = DriverStation.getInstance();
        String fn = "robot";
        switch (ds.getMatchType()) {
            case Practice:
                fn = String.format("practice-%d-%d", ds.getMatchNumber(), ds.getReplayNumber());
                break;

            case Qualification:
                fn = String.format("qual-%d-%d", ds.getMatchNumber(), ds.getReplayNumber());
                break;

            case Elimination:
                fn = String.format("elim-%d-%d", ds.getMatchNumber(), ds.getReplayNumber());
                break;

            default:
                break;
        }
        Logger.setBaseFileName(fn);
        DataLogger.setBaseFileName(fn);
        Logger.info("Setup filename " + fn);
    }

    @Override
    protected void loopFunc() {
        try {
            super.loopFunc();
        } catch (Throwable t) {
            // ported from https://github.com/wpilibsuite/allwpilib/pull/926/commits
            // to better report unexpected exceptions
            Throwable cause = t.getCause();
            if (cause != null) {
                t = cause;
            }
            CrashTracker.logThrowableCrash(cause);
            DriverStation.reportError("Unhandled exception: " + t.toString(),
                    t.getStackTrace());
            DriverStation.reportWarning("Robots should not quit, but yours did!", false);
            DriverStation.reportError(
                    "The loopFunc() method (or methods called by it) should have handled "
                            + "the exception above.", false);

            System.exit(1);
        }

    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        core = new Core();
        shifter = new Shifter();
        gripper = new Gripper();
        lift = new Lift();
        shineBois = new ShineBois();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        cubeVision = new CubeVision();
        robotStateEstimator = new RobotStateEstimator(driveTrain, core);

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.addObject("SwitchAuton", new SwitchAuton());
        chooser.addObject("ScaleAuton", new ScaleAuton());
        chooser.addObject("ScaleOutSideAuton", new ScaleOutsideAuton());
        chooser.addObject("SmartAuton", new SmartAuton());
        chooser.addObject("Vision Test Auton", new VisionTestAuton());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        ShineBois.rainbow();

        chooser.addObject("StraightAuton", new StraightSwitch());
        chooser.addObject("Do Nothing", null);
        SmartDashboard.putData("Auton Mode", chooser);

        sideChooser.addDefault("Left", "left");
        sideChooser.addObject("Right", "right");
        SmartDashboard.putData("Auton Side", sideChooser);

        cubeChooser.addDefault("Single", "single");
        cubeChooser.addObject("Mutli", "multi");
        SmartDashboard.putData("Power Cubes", cubeChooser);

        Robot.resetLoggingFiles();

        Logger.getWriter().onStart();
        DataLogger.getLogger().onStart();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {
        ShineBois.reset();
        DataLogger.flush();
        Logger.getWriter().flush();
    }

    // get rid of overload me message
    private int counter = 0;
    @Override
    public void robotPeriodic() {
       DataLogger.getLogger().onLoop();

       if (counter % 25 == 0) {
           DataLogger.getLogger().getLogWriter().onLoop();
           Logger.getWriter().onLoop();
       }

       if (counter % 100 == 0) {
           DataLogger.flush();
           Logger.getWriter().flush();
       }

       counter += 1;
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        autonStartOnLeft = sideChooser.getSelected().equals("left");
        multiCubeAuton = cubeChooser.getSelected().equals("multi");

        String msg = DriverStation.getInstance().getGameSpecificMessage();
        while (msg == null || msg.length() < 2) {
            msg = DriverStation.getInstance().getGameSpecificMessage();
        }

        Logger.info("FMS Auton Message: " + msg);

        fmsSwitchOnLeft = msg.substring(0,1).equals("L");
        fmsScaleOnLeft = msg.substring(1,2).equals("L");

        Logger.info("Switch on Left: " + fmsSwitchOnLeft);
        Logger.info("Scale on Left: " + fmsScaleOnLeft);

        // schedule the autonomous command
        Logger.info("autonomousCommnad: " + autonomousCommand);
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();

        // setup our default commands (which seemed to be causing
        // trouble with our dynamic autons?

        // Also wrapping these conditionally to reduce the amount
        // of error logging when testing without controllers
        if (oi.driverControlsAvailable()) {
//            driveTrain.setDefaultCommand(new TankDrive());
//            shifter.setDefaultCommand(new HighGearAutoShift());
            shifter.setDefaultCommand(new AutoShift());
        }

        if (oi.copilotControlsAvailable()) {
            lift.setDefaultCommand(new CoPilotAuto());
        }
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putData("Scheduler", Scheduler.getInstance());
    }

    public static boolean isOBot() {
        return new File("/home/lvuser/obot").exists();
    }

    public static boolean isEcho() {
        return new File("/home/lvuser/echo").exists();
    }
    
    public static boolean startOnLeft() {
        return autonStartOnLeft;
    }

    public static boolean startOnRight() {
        return !autonStartOnLeft;
    }

    public static boolean switchOnLeft() {
        return fmsSwitchOnLeft;
    }

    public static boolean scaleOnLeft() {
        return fmsScaleOnLeft;
    }

    public static double matchTime() {
        return DriverStation.getInstance().getMatchTime();
    }

    public static double autonTimeRemaining() {
        return Math.max(0, 15 - DriverStation.getInstance().getMatchTime());
    }

    public static boolean attemptMultiCubeAuton() {
        return multiCubeAuton;
    }
}
