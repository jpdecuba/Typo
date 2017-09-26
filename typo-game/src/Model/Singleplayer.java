package Model;

import java.sql.Connection;
import java.util.Observable;

public class Singleplayer extends Session {

    //Constructor
    public Singleplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    @Override
    public void Start() {
        if(players.size() == 1){
            //start the game
        }
        else{
            throw new NullPointerException("There are not enough players in the lobby");
        }
    }

    @Override
    public void EndGame() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
