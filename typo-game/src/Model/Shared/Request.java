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

        public Multiplayer sessie;

        public Request(Difficulty diff, RequestType msg) {
            this.diff = diff;
            this.msg = msg;
        }

        //request for sending lobby
        public Request(RequestType msg,Lobby lobby) {
            this.msg = msg;
            this.lobby = lobby;
        }
        //Request for sending lobby + LobbyID
        public Request(RequestType msg,Lobby lobby, String LobbyID) {
            this.msg = msg;
            this.lobby = lobby;
            this.LobbyID = LobbyID;
        }
        //Request for sending only LobbyID
        public Request(RequestType msg, String LobbyID) {
            this.msg = msg;
            this.LobbyID = LobbyID;
        }


        //Request for sending diffculty and highscore
        public Request(Difficulty diff, RequestType msg, HighScore score) {
            this.diff = diff;
            this.msg = msg;
            this.score = score;
        }
        //Request for sending player object en lobbyID
        public Request(RequestType msg,Player player,String lobbyID) {
            this.msg = msg;
            this.player = player;
            this.LobbyID = lobbyID;
        }
        //Request for sending player
        public Request(RequestType msg, Player player) {
            this.msg = msg;
            this.player = player;
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
