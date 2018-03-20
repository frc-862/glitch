package MathTest;

public class Tester {

	// change all of these values I just put the ones that were in the video
	// The stall torque, or max torque
	private static final double kStallTorque = 2.402;
	// Stall current in amps
	private static final double kStallCurrent = 126.145;
	// Free speed in RPM
	private static final double kFreeSpeed = 5015.562;
	// Free current in amps
	private static final double kFreeCurrent = 1.170;
	// mass of the elevator
	private static final double kMass = 20.0;
	// number of motors
	private static final double kNumMotors = 2.0;
	// Resistance of the motor
	private static final double kResistance = 12.0 / kStallCurrent;
	// Motor velocity constant
	private static final double Kv = ((kFreeSpeed / 60.0 * 2.0 * Math.PI) / (12.0 - kResistance * kFreeCurrent));
	// Torque constant
	private static final double Kt = (kNumMotors * kStallTorque) / kStallCurrent;
	// Gear ratio
	private static final double kG = 72.0 / 12.0 * 22.0 / 16.0;
	// Radius of pulley
	private static final double kr = 0.25 * 0.0254 * 22.0 / Math.PI / 2.0;
	// control loop time step
	public static double kDt = 0.010;
	
	private double velocity = 0.0;
	public double position = 0.0;
	
	public Tester() {
		
	}
	
	// V = I * R + omega / Kv
	// torque = Kt * I
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//----------------------------------------+---------------------------------------
	
	private double getAcceleration(double voltage) {
		return -Kt * kG / (Kv * kResistance * Math.pow(kr, 2) * kMass) * velocity + kG * Kt / (kResistance * kr * kMass) * voltage;
	}
	public double getCurrentTime () {
		return currentTime; 
	}
	private double currentTime = 0;
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
	
	private final static double kMaxHeight = 0.50;
	
	private static double realGoal = 0;
	//currentPos is the current position
	public static double currentPos = 0;
	
}
