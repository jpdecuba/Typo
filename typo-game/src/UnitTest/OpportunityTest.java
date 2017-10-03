package UnitTest;

import Model.Difficulty;
import Model.Opportunity;
import Model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class OpportunityTest{

    TestObserver testObserver = new TestObserver();

    Opportunity opp = new Opportunity("test", Difficulty.Beginner);

    @Test
    public void getName() throws Exception {
        assertEquals("test",opp.getName());
    }

    @Test
    public void setName() throws Exception {
        opp.setName("toast");
        assertEquals("toast",opp.getName());
    }

    @Test
    public void getDiff() throws Exception {
        assertEquals(Difficulty.Beginner,opp.getDiff());
    }

    @Test
    public void setDiff() throws Exception {
        opp.setDiff(Difficulty.Expert);
        assertEquals(Difficulty.Expert,opp.getDiff());
    }

    @Test
    public void getPlayer() throws Exception {
        opp.Effect(new Player());
        assertEquals(0,opp.getPlayer().getScore());
    }

    @Test
    public void effect() throws Exception {

        TestObserver testObserver2 = new TestObserver();

        Opportunity opp3 = new Opportunity("test", Difficulty.Beginner);

        opp3.addObserver(testObserver2);

        opp3.Effect(new Player());






        assertEquals(true, testObserver2.success); }

}