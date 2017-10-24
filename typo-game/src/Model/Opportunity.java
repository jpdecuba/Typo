package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Observable;
import java.util.Random;

public class Opportunity extends Observable {

    private OppName name;
    private Difficulty diff;
    private Player p;
    private int posX, posY;
    private int width = 25, length = 25;
    private int minX,minY,maxX,maxY;

    public Opportunity(OppName name, Difficulty diff) {
        this.name = name;
        this.diff = diff;

        int low = 25;
        int high = 125;
        Random r = new Random();
        posX = r.nextInt(maxX-minX)+minX;
        posY = r.nextInt(maxY-minY)+minY;
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
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
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
