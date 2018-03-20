package org.usfirst.frc862.util;

import java.io.File;

import org.usfirst.frc862.glitch.Constants;

public class Logger {
    private final LogWriter writer;

	private static String baseFileName = "robot";

    private static final int TRACE = 0;
    private static final int DEBUG = 10;
    private static final int INFO = 20;
    private static final int WARN = 30;
    private static final int ERROR = 40;

    private static int level = DEBUG;
    private static Logger logger;

    private static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    private Logger() {
        File file = logFileName();
        writer = new LogWriter(file.getAbsolutePath(), Constants.logDepth);            
    }

    public static LogWriter getWriter() {
        return getLogger().writer;
    }
    
    public static void setBaseFileName(String fname) {
    		baseFileName = fname;
    		getLogger().reset_file();
    }
    
    private File logFileName() {
        File base = null;

        // find the mount point
        char mount = 'u';
        while (base == null && mount <= 'z') {
            File f = new File("/" + mount);
            if (f.isDirectory()) {
                base = f;
            }
            ++mount;
        }

        if (base == null) {
            base = new File("/home/lvuser");
        }

        base = new File(base, "log");
        //noinspection ResultOfMethodCallIgnored
        base.mkdirs();

        int counter = 1;
        String name_format = baseFileName + "-%05d.log";
        File result = new File(base, String.format(name_format, counter));
        while (result.exists()) {
            result = new File(base, String.format(name_format, ++counter));
        }

        return result;
    }

    private void logString(String s) {
        writer.logString(s);
    }
    
    private void logString(String format, Object... args) {
        logString(String.format(format, args));
    }

    public static void log(String s) {
        Logger.getLogger().logString(s);
    }
    
    public static void setLevel(int l) {
        level = l;
    }

    public static void trace(String s) {
        if (level <= TRACE)
            getLogger().logString(s);
    }

    public static void trace(String format, Object... args) {
        if (level <= TRACE)
            getLogger().logString(format, args);
    }

    public static void debug(String s) {
        if (level <= DEBUG)
            getLogger().logString(s);
    }

    public static void debug(String format, Object... args) {
        if (level <= DEBUG)
            getLogger().logString(format, args);
    }

    public static void info(String s) {
        if (level <= INFO)
            getLogger().logString(s);
    }

    public static void info(String format, Object... args) {
        if (level <= INFO)
            getLogger().logString(format, args);
    }

    public static void warn(String s) {
        if (level <= WARN) {
            System.err.println(s);
            getLogger().logString(s);
        }
    }

    public static void warn(String s, Object... args) {
        if (level <= WARN) {
            String msg = String.format(s, args);
            System.err.println(msg);
            getLogger().logString(msg);
        }
    }

    public static void error(String s) {
        System.err.println(s);
        if (level <= ERROR) getLogger().logString(s);
    }

    public static void error(String s, Object... args) {
        String msg = String.format(s, args);
        System.err.println(msg);
        if (level <= ERROR) getLogger().logString(msg);
    }

    public static void flush() {
        getLogger().writer.flush();
    }
    
    private void reset_file() {
        writer.setFileName(logFileName().getAbsolutePath());
    }
}
