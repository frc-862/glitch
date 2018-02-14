package org.usfirst.frc862.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

import org.usfirst.frc862.glitch.Constants;

import edu.wpi.first.wpilibj.Timer;

public class LogWriter implements Loop {
    private BufferedWriter writer;
    private final ArrayBlockingQueue<String> buffer;
    private final Vector<String> drain;
    private boolean overflow = false;

    public LogWriter(String file, int buffer_depth) {
        buffer = new ArrayBlockingQueue<>(buffer_depth);
        drain = new Vector<>(buffer_depth);
        setFileName(file);
    }
    
    public LogWriter(String fname) {
        this(fname, Constants.logDepth);
    }

    public void setFileName(String file) {
        try {
            if (writer != null) {
                drain();
                writer.close();
                buffer.clear();
            }

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLoop() {
        drain();
    }

    public void drain() {
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
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logString(String s) {
        overflow |= !buffer.offer(String.format("%6.3f: %s", Timer.getFPGATimestamp(), s));
    }
    
    @Override
    public void onStart() { }

    @Override
    public void onStop() {
        flush();
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logRawString(String s) {
        overflow |= !buffer.offer(s);
    }
}
