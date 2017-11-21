package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public abstract class Session extends Observable implements Observer {

    //Attributes
    private Difficulty difficulty;
    public ArrayList<Set> sets = new ArrayList<Set>();
    public ArrayList<Opportunity> opportunities = new ArrayList<Opportunity>();
    private Set currentSet = null;
    private Set lastSet = null;
    private Player playerOne = null;
    private Player playerTwo = null;
    private Opportunity opp = null;

    private ComboTimer combotimer = new ComboTimer();

    //Methods
    public void ActiveOpportunity(Player player,Opportunity opp){
        //give the active opportunity in effect on which player???
        switch (opp.getName()) {
            /*case Reverse: throw new NotImplementedException();
            case Spotlight: throw new NotImplementedException();
            case ComboBonus: throw new NotImplementedException();*/
            case ComboPunish: throw new NotImplementedException();
            case ExtraLife: player.AddLives(1); break;
        }
    }

    public abstract void Start(); //start the game
    public abstract boolean EndGame(); //Ends the current game

    //adds a player to the game
    public void AddPlayer(Player player){
        if(playerOne == null && player != playerTwo){ playerOne = player; }
        else if(playerTwo == null && player != playerOne){ playerTwo = player; }
        else{ throw new NumberFormatException("there are already two players"); }
    }

    //Removes the asked player from the game
    public void RemovePlayer(Player player){
        if(playerOne == player){ playerOne = null; }
        else if(playerTwo == player){ playerTwo = null; }
        else{ throw new IllegalArgumentException("player does not exist in the lobby"); }
    }

    public Set NextSet(Player player){
        if(player != null){
            player.setCombo(combotimer.getCombo(player.getCombo()));
            player.AwardPoints();
            Random r = new Random();
            if(r.nextInt(100) <= 10){
                opp = new Opportunity(OppName.ExtraLife, difficulty);
                this.setChanged();
                this.notifyObservers(opp);
            }
        }
        if (!sets.isEmpty()){
            Random r = new Random();
            currentSet = sets.get(r.nextInt(sets.size()));
            StringBuilder s = new StringBuilder();
            for (Letter l: currentSet.getCharacters()) { s.append(l.getCharacter()); }
            lastSet = new Set(s.toString());
            sets.remove(currentSet);
            sets.add(lastSet);
            combotimer.setStartTime(LocalDateTime.now());
            return currentSet;
        }
        else {
            EndGame();
            throw new NullPointerException("there are no more sets available");
        }
    }

    public void mouseclick(int x,int y,Player player){
        if(opp.CheckHit(x,y)){
            ActiveOpportunity(player, opp);
            setChanged();
            notifyObservers(false);
        }
    }

    //calculate if the character thats hit is correct
    public boolean TypeCharacter(String character, Player player) {
        String currentletter = currentSet.getCharacters().get(0).getCharacter().toString();

        if(character.equals(currentletter)){ //character is typed correct
            player.setTempPoints(5); //temporary points set to 5
            currentSet.getCharacters().remove(0);
            if(currentSet.getCharacters().isEmpty()){
                NextSet(player);
            }
            return true;
        }
        else{ //character is typed incorrect
            player.WrongKeypress();
            return false;
        }
    }

    public void getOpportunity(){
        this.setChanged();
        this.notifyObservers(opp);
    }

    //Properties
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
    public Player getPlayerOne() { return playerOne; }
    public Player getPlayerTwo() { return playerTwo; }
    public Set getCurrentSet() { return currentSet; }
}
