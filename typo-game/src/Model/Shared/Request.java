package Model.Shared;

import Model.Difficulty;
import Model.GameServer.Lobby;
import Model.HighScore;
import Model.Multiplayer;
import Model.Player;

import java.io.Serializable;
import java.util.List;


public class Request implements Serializable {
    public Difficulty diff;

    public RequestType msg;

    public HighScore score;
    public Lobby lobby;

    public String LobbyID;

    public Player player;

    public List<Lobby> lobbys;

    public Request(Difficulty diff, RequestType msg) {
        this.diff = diff;
        this.msg = msg;
    }


    public Request(RequestType msg,Lobby lobby) {
        this.msg = msg;
        this.lobby = lobby;
    }

    public Request(RequestType msg,Lobby lobby, String LobbyID) {
        this.msg = msg;
        this.lobby = lobby;
        this.LobbyID = LobbyID;
    }
    public Request(RequestType msg, String LobbyID) {
        this.msg = msg;
        this.LobbyID = LobbyID;
    }



    public Request(Difficulty diff, RequestType msg, HighScore score) {
        this.diff = diff;
        this.msg = msg;
        this.score = score;
    }

    public Request(RequestType msg,Player player,String lobbyID) {
        this.msg = msg;
        this.player = player;
        this.LobbyID = lobbyID;
    }

    public Request(RequestType msg,Player player) {
        this.msg = msg;
        this.player = player;
    }


    public Request(RequestType msg, List<Lobby> lobbys) {
        this.msg = msg;
        this.lobbys = lobbys;
    }

}
