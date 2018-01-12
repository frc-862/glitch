package org.usfirst.frc862.util;

import java.io.File;
import java.util.ArrayList;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;

public class DataLogger implements Loop {
    private static DataLogger logger;
    private LogWriter writer;
    private ArrayList<String> fieldNames = new ArrayList<String>();
    private ArrayList<DoubleSupplier> fieldValues = new ArrayList<DoubleSupplier>();
    private boolean first_time = true;
    
    public static DataLogger getLogger() {
        if (logger == null) {
            logger = new DataLogger();
        }
        return logger;
    }

    public LogWriter getLogWriter() { return writer; }
    
    public static void addDataElement(String name, DoubleSupplier val) {
        DataLogger me = DataLogger.getLogger();
        me.fieldNames.add(name);
        me.fieldValues.add(val);
    }
    
    public void onStart() {
        if (first_time) {
            writeHeader();
            first_time = false;
        }
    }
    
    public void onStop() {
        writer.flush();
    }
    
    private void writeHeader() {
        String header = "timestamp";
        for (String fld : fieldNames) {
            header += "," + fld;
        }
        writer.logRawString(header);
    }
    
    private void writeValues() {
        String values = Double.toString(Timer.getFPGATimestamp());
        for (DoubleSupplier fld : fieldValues) {
            values += "," + Double.toString(fld.getAsDouble());
        }
        writer.logRawString(values);
    }
    
    private DataLogger() {
        File file = logFileName();
        writer = new LogWriter(file.getAbsolutePath());
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
        base.mkdirs();

        int counter = 0;
        File result = new File(base, String.format("data-%05d.log", counter));
        while (result.exists()) {
            result = new File(base, String.format("data-%05d.log", ++counter));
        }

        return result;
    }

    public void reset_file() {
        writer.flush();
        writer.close();
        File file = logFileName();
        System.out.println("new logfile: " + file);
        writer = new LogWriter(file.getAbsolutePath());
    }
    
    public static void new_file() {
        getLogger().reset_file();
    }
    
    public static void flush() {
        getLogger().writer.flush();
    }

    @Override
    public void onLoop() {
        writeValues();
    }
}
