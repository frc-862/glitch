package org.usfirst.frc862.util;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Created by phurley on 12/7/16.
 *
 * Only performs the action, when both buttons are pressed. Can be used
 * to guard against dangerous actions or to setup a set of "shift" operations
 * if you run out of buttons.
 *
 */
public class TwoButtonTrigger extends Trigger {
    JoystickButton button1;
    JoystickButton button2;

    public TwoButtonTrigger(JoystickButton b1, JoystickButton b2) {
        button1 = b1;
        button2 = b2;
    }

    @Override
    public boolean get() {
        return button1.get() && button2.get();
    }
}


