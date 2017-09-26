package Model;

import Model.Database.Database;

public class Multiplayer extends Session {

    //Atributes
    private Database connection;

    //Constructor
    public Multiplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    @Override
    public void Start(){
        if(players.size() > 1){
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
