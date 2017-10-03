package UnitTest;

import Model.Difficulty;
import Model.Multiplayer;
import Model.Player;
import Model.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;

public class MultiplayerTest {
    Multiplayer mp = new Multiplayer(Difficulty.Beginner);
    Player p1 = new Player();
    Player p2 = new Player();
    Set s1 = new Set("TEST1");
    Set s2 = new Set("TEST2");

    @Before
    public void setUp() throws Exception {
        mp = new Multiplayer(Difficulty.Beginner);
    }

    @Test
    public void start() throws Exception {
        mp.AddPlayer(p1);
        mp.AddPlayer(p2);
        mp.Start();
    }

    @Test (expected = NullPointerException.class)
    public void start2() throws Exception {
        mp.Start(); }

    @Test
    public void update() throws Exception {
        mp.AddPlayer(p1);
        mp.AddPlayer(p2);
        mp.Start();
        p1.WrongKeypress();
        p1.WrongKeypress();
        p1.WrongKeypress();
        //mp.update(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update2() throws Exception {
        mp.update(null, true); }

    @Test
    public void endGame() throws Exception {
        Assert.assertEquals(false, mp.EndGame());
    }



}