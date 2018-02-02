import static org.junit.Assert.assertTrue;

import org.junit.Test;
import MathTest.Tester;

public class ElevatorTest {

	Tester elevator = new Tester();
	
	@Test
	public void testMan() {
		elevator.simulateTime(2, 1);
		assertTrue("Elevator Test", elevator.position > 0);
		
	}
}
