package org.usfirst.frc862.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.wpi.first.wpilibj.command.Command;

public class StatefulCommand extends Command {
    private Enum<?> state;
    private Runnable default_action = () -> {};
    private Enum<?> previous_state = null;
    private Enum<?> calling_state = null;
    
    protected void setState(Enum<?> new_state) {
        state = new_state;
    }
    
    protected Enum<?> getState() {
        return state;
    }
    
    protected Enum<?> getCallingState() {
        return calling_state;
    }
    
    protected void setDefaultAction(Runnable action) {
        default_action = action;
    }

    protected StatefulCommand(Enum<?> state) {
        this.state = state;
    }

    @Override
    protected void initialize() {
        previous_state = null;
        calling_state = state;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private boolean call(String method_name) {
        try {
            Logger.debug("Call " + method_name);
            Method method = getClass().getMethod(method_name);
            method.invoke(this);
        } catch (NoSuchMethodException | SecurityException | 
                 IllegalAccessException | IllegalArgumentException | 
                 InvocationTargetException e) {
            Logger.error("StatefulCommand missing method: " + method_name);
            return false;
        }        
        return true;
    }
    
    private String methodName(Enum<?> state) {
        String state_name = state.name().toLowerCase();
        String method_name = Stream.of(state_name.split("[^a-zA-Z0-9]"))
                .map(v -> v.substring(0, 1).toUpperCase() + v.substring(1).toLowerCase())
                .collect(Collectors.joining());
        method_name = method_name.substring(0, 1).toLowerCase() + method_name.substring(1);
        return method_name;
    }
    
    @Override
    protected void execute() {
        if (previous_state != state) {
            if (previous_state != null) {
                String exit_method = methodName(previous_state) + "Exit";
                call(exit_method);
            }
            
            previous_state = state;
            calling_state = state;
            call(methodName(state) + "Enter");
        }
        
        if (!call(methodName(state))) {
            this.default_action.run();
        }
    }
}
