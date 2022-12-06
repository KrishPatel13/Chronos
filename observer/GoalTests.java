package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalTests {

    @Test
    public void addPointsTest() {
        Goal goalA = new Goal("goalA", 50);
        goalA.addPoints(30);
        goalA.addPoints(5);
        assertEquals("goalA: 35/50", goalA.toString());
    }

    @Test
    public void goalCompleteTest() {
        Goal goalB = new Goal("goalB", 60);
        assertEquals(true, goalB.addPoints(60));
    }

    @Test
    public void testGoalToString() {
        Goal goalC = new Goal("goalC", 100);
        assertEquals("goalC: 0/100", goalC.toString());
        goalC.addPoints(50);
        assertEquals("goalC: 50/100", goalC.toString());
        goalC.addPoints(50);
        assertEquals("goalC", goalC.toString());
        goalC.addPoints(50);
        assertEquals("goalC", goalC.toString());
    }
}
