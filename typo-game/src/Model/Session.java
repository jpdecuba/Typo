package Model;

import java.util.ArrayList;
import java.util.Observer;

public abstract class Session implements Observer {

    //Atributes
    private Difficulty difficulty;
    public ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Set> sets = new ArrayList<Set>();
    private ArrayList<Opportunity> opportunities = new ArrayList<Opportunity>();
    private Set currentSet = null;

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
            currentSet = sets.get(0);
            sets.remove(currentSet);
            return currentSet;
        }
        else {
            throw new NullPointerException("there are no more sets available");
        }
    }

    //calculate if the character thats hit is correct
    public boolean TypeCharacter(char character, Player player) {
        char currentCharacter = currentSet.getCharacters()[0].getCharacter();
        if(character == currentCharacter){
            //character is typed correct
            return true;
        }
        else{
            //character is typed incorrect
            player.WrongKeypress();
            return false;
        }
    }

    //Properties
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
}
