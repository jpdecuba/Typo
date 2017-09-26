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
        if(players.size() > 1){
            for (Player player: players){

            }

        }
        else{
            throw new NullPointerException("There are not enough players in the lobby");
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if(!(boolean)arg){
            EndGame();
        }
        else{
            throw new IllegalArgumentException("Session got an unexcpected update");
        }
    }

    @Override
    public void EndGame() {
        //end the game after update
    }
}
