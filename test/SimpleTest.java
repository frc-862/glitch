import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
 
public class SimpleTest {
 private static final String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
 private static Pattern pattern;
 
 @BeforeClass
 public static void setUpBeforeClass() {
  pattern = Pattern.compile(zipRegEx);
 }
 
 @Test
 public void verifyGoodZipCode() {
  Matcher mtcher = pattern.matcher("22101");
  boolean isValid = mtcher.matches();       
  assertTrue("Pattern did not validate zip code", isValid);
 }
}

