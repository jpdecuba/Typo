package Model;

import Model.Database.DBSet;
import Model.Database.Database;
import Model.Repository.SetRepository;
import Model.Sockets.GameClient;
import javafx.beans.Observable;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

public class Multiplayer extends Session implements Serializable{

    //Attributes
    private Database connection;
    private DatabaseClient setRepository;
    private GameClient gc;

    private int index = 0;

    //Constructor
    public Multiplayer(Difficulty difficulty){
        setDifficulty(difficulty);
    }

    @Override
    public void AddSets(){
        if(Database.checkConnection()) {
            DatabaseClient setRepository2 = new DatabaseClient(null);
            try {
                sets.addAll(setRepository2.getSet(getDifficulty()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public Set NextSet(Player player){
        if(player != null){
            player.setCombo(combotimer.getCombo(player.getCombo()));
            player.AwardPoints();
            Random r = new Random();
            if(r.nextInt(100) <= 10){
                SpawnOpportunity();
                this.setChanged();
                this.notifyObservers(opp);
            }
        }
        if (!sets.isEmpty()){
            //Random r = new Random();
            currentSet = sets.get(index++);
            StringBuilder s = new StringBuilder();
            for (Letter l: currentSet.getCharacters()) { s.append(l.getCharacter()); }
            lastSet = new Set(s.toString());
            sets.remove(currentSet);
            sets.add(lastSet);
            combotimer.setStartTime(LocalDateTime.now());
            this.setChanged();
            this.notifyObservers("UpdateGame");
            return currentSet;
        }
        else {
            EndGame();
            throw new NullPointerException("there are no more sets available");
        }
    }
}
