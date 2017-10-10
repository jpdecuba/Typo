package Model;

import Model.Database.DBSet;
import Model.Database.Database;
import Model.Repository.SetRepository;
import Model.Serialize.SetSerialize;

import java.sql.Connection;
import java.util.Observable;

public class Singleplayer extends Session {
    private SetRepository setRepository;
    //Constructor
    public Singleplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    //start the game
    @Override
    public void Start() {
        if(getPlayerOne() != null && getPlayerTwo() == null){
            if(Database.checkConnection()){
                setRepository = new SetRepository(new DBSet());
                try{ sets.addAll(setRepository.GetSets(getDifficulty())); } catch(Exception e){ e.printStackTrace(); }
            }
            else {
                try{ sets.addAll(SetSerialize.GetSets(getDifficulty())); } catch(Exception e){ e.printStackTrace(); }
            }
            getPlayerOne().addObserver(this);
            NextSet(null);
        }
        else{
            throw new NullPointerException("There are not enough players in teh lobby");
        }
    }

    //update when game needs to end
    @Override
    public void update(Observable o, Object arg) {
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
        setChanged(); notifyObservers();
        return false;
    }
}
