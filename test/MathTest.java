import org.junit.Test;
import org.junit.BeforeClass;
import MathTest.Tester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MathTest {

    @Test
    public void verifyMeaning() {
        assertEquals("Meaning?", 42, Tester.testMe());
//        assertTrue("Pattern did not validate zip code", Tester.testMe() == 41);
    }
}

