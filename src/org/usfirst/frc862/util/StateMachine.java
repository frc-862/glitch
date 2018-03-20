package org.usfirst.frc862.util;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Timer;

public class StateMachine implements Loop {
    public class State {
        String name;
        
        void enter() {}
        int loop(double delta) { return 0; }
        void exit() {}
    }
    
    class StateEntry {
        State state;
        final HashMap<Integer,StateEntry> transitions = new HashMap<>();
    }
    
    boolean first_time = true;
    private StateEntry currentState;
    private double start;
    private double finish;
    
    public StateMachine(StateEntry se) {
        currentState = se;
        finish = Timer.getFPGATimestamp();
    }
    
    public void onStart() {
        currentState.state.enter();
    }
    
    public void onLoop() {
        start = Timer.getFPGATimestamp();
        int rc = currentState.state.loop(start - finish);
        finish = Timer.getFPGATimestamp();
        
        StateEntry se = currentState.transitions.get(rc);
        if (se != null && se != currentState) {
            currentState.state.exit();
            currentState = se;
            currentState.state.enter();
        }
    }
    
    public void onStop() {
        currentState.state.exit();
    }
}
