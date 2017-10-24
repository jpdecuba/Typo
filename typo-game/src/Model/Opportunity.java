package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Observable;
import java.util.Random;

public class Opportunity extends Observable {

    private OppName name;
    private Difficulty diff;
    private Player p;
    protected int posX, posY;
    protected int width = 25, length = 25;

    public Opportunity(OppName name, Difficulty diff) {
        this.name = name;
        this.diff = diff;

        int low = 25;
        int high = 125;
        Random r = new Random();
        posX = r.nextInt(high-low)+low;
        posY = r.nextInt(high-low)+low;
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

    public int CheckHit(int x, int y){
        if(x >= posX && x <= posX+width && y >= posY && y <= posY+length){
            return 1;
        }
        else{
            return 0;
        }
    }
}
