package Model;

import sample.DBConnection;

public class Multiplayer extends Session {

    //Atributes
    private DBConnection connection;

    //Constructor
    public Multiplayer(Difficulty difficulty){
        //Creates a multiplayer game
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
