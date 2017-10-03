package UnitTest;

import Model.Difficulty;
import Model.Player;
import Model.Singleplayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleplayerTest {
    Singleplayer sp;
    Player p1 = new Player();
    Player p2 = new Player();

    @Before
    public void setUp() throws Exception {
        sp = new Singleplayer(Difficulty.Beginner);
    }

    @Test
    public void start() throws Exception {
        sp.AddPlayer(p1);
        sp.Start();
    }

    @Test (expected = NullPointerException.class)
    public void start2() throws Exception {
        sp.Start(); }

    @Test
    public void update() throws Exception {
        sp.update(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update2() throws Exception {
        sp.update(null, true); }

    @Test
    public void endGame() throws Exception {
        Assert.assertEquals(false, sp.EndGame());
    }

}