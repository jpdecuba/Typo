package Model.Shared;

import Model.Difficulty;
import Model.GameServer.Lobby;
import Model.HighScore;

import java.io.Serializable;

public class Request implements Serializable {
    public Difficulty diff;

    public RequestType msg;

    public HighScore score;
    public Lobby lobby;

    public String LobbyID;

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

}
