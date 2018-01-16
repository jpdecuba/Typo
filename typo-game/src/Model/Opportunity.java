package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

public class Opportunity extends Observable implements Serializable {

    private OppName name;
    private Difficulty diff;
    private Player p;
    private int posX, posY;
    private int width = 50, length = 50;
    private int minX,minY,maxX,maxY;

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

    public boolean CheckHit(int x, int y){
        return x >= posX && x <= posX + width && y >= posY && y <= posY + length;
    }

    //properties anchor
    public int getPosX() {
        Random r = new Random();
        posX = r.nextInt(maxX-minX)+minX;
        return posX;
    }
    public int getPosY() {
        Random r = new Random();
        posY = r.nextInt(maxY-minY)+minY;
        return posY;
    }

    //set position
    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }

    //properties scale
    public int getWidth() { return width; }
    public int getLength() { return length; }
    public void setWidth(int width) { this.width = width; }
    public void setLength(int length) { this.length = length; }

    //properties Boundry
    public int getMinX() { return minX; }
    public int getMinY() { return minY; }
    public int getMaxX() { return maxX; }
    public int getMaxY() { return maxY; }
    public void setMinX(int minX) { this.minX = minX; }
    public void setMinY(int minY) { this.minY = minY; }
    public void setMaxX(int maxX) { this.maxX = maxX; }
    public void setMaxY(int maxY) { this.maxY = maxY; }
}
