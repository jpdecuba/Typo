package Model;

import java.util.ArrayList;

public abstract class Session {

    //Atributes
    private Difficulty difficulty;
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Set> sets;
    private ArrayList<Opportunity> opportunities;

    //Methods
    public void ActiveOpportunity(Player player){
        //give the active opportunity in effect
    }

    public abstract void Start(); //start the game
    public abstract void EndGame(); //Ends the current game


    //adds a player to the game
    public void AddPlayer(Player player){
        if(players.contains(player)){
            throw new IllegalArgumentException("player already exsists in lobby");
        }
        else{
            players.add(new Player());
        }
    }

    public void RemovePlayer(Player player){
        if(!players.contains(player)){
            throw new IllegalArgumentException("player does not exist in lobby");
        }
        else{
            players.remove(player);
        }
    }

    //calculates to give the next set
    public Set NextSet(){
        if (!sets.isEmpty()){
            
        }
        else{

        }
        return false;
    }

    //calculate if the character thats hit is correct
    public boolean TypeCharacter() {
        return false;
    }

    //Properties
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
}
