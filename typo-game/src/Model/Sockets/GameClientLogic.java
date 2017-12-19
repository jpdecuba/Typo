package Model.Sockets;

import Model.Multiplayer;
import Model.Player;
import Model.Shared.Request;

import java.io.ObjectOutputStream;
import java.util.Observable;

public class GameClientLogic extends Observable {


    private ObjectOutputStream output;

    private Multiplayer game;

    private Player player;
    private int LobbyUsers;


    public GameClientLogic(ObjectOutputStream output) {
        this.output = output;
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

        }

    }
}
