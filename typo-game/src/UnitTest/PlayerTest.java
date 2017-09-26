package UnitTest;



import Model.Difficulty;
import Model.HighScore;
import Model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;



public class PlayerTest {

    private Player p1;

    @Before
    public void setUp() throws Exception {

        p1 = new Player();
    }

    @Test
    public void getScore() throws Exception {

        assertEquals(0,p1.getScore());

    }

    @Test
    public void awardPoints() throws Exception {
        p1.setTempPoints(20);
        p1.AwardPoints();
        assertEquals(20,p1.getScore());

    }

    @Test
    public void getLives() throws Exception {


        assertEquals(3,p1.getLives());
    }

    @Test
    public void addLives() throws Exception {

        p1.AddLives(1);

        assertEquals(4,p1.getLives());
    }

    @Test
    public void wrongKeypress() throws Exception {

        assertEquals(true,p1.WrongKeypress());
        assertEquals(true,p1.WrongKeypress());
        assertEquals(false,p1.WrongKeypress());
    }

    @Test
    public void getCombo() throws Exception {
        assertEquals(1,p1.getCombo());


    }

    @Test
    public void setCombo() throws Exception {

        p1.setCombo(2);
        assertEquals(2,p1.getCombo());
    }

    @Test
    public void getTempPoints() throws Exception {

        assertEquals(0,p1.getTempPoints());
    }

    @Test
    public void setTempPoints() throws Exception {
        p1.setTempPoints(30);
        assertEquals(30,p1.getTempPoints());
    }

    @Test
    public void getHighScores() throws Exception {


        assertEquals(null,p1.getHighScores());
    }

    @Test
    public void setHighScores() throws Exception {
        HighScore HS = new HighScore("test",10, Difficulty.Beginner);
        ArrayList<HighScore> hslist = new ArrayList<>();
        hslist.add(HS);
        p1.setHighScores(hslist);

        assertEquals(hslist,p1.getHighScores());
    }

}