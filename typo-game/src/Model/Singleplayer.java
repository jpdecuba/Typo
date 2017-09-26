package Model;

import java.sql.Connection;

public class Singleplayer extends Session {

    //Constructor
    public Singleplayer(Difficulty difficulty){
        //creates an singleplayer game
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
}
