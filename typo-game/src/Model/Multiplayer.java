package Model;

import Model.Database.Database;
import javafx.beans.Observable;

public class Multiplayer extends Session{

    //Atributes
    private Database connection;

    //Constructor
    public Multiplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    //start the game
    @Override
    public void Start(){
        if(getPlayerOne() != null && getPlayerTwo() != null){
            getPlayerOne().addObserver(this);
            getPlayerTwo().addObserver(this);
        }
        else{
            throw new NullPointerException("There are not enough players in the lobby");
        }
        /*
        if(players.size() > 1){
            for (Player player: players){
                player.addObserver(this);
            }
        }
        else{
            throw new NullPointerException("There are not enough players in the lobby");
        }
        */
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if(!(boolean)arg){
            EndGame();
        }
        else{
            throw new IllegalArgumentException("Session got an unexpected update");
        }
    }

    //end the game after update
    @Override
    public boolean EndGame() {
        return false;
    }
}
