package Model;

public abstract class Opportunity {

    String name;
    Difficulty diff;

    public Opportunity(String name, Difficulty diff) {
        this.name = name;
        this.diff = diff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    public void Effect(Player p){
        
    }
}
