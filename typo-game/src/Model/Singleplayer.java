package Model;

import java.sql.Connection;
import java.util.Observable;

public class Singleplayer extends Session {

    //Constructor
    public Singleplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    //start the game
    @Override
    public void Start() {
        if(players.size() == 1){
            for(Player player: players){
                player.addObserver(this);
            }
        }
        else{
            throw new NullPointerException("There are not enough players in the lobby");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!(boolean)arg){
            EndGame();
        }
        else{
            throw new IllegalArgumentException("Session got an unexcpected update");
        }
    }

    //end the game after update
    @Override
    public boolean EndGame() {
        
        return false;
    }
}
