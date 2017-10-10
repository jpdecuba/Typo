package UnitTest;

import Model.Difficulty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DifficultyTest {

    @org.junit.Test
    public void valueOf() throws Exception {
        Difficulty diff = Difficulty.valueOf(2);
        assertEquals(Difficulty.Expert,diff);
    }
}
