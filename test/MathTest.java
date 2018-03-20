import org.junit.Test;
import MathTest.Tester;

import static org.junit.Assert.assertEquals;

public class MathTest {

    @Test
    public void verifyMeaning() {
        assertEquals("Meaning?", 42, Tester.testMe());
//        assertTrue("Pattern did not validate zip code", Tester.testMe() == 41);
    }
}

