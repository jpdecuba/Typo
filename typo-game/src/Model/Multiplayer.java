package Model;

import Model.Database.DBSet;
import Model.Database.Database;
import Model.Repository.SetRepository;
import javafx.beans.Observable;

public class Multiplayer extends Session{

    //Attributes
    private Database connection;
    private DatabaseClient setRepository;

    //Constructor
    public Multiplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    //start the game
    @Override
    public void Start(){
        if(getPlayerOne() != null && getPlayerTwo() != null){
            if(Database.checkConnection()){
                setRepository = new DatabaseClient(null);
                try{ sets.addAll(setRepository.getSet(getDifficulty())); } catch(Exception e){ e.printStackTrace(); }
            }
            else {
            }
            getPlayerOne().addObserver(this);
            getPlayerTwo().addObserver(this);
            NextSet(new Player());
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
            throw new IllegalArgumentException("Session got an unexpected update");
        }
    }

    //end the game after update
    @Override
    public boolean EndGame() {
        return false;
    }
}
