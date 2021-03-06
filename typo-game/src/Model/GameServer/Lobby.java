package Model.GameServer;

import Model.Difficulty;
import Model.Multiplayer;
import Model.Session;
import com.sun.scenario.effect.impl.prism.PrImage;

import java.io.Serializable;
import java.net.Socket;

public class Lobby implements Serializable {


    private Difficulty gameDiff;
    private boolean full = false;

    protected String LobbyID;

    protected Multiplayer sessie;

    public Lobby(Difficulty gameDiff, String lobbyID) {
        this.gameDiff = gameDiff;
        LobbyID = lobbyID;
    }


    public Difficulty getGame() {
        return gameDiff;
    }

    public Difficulty getGameDiff() {
        return gameDiff;
    }

    public String getLobbyID() {
        return LobbyID;
    }

    public void setLobbyID(String lobbyID) {
        LobbyID = lobbyID;
    }

    public boolean getFull() {
        return full;
    }

    public void setFull() {
        full = true;
    }


    public Multiplayer StartGame() {
        Multiplayer item = new Multiplayer(gameDiff);
        sessie = item;
        return item;

    }


}
