import org.junit.Test;
import org.usfirst.frc862.util.TimedTriggers;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertTrue;

public class TreeListTest {
 @Test
 public void verifySortedOrder() {
     SortedSet<TimedTriggers.TimedCommandEntry> actions = new TreeSet<>();

     actions.add(new TimedTriggers.TimedCommandEntry(null, 3));
     actions.add(new TimedTriggers.TimedCommandEntry(null, 1));
     actions.add(new TimedTriggers.TimedCommandEntry(null, 2));

     assertTrue("Are first things first?", Math.abs(actions.first().getAt() - 1) < 0.00001);
     actions.remove(actions.first());
     assertTrue("Are second things second?", Math.abs(actions.first().getAt() - 2) < 0.00001);
     actions.remove(actions.first());
     assertTrue("Are third things third?", Math.abs(actions.first().getAt() - 3) < 0.00001);
 }
}

