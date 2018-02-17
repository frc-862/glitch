package org.usfirst.frc862.util;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by phurley on 12/7/16.
 *
 * Only performs the action, when both buttons are pressed. Can be used
 * to guard against dangerous actions or to setup a set of "shift" operations
 * if you run out of buttons.
 *
 */
public class ToggleTriggerGenerator {
    boolean a = true;
    Trigger trigger;

    public ToggleTriggerGenerator(Trigger t) {
        trigger = t;

//        new Trigger.ButtonScheduler() {
//
//            private boolean m_pressedLast = get();
//
//            @Override
//            public void execute() {
//                if (get()) {
//                    m_pressedLast = true;
//                    command.start();
//                } else {
//                    if (m_pressedLast) {
//                        m_pressedLast = false;
//                        command.cancel();
//                    }
//                }
//            }
//        }.start();
    }

    public Trigger buildTriggerA() {
        return new Trigger() {
            @Override
            public void whenInactive(Command command) {
                a = !a;
                super.whenInactive(command);
            }

            @Override
            public boolean get() {
                return a && trigger.get();
            }
        };
    }

    public Trigger buildTriggerB() {
        return new Trigger() {
            @Override
            public void whenInactive(Command command) {
                a = !a;
                super.whenInactive(command);
            }

            @Override
            public boolean get() {
                return !a && trigger.get();
            }
        };
    }
}


