package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Observable;

public class Opportunity extends Observable {

    private OppName name;
    private Difficulty diff;
    private Player p;

    public Opportunity(OppName name, Difficulty diff) {
        this.name = name;
        this.diff = diff;
    }

    public OppName getName() {
        return name;
    }

    public void setName(OppName name) {
        this.name = name;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    public Player getPlayer() {
        return p;
    }

    public void Effect(Player p) {
        this.p = p;
        this.setChanged();
        this.notifyObservers(this);
    }
}
