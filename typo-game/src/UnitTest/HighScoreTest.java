package UnitTest;

import Model.Difficulty;
import Model.HighScore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class HighScoreTest {
    DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
            FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
    private LocalDate dt = LocalDate.parse("21.05.1997",germanFormatter);
    private HighScore hs = new HighScore("tom",1,Difficulty.Beginner,dt);


    @org.junit.Test
    public void getName() throws Exception {
        assertEquals("tom",hs.getName());
    }

    @org.junit.Test
    public void setName() throws Exception {
        hs.setName("tim");
        assertEquals("tim",hs.getName());
    }

    @org.junit.Test
    public void getScore() throws Exception {
        assertEquals(1,hs.getScore());
    }

    @org.junit.Test
    public void setScore() throws Exception {
        hs.setScore(2);
        assertEquals(2,hs.getScore());
    }

    @org.junit.Test
    public void getDiff() throws Exception {
        assertEquals(Difficulty.Beginner,hs.getDiff());
    }

    @org.junit.Test
    public void setDiff() throws Exception {
        hs.setDiff(Difficulty.Expert);
        assertEquals(Difficulty.Expert,hs.getDiff());
    }

    @org.junit.Test
    public void getDate() throws Exception {
        assertEquals(LocalDate.parse("21.05.1997",germanFormatter),hs.getDate());
    }

    @org.junit.Test
    public void setDate() throws Exception {
        hs.setDate(LocalDate.parse("21.05.1998",germanFormatter));
        assertEquals(LocalDate.parse("21.05.1998",germanFormatter),hs.getDate());
    }

}