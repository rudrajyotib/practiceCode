package ds;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FightSimulatorTest {

    @Test
    public void testCase1() {
        FightSimulator fightSimulator = new FightSimulator(3, "11 22 33", "22 33 44");
        assertEquals("WIN", fightSimulator.fightResult());
    }

    @Test
    public void testCase2() {
        FightSimulator fightSimulator = new FightSimulator(6, "112 243 512 343 90 478", "500 789 234 400 452 150");
        assertEquals("WIN", fightSimulator.fightResult());
    }
}