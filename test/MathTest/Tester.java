package MathTest;

public class Tester {

	// change all of these values I just put the ones that were in the video
	// The stall torque, or max torque
	public static double kStallTorque = 2.402;
	// Stall current in amps
	public static double kStallCurrent = 126.145;
	// Free speed in RPM
	public static double kFreeSpeed = 5015.562;
	// Free current in amps
	public static double kFreeCurrent = 1.170;
	// mass of the elevator
	public static double kMass = 20.0;
	// number of motors
	public static double kNumMotors = 2.0;
	// Resistance of the motor
	public static double kResistance = 12.0 / kStallCurrent;
	// Motor velocity constant
	public static double Kv = ((kFreeSpeed / 60.0 * 2.0 * Math.PI) / (12.0 - kResistance * kFreeCurrent));
	// Torque constant
	public static double Kt = (kNumMotors * kStallTorque) / kStallCurrent;
	// Gear ratio
	public static double kG = 72.0 / 12.0 * 22.0 / 16.0;
	// Radius of pulley
	public static double kr = 0.25 * 0.0254 * 22.0 / Math.PI / 2.0;
	// control loop time step
	public static double kDt = 0.010;
	
	public double velocity = 0.0;
	public double position = 0.0;
	
	public Tester() {
		
	}
	
	// V = I * R + omega / Kv
	// torque = Kt * I
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//----------------------------------------+---------------------------------------
	
	public double getAcceleration(double voltage) {
		return -Kt * kG / (Kv * kResistance * Math.pow(kr, 2) * kMass) * velocity + kG * Kt / (kResistance * kr * kMass) * voltage;
	}
	public double getCurrentTime () {
		return currentTime; 
	}
	double currentTime = 0;
	public void simulateTime(double voltage, double time) {
		double kSimTime = 0.0001;
		
		while (currentTime < time) {
			double acc = getAcceleration(voltage);
			position += velocity * currentTime;
			velocity += acc * currentTime;
			currentTime += kSimTime;
		}
		
	}

	public static int testMe() {
		return 42;
	}
	
	public double update(double encoder, boolean limitTriggered, boolean enabled) {
		return 1.0;

		
	}
	
	public void setGoal(double goal) {
		if (goal > kMaxHeight)
			realGoal = kMaxHeight;
		else 
			realGoal = goal;
	}
	public static double dt = 0.05;
	
	final public static double kZeroingVelocity = 0.01;
	
	final public static double kMaxHeight = 0.50;
	
	public static double realGoal = 0;
	//currentPos is the current position
	public static double currentPos = 0;
	
}
