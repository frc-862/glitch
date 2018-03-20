package MathTest;

class TesterLoop {


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
