package UnitTest;

import Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;

public class SessionTest {
    Multiplayer mp;
    Singleplayer sp;
    Player p1 = new Player();
    Player p2 = new Player();
    Set s1 = new Set("TEST1");
    Set s2 = new Set("TEST2");

    @Before
    public void setUp() throws Exception {
        mp = new Multiplayer(Difficulty.Beginner);
        sp = new Singleplayer(Difficulty.Beginner);
    }

    @Test(expected = NotImplementedException.class)
    public void activeOpportunity() throws Exception {
        mp.AddPlayer(p1);
        mp.ActiveOpportunity(p1);
    }

    @Test(expected = NotImplementedException.class)
    public void activeOpportunity1() throws Exception {
        mp.AddPlayer(p1);
        mp.AddPlayer(p2);
        mp.ActiveOpportunity(p2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activeOpportunity2() throws Exception {
        mp.ActiveOpportunity(p1);
    }

    @Test
    public void addPlayer() throws Exception {
        mp.AddPlayer(p1);
        mp.AddPlayer(p2);
        Assert.assertEquals(p1, mp.getPlayerOne());
        Assert.assertEquals(p2, mp.getPlayerTwo());
    }

    @Test(expected = NumberFormatException.class)
    public void addPlayer2() throws Exception {
        mp.AddPlayer(p1);
        mp.AddPlayer(p1);
    }

    @Test
    public void removePlayer() throws Exception {
        mp.AddPlayer(p1);
        mp.AddPlayer(p2);
        mp.RemovePlayer(p1);
        mp.RemovePlayer(p2);
        Assert.assertEquals(null, mp.getPlayerOne());
        Assert.assertEquals(null, mp.getPlayerTwo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePlayer2() throws Exception {
        mp.RemovePlayer(p1);
    }

    @Test(expected = NullPointerException.class)
    public void nextSet1() throws Exception {
        mp.NextSet();
    }

    @Test
    public void nextSet() throws Exception {
        mp.sets.add(s1);
        mp.sets.add(s2);
        Assert.assertEquals(s1, mp.NextSet());
        Assert.assertEquals(s2, mp.NextSet());
    }

    @Test
    public void typeCharacter() throws Exception {
        mp.sets.add(s1);
        mp.NextSet();
        Assert.assertEquals(true, mp.TypeCharacter('T', p1));
    }

    @Test
    public void typeCharacter2() throws Exception {
        mp.sets.add(s1);
        mp.NextSet();
        Assert.assertEquals(false, mp.TypeCharacter('F', p1));
    }

    @Test
    public void getDifficulty() throws Exception {
        Assert.assertEquals(Difficulty.Beginner, mp.getDifficulty());
    }

    @Test
    public void setDifficulty() throws Exception {
        mp.setDifficulty(Difficulty.Expert);
        Assert.assertEquals(Difficulty.Expert, mp.getDifficulty());
    }
}