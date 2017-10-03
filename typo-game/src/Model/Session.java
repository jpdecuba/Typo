package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Observer;

public abstract class Session implements Observer {

    //Attributes
    private Difficulty difficulty;
    //public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Set> sets = new ArrayList<Set>();
    public ArrayList<Opportunity> opportunities = new ArrayList<Opportunity>();
    private Set currentSet = null;
    private Player playerOne = null;
    private Player playerTwo = null;

    //Methods
    public void ActiveOpportunity(Player player){
        //give the active opportunity in effect on which player???
        if(player == playerOne){
            throw new NotImplementedException();
        }
        else if(player == playerTwo){
            throw new NotImplementedException();
        }
        else{
            throw new IllegalArgumentException("player does not exist in lobby");
        }
    }

    public abstract void Start(); //start the game
    public abstract boolean EndGame(); //Ends the current game


    //adds a player to the game
    public void AddPlayer(Player player){
        if(playerOne == null && player != playerTwo){
            playerOne = player;
        }
        else if(playerTwo == null && player != playerOne){
            playerTwo = player;
        }
        else{
            throw new NumberFormatException("there are already two players");
        }

        /*
        if(players.contains(player)){
            throw new IllegalArgumentException("player already exsists in lobby");
        }
        else{
            players.add(player);
        }
        */
    }

    //Removes the asked player from the game
    public void RemovePlayer(Player player){
        if(playerOne == player){
            playerOne = null;
        }
        else if(playerTwo == player){
            playerTwo = null;
        }
        else{
            throw new IllegalArgumentException("player does not exist in the lobby");
        }

        /*
        if(!players.contains(player)){
            throw new IllegalArgumentException("player does not exist in lobby");
        }
        else{
            players.remove(player);
        }
        */
    }

    /*
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
    */

    public Set NextSet(Player player){
        if (!sets.isEmpty()){
            if(player != null){
                player.AwardPoints();
            }
            currentSet = sets.get(0);
            sets.remove(currentSet);
            return currentSet;
        }
        else {
            throw new NullPointerException("there are no more sets available");
        }
    }

    //calculate if the character thats hit is correct
    public boolean TypeCharacter(String character, Player player) {
        if(currentSet.getCharacters().get(0).getCharacter().toLowerCase().contains(character.toLowerCase())){
            //character is typed correct
            currentSet.getCharacters().remove(0);
            if(currentSet.getCharacters().isEmpty()){
                NextSet(player);
            }
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
    public Player getPlayerOne() { return playerOne; }
    public Player getPlayerTwo() { return playerTwo; }
    public Set getCurrentSet() { return currentSet; }
}
