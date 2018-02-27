package org.usfirst.frc862.util;

import edu.wpi.first.wpilibj.I2C;


/*
 * It is designed specifically to work with the
 * Adafruit VL6180X breakout: http://www.adafruit.com/products/3316
 *
 * These sensors use I2C to communicate, 2 pins (SCL+SDA) are required
 * to interface with the breakout.
 *
 * Based on code found here https://github.com/adafruit/Adafruit_VL6180X
 * Copyright AdaFruit 2018
 *
 */


public class AdaFruitVL6180X {
    final static int VL6180X_DEFAULT_I2C_ADDR = 0x29;  ///< The fixed I2C addres

///! Device model identification number
    final static int VL6180X_REG_IDENTIFICATION_MODEL_ID = 0x000;
///! Interrupt configuration
    final static int VL6180X_REG_SYSTEM_INTERRUPT_CONFIG = 0x014;
///! Interrupt clear bits
    final static int VL6180X_REG_SYSTEM_INTERRUPT_CLEAR = 0x015;
///! Fresh out of reset bit
    final static int VL6180X_REG_SYSTEM_FRESH_OUT_OF_RESET = 0x016;
///! Trigger Ranging
    final static int VL6180X_REG_SYSRANGE_START = 0x018;
///! Trigger Lux Reading
    final static int VL6180X_REG_SYSALS_START = 0x038;
///! Lux reading gain
    final static int VL6180X_REG_SYSALS_ANALOGUE_GAIN = 0x03F;
///! Integration period for ALS mode, high byte
    final static int VL6180X_REG_SYSALS_INTEGRATION_PERIOD_HI = 0x040;
///! Integration period for ALS mode, low byte
    final static int VL6180X_REG_SYSALS_INTEGRATION_PERIOD_LO = 0x041;
///! Specific error codes
    final static int VL6180X_REG_RESULT_RANGE_STATUS = 0x04d;
///! Interrupt status
    final static int VL6180X_REG_RESULT_INTERRUPT_STATUS_GPIO = 0x04f;
///! Light reading value
    final static int VL6180X_REG_RESULT_ALS_VAL = 0x050;
///! Ranging reading value
    final static int VL6180X_REG_RESULT_RANGE_VAL = 0x062;

    final static int VL6180X_ALS_GAIN_1 = 0x06;  ///< 1x gain;
    final static int VL6180X_ALS_GAIN_1_25 = 0x05;  ///< 1.25x gain;
    final static int VL6180X_ALS_GAIN_1_67 = 0x04;  ///< 1.67x gain;
    final static int VL6180X_ALS_GAIN_2_5 = 0x03;  ///< 2.5x gain;
    final static int VL6180X_ALS_GAIN_5 = 0x02;  ///< 5x gain;
    final static int VL6180X_ALS_GAIN_10 = 0x01;  ///< 10x gain;
    final static int VL6180X_ALS_GAIN_20 = 0x00;  ///< 20x gain;
    final static int VL6180X_ALS_GAIN_40 = 0x07;  ///< 40x gain;

    final static int VL6180X_ERROR_NONE = 0;   ///< Success!;
    final static int VL6180X_ERROR_SYSERR_1 = 1;   ///< System error;
    final static int VL6180X_ERROR_SYSERR_5 = 5;   ///< Sysem error;
    final static int VL6180X_ERROR_ECEFAIL = 6;   ///< Early convergence estimate fail;
    final static int VL6180X_ERROR_NOCONVERGE = 7;   ///< No target detected;
    final static int VL6180X_ERROR_RANGEIGNORE = 8;   ///< Ignore threshold check failed;
    final static int VL6180X_ERROR_SNR = 11;  ///< Ambient conditions too high;
    final static int VL6180X_ERROR_RAWUFLOW = 12;  ///< Raw range algo underflow;
    final static int VL6180X_ERROR_RAWOFLOW = 13;  ///< Raw range algo overflow;
    final static int VL6180X_ERROR_RANGEUFLOW = 14;  ///< Raw range algo underflow;
    final static int VL6180X_ERROR_RANGEOFLOW = 15;  ///< Raw range algo overflow;


    I2C i2c;
    public AdaFruitVL6180X(I2C.Port port) {
        i2c = new I2C(port, VL6180X_DEFAULT_I2C_ADDR);
    }

/**************************************************************************/
/*!
    @brief  Initializes I2C interface, checks that VL6180X is found and resets chip.
    @param  theWire Optional pointer to I2C interface, &Wire is used by default
    @returns True if chip found and initialized, False otherwise
*/
    /**************************************************************************/
    public boolean begin() {
        if (read8(VL6180X_REG_IDENTIFICATION_MODEL_ID) != 0xB4) {
            return false;
        }

        loadSettings();
        write8(VL6180X_REG_SYSTEM_FRESH_OUT_OF_RESET, 0x00);

        return true;
    }

///**************************************************************************/
///*!
//    @brief  Load the settings for proximity/distance ranging
//*/
//    /**************************************************************************/
    private void loadSettings() {
        // load settings!

        // private settings from page 24 of app note
        write8(0x0207, 0x01);
        write8(0x0208, 0x01);
        write8(0x0096, 0x00);
        write8(0x0097, 0xfd);
        write8(0x00e3, 0x00);
        write8(0x00e4, 0x04);
        write8(0x00e5, 0x02);
        write8(0x00e6, 0x01);
        write8(0x00e7, 0x03);
        write8(0x00f5, 0x02);
        write8(0x00d9, 0x05);
        write8(0x00db, 0xce);
        write8(0x00dc, 0x03);
        write8(0x00dd, 0xf8);
        write8(0x009f, 0x00);
        write8(0x00a3, 0x3c);
        write8(0x00b7, 0x00);
        write8(0x00bb, 0x3c);
        write8(0x00b2, 0x09);
        write8(0x00ca, 0x09);
        write8(0x0198, 0x01);
        write8(0x01b0, 0x17);
        write8(0x01ad, 0x00);
        write8(0x00ff, 0x05);
        write8(0x0100, 0x05);
        write8(0x0199, 0x05);
        write8(0x01a6, 0x1b);
        write8(0x01ac, 0x3e);
        write8(0x01a7, 0x1f);
        write8(0x0030, 0x00);

        // Recommended : Public registers - See data sheet for more detail
        write8(0x0011, 0x10);       // Enables polling for 'New Sample ready'
        // when measurement completes
        write8(0x010a, 0x30);       // Set the averaging sample period
        // (compromise between lower noise and
        // increased execution time)
        write8(0x003f, 0x46);       // Sets the light and dark gain (upper
        // nibble). Dark gain should not be
        // changed.
        write8(0x0031, 0xFF);       // sets the # of range measurements after
        // which auto calibration of system is
        // performed
        write8(0x0040, 0x63);       // Set ALS integration time to 100ms
        write8(0x002e, 0x01);       // perform a single temperature calibration
        // of the ranging sensor

        // Optional: Public registers - See data sheet for more detail
        write8(0x001b, 0x09);       // Set default ranging inter-measurement
        // period to 100ms
        write8(0x003e, 0x31);       // Set default ALS inter-measurement period
        // to 500ms
        write8(0x0014, 0x24);       // Configures interrupt on 'New Sample
        // Ready threshold event'
    }


///**************************************************************************/
///*!
//    @brief  Single shot ranging. Be sure to check the return of {@link readRangeStatus} to before using the return value!
//    @return Distance in millimeters if valid
//*/
//    /**************************************************************************/
    public int readRange() {
        // wait for device to be ready for range measurement
        while ((read8(VL6180X_REG_RESULT_RANGE_STATUS) & 0x01) == 0) {
            //TODO be smarter?
        }

        // Start a range measurement
        write8(VL6180X_REG_SYSRANGE_START, 0x01);

        // Poll until bit 2 is set
        while ((read8(VL6180X_REG_RESULT_INTERRUPT_STATUS_GPIO) & 0x04) == 0) {
            //TODO be smarter?
        }

        // read range in mm
        int range = read8(VL6180X_REG_RESULT_RANGE_VAL);

        // clear interrupt
        write8(VL6180X_REG_SYSTEM_INTERRUPT_CLEAR, 0x07);

        return range;
    }


///**************************************************************************/
///*!
//    @brief  Request ranging success/error message (retreive after ranging)
//    @returns One of possible VL6180X_ERROR_* values
//*/
//    /**************************************************************************/
//
//    uint8_t Adafruit_VL6180X::readRangeStatus(void) {
//        return (read8(VL6180X_REG_RESULT_RANGE_STATUS) >> 4);
//    }
//
//
///**************************************************************************/
///*!
//    @brief  Single shot lux measurement
//    @param  gain Gain setting, one of VL6180X_ALS_GAIN_*
//    @returns Lux reading
//*/
//    /**************************************************************************/
//
//    float Adafruit_VL6180X::readLux(uint8_t gain) {
//        uint8_t reg;
//
//        reg = read8(VL6180X_REG_SYSTEM_INTERRUPT_CONFIG);
//        reg &= ~0x38;
//        reg |= (0x4 << 3); // IRQ on ALS ready
//        write8(VL6180X_REG_SYSTEM_INTERRUPT_CONFIG, reg);
//
//        // 100 ms integration period
//        write8(VL6180X_REG_SYSALS_INTEGRATION_PERIOD_HI, 0);
//        write8(VL6180X_REG_SYSALS_INTEGRATION_PERIOD_LO, 100);
//
//        // analog gain
//        if (gain > VL6180X_ALS_GAIN_40) {
//            gain = VL6180X_ALS_GAIN_40;
//        }
//        write8(VL6180X_REG_SYSALS_ANALOGUE_GAIN, 0x40 | gain);
//
//        // start ALS
//        write8(VL6180X_REG_SYSALS_START, 0x1);
//
//        // Poll until "New Sample Ready threshold event" is set
//        while (4 != ((read8(VL6180X_REG_RESULT_INTERRUPT_STATUS_GPIO) >> 3) & 0x7));
//
//        // read lux!
//        float lux = read16(VL6180X_REG_RESULT_ALS_VAL);
//
//        // clear interrupt
//        write8(VL6180X_REG_SYSTEM_INTERRUPT_CLEAR, 0x07);
//
//        lux *= 0.32; // calibrated count/lux
//        switch(gain) {
//            case VL6180X_ALS_GAIN_1:
//                break;
//            case VL6180X_ALS_GAIN_1_25:
//                lux /= 1.25;
//                break;
//            case VL6180X_ALS_GAIN_1_67:
//                lux /= 1.76;
//                break;
//            case VL6180X_ALS_GAIN_2_5:
//                lux /= 2.5;
//                break;
//            case VL6180X_ALS_GAIN_5:
//                lux /= 5;
//                break;
//            case VL6180X_ALS_GAIN_10:
//                lux /= 10;
//                break;
//            case VL6180X_ALS_GAIN_20:
//                lux /= 20;
//                break;
//            case VL6180X_ALS_GAIN_40:
//                lux /= 20;
//                break;
//        }
//        lux *= 100;
//        lux /= 100; // integration time in ms
//
//
//        return lux;
//    }
//
/**************************************************************************/
/*!
    @brief  I2C low level interfacing
*/
/**************************************************************************/
// Read 1 byte from the VL6180X at 'address'
byte read8(int address) {
    byte[] buffer = {0};

    if (i2c.read(address, 1, buffer)) {
        Logger.error("Unable to read on i2c");
    }

    return buffer[0];
}

    // write 1 byte
    void write8(int address, int data) {
        i2c.write(address, data);
    }
}



