package Model.Sockets;

import Model.GameServer.Lobby;
import Model.Multiplayer;
import Model.Opportunity;
import Model.Player;
import Model.Shared.Request;

import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Observable;

public class GameClientLogic extends Observable {


    private ObjectOutputStream output;

    private Multiplayer game;

    private Player player;
    private int LobbyUsers;

    private List<Lobby> lobbys;

    private Opportunity opp;


    public GameClientLogic() {

    }

    public void Msg(Request request){


        switch (request.msg){
            case GameUpdate:
                player = request.player;
                setChanged();
                notifyObservers(player);
                break;
            case ServergameStart:
                game = request.sessie;
                setChanged();
                notifyObservers(game);
                break;
            case LobbyJoined:
                LobbyUsers = request.LobbyUsers;
                setChanged();
                notifyObservers(LobbyUsers);
                break;
            case SendLobby:
                lobbys = request.lobbys;
                setChanged();
                notifyObservers(lobbys);
                break;
            case OppertunityActive:
                opp = request.opp;
                setChanged();
                notifyObservers(opp);
                break;

        }

    }
}
