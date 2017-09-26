package Model;

import java.util.ArrayList;

public abstract class Session {

    //Atributes
    private Difficulty difficulty;
    private ArrayList<Player> players;
    private ArrayList<Set> sets;
    private ArrayList<Opportunity> opportunities;

    //Methods
    public void ActiveOpportunity(Player player){
        //give the active opportunity in effect
    }

    public abstract void Start(); //start the game
    public abstract void EndGame(); //Ends the current game

    public boolean NextSet(){
        //calculates to give the next set
    }

    public boolean TypeCharacter(){
        //calculate if the character thats hit is correct
    }



    //Properties
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
}
