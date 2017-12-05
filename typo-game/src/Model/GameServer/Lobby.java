package Model.GameServer;

import Model.Difficulty;
import Model.Multiplayer;
import Model.Session;
import com.sun.scenario.effect.impl.prism.PrImage;

import java.io.Serializable;
import java.net.Socket;

public class Lobby  implements Serializable{

    private Socket player1;
    private Socket player2;
    private Difficulty gameDiff;

    private String LobbyID;

    protected Multiplayer sessie;

    public Lobby(Socket player1, Difficulty gameDiff, String lobbyID) {
        this.player1 = player1;
        this.gameDiff = gameDiff;
        LobbyID = lobbyID;
    }

    public Socket getPlayer2() {
        return player2;
    }
    public Socket getPlayer() {
        return player2;
    }

    public void setPlayer2(Socket player2) {
        this.player2 = player2;

    }

    public Difficulty getGame() {
        return gameDiff;
    }



    public String getLobbyID() {
        return LobbyID;
    }

    public void setLobbyID(String lobbyID) {
        LobbyID = lobbyID;
    }


    public Multiplayer StartGame() {
        Multiplayer item = new Multiplayer(gameDiff);
        sessie = item;

        return item;

    }





}
