package Model;

import Model.Database.DBSet;
import Model.Repository.SetRepository;

import java.sql.Connection;
import java.util.Observable;

public class Singleplayer extends Session {
    private SetRepository setRepository = new SetRepository(new DBSet());
    //Constructor
    public Singleplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    //start the game
    @Override
    public void Start() {
        if(getPlayerOne() != null && getPlayerTwo() == null){
            try{ sets.addAll(setRepository.GetSets(getDifficulty())); } catch(Exception e){ e.printStackTrace(); }
            getPlayerOne().addObserver(this);
            NextSet(null);
        }
        else{
            throw new NullPointerException("There are not enough players in teh lobby");
        }

        /*
        if(players.size() == 1){
            for(Player player: players){
                player.addObserver(this);
            }
        }
        else{
            throw new NullPointerException("There are not enough players in the lobby");
        }
        */
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
        return false;
    }
}
