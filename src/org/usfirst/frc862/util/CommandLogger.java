package org.usfirst.frc862.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import org.usfirst.frc862.glitch.Constants;

import edu.wpi.first.wpilibj.Timer;

public class CommandLogger {
    private final String prefix;
    private BufferedWriter writer;
    private ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(Constants.logDepth);
    private Vector<String> drain = new Vector<String>(Constants.logDepth);
    private boolean overflow = false;
    
    private HashMap<String,String> values = new HashMap<String,String>();
    private ArrayList<String> fieldNames = new ArrayList<String>();
    private boolean first_time = true;
        
    public CommandLogger(String prefix) {
        this.prefix = prefix;
        reset();
    }

    public void reset() {
        File file = logFileName(prefix);
        try {
            if (writer != null) {
                drain();
                writer.close();
                writer = null;
                buffer.clear();
                first_time = true;
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath()), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addDataElement(String name) {
        fieldNames.add(name);
        values.put(name, "");
    }
    
    public void set(String key, String val) {
        values.put(key, val);
    }
    
    public void set(String key, double val) {
        values.put(key, Double.toString(val));
    }
    
    public void write() {
        if (first_time) {
            writeHeader();
            first_time = false;
        }
        this.writeValues();
    }
    
    private void writeHeader() {
        String header = "timestamp";
        for (String fld : fieldNames) {
            header += "," + fld;
        }
        logString(header);
    }

    private void logString(String s) {
        overflow |= !buffer.offer(s);
    }
    
    public void writeValues() {
        String line = Double.toString(Timer.getFPGATimestamp());
        for (String fld : fieldNames) {
            line += "," + values.get(fld);
        }
        logString(line);
    }
    
    private File logFileName(String prefix) {
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
        File result = new File(base, String.format("%s-%05d.log", prefix, counter));
        while (result.exists()) {
            result = new File(base, String.format("%s-%05d.log", prefix, ++counter));
        }

        return result;
    }

    public void drain() {
        if (writer == null) {
           return;
        }

        try {
            buffer.drainTo(drain);
            for (String msg : drain) {
                writer.write(msg);
                writer.newLine();
            }
            if (overflow) {
                writer.write("BUFFER OVERFLOW\n");
                // there is a small race condition here
                // but we can live with it to keep things
                // fast. The right fix would be to lock
                // around the read/write to the overflow
                // boolean, but a false positive will only
                // happen if we were really close to overflow
                // anyway...
                overflow = false;
            }
            drain.clear();
        } catch (Exception e) {
            System.err.println("Error writing buffer");
            e.printStackTrace();
        }
    }
    
    public void flush() {
        try {
            if (writer != null) {
                writer.flush();
            }
        } catch (IOException e) {
            // Do nothing
        }
    }
    
    public void close() {
        try {
            if (writer != null) {
                drain();
                writer.close();
            }
            writer = null;
        } catch (IOException e) {
            // Do nothing
        }
    }
}
