package MathTest;

public class tester {

	// change all of these values I just put the ones that were in the video
	// The stall torque, or max torque
	private static double kStallTorque = 2.402;
	// Stall current in amps
	private static double kStallCurrent = 126.145;
	// Free speed in RPM
	private static double kFreeSpeed = 5015.562;
	// Free current in amps
	private static double kFreeCurrent = 1.170;
	// mass of the elevator
	private static double kMass = 20.0;
	// number of motors
	private static double kNumMotors = 2.0;
	// Resistance of the motor
	private static double kResistance = 12.0 / kStallCurrent;
	// Motor velocity constant
	private static double Kv = ((kFreeSpeed / 60.0 * 2.0 * Math.PI) / (12.0 - kResistance * kFreeCurrent));
	// Torque constant
	private static double Kt = (kNumMotors * kStallTorque) / kStallCurrent;
	// Gear ratio
	private static double kG = 72.0 / 12.0 * 22.0 / 16.0;
	// Radius of pulley
	private static double kr = 0.25 * 0.0254 * 22.0 / Math.PI / 2.0;
	// control loop time step
	private static double kDt = 0.010;
	
	private double velocity = 0.0;
	private double position = 0.0;
	
	public tester() {
		
	}
	
	// V = I * R + omega / Kv
	// torque = Kt * I
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//----------------------------------------+---------------------------------------
	
	private double getAcceleration(double voltage) {
		return -Kt * kG / (Kv * kResistance * Math.pow(kr, 2) * kMass) * velocity + kG * Kt / (kResistance * kr * kMass) * voltage;
	}
	
	private void simulateTime(double voltage, double time) {
		double kSimTime = 0.0001;
		double currentTime = 0;
		while (currentTime < time) {
			double acc = getAcceleration(voltage);
			position += velocity * currentTime;
			velocity += acc * currentTime;
			currentTime += kSimTime;
		}
		
	}

}
