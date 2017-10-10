package UnitTest;

import Model.Letter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LetterTest {
    Letter letter = new Letter("t");

    @org.junit.Test
    public void type() throws Exception {
        letter.type("t");
        assertEquals(true,letter.getTyped());
        letter.type("b");
        assertNotEquals(true,letter.getTyped());
        assertEquals("t",letter.getCharacter());
    }
}
