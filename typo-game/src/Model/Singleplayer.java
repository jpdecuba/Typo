package Model;

import Model.Database.DBSet;
import Model.Database.Database;
import Model.Repository.SetRepository;
import Model.SaveProps.SetSerialize;

import java.util.Observable;

public class Singleplayer extends Session {
    private DatabaseClient setRepository;
    //Constructor
    public Singleplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    //start the game
    @Override
    public void Start() {
        if(getPlayerOne() != null && getPlayerTwo() == null){
            if(Database.checkConnection()){
                setRepository = new DatabaseClient(null);
                try{ sets.addAll(setRepository.getSet(getDifficulty())); } catch(Exception e){ e.printStackTrace(); }
            }
            else {
                try{ sets.addAll(SetSerialize.GetSets(getDifficulty())); } catch(Exception e){ e.printStackTrace(); }
            }
            getPlayerOne().addObserver(this);
            NextSet(new Player());
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
        else if((boolean)arg){
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
