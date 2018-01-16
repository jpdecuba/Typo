package Model.Shared;

import Model.*;
import Model.GameServer.Lobby;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    public Difficulty diff;
    public RequestType msg;
    public HighScore score;
    public Lobby lobby;
    public String LobbyID;
    public PlayerData player;
    public List<Lobby> lobbys;
    public Multiplayer sessie;
    public int LobbyUsers;
    public Opportunity opp;

    public Request(RequestType msg) {
        this.msg = msg;
    }

    public Request(RequestType msg, int users) {
        this.msg = msg;
        this.LobbyUsers = users;
    }

    public Request(RequestType msg, Opportunity opp) {
        this.msg = msg;
        this.opp = opp;
    }

    public Request(Difficulty diff, RequestType msg) {
        this.diff = diff;
        this.msg = msg;
    }

    public Request(Difficulty diff, RequestType msg, Lobby lobby) {
        this.diff = diff;
        this.msg = msg;
        this.lobby = lobby;
    }

    //request for sending lobby
    public Request(RequestType msg, Lobby lobby) {
        this.msg = msg;
        this.lobby = lobby;
    }

    //Request for sending diffculty and highscore
    public Request(Difficulty diff, RequestType msg, HighScore score) {
        this.diff = diff;
        this.msg = msg;
        this.score = score;
    }
    //Request for sending player object en lobbyID
    public Request(RequestType msg, PlayerData player, String lobbyID) {
        this.msg = msg;
        this.player = player;
        this.LobbyID = lobbyID;
    }
    //Request for sending player
    public Request(RequestType msg, PlayerData player) {
        this.msg = msg;
        this.player = player;
    }

    //Request for sending only LobbyID
    public Request(RequestType msg, String LobbyID) {
        this.msg = msg;
        this.LobbyID = LobbyID;
    }

    //Request for sending lobbys
    public Request(RequestType msg, List<Lobby> lobbys) {
        this.msg = msg;
        this.lobbys = lobbys;
    }

    //Request for multiplayer object
    public Request(RequestType msg, Multiplayer sessie) {
        this.msg = msg;
        this.sessie = sessie;
    }
}
