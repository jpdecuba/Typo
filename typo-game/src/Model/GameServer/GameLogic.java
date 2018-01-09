package Model.GameServer;

import Model.Multiplayer;
import Model.Opportunity;
import Model.Player;
import Model.PlayerData;
import Model.Shared.Request;
import Model.Shared.RequestType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import static Model.Shared.RequestType.*;

public class GameLogic {

    private Socket Socket;

    private ObjectOutputStream output;

    private GameManager manger;

    private Lobby lobby;

    public GameLogic(Socket socket, ObjectOutputStream output) {
        Socket = socket;
        this.output = output;
    }


    public void MSG(Request request) {
        System.out.println(request.msg);
        switch (request.msg) {
            case GetLobby:
                GetLobbys();
                break;
            case CreateLobby:
                CreateLobby(request.lobby);
                break;
            case StartGame:
                StartGame();
                break;
            case JoinLobby:
                JoinLobby(request.lobby, Socket);
                break;
            case GameUpdate:
                UpdateGame(request.player);
                break;
            case LeaveLobby:
                LeaveLobby();
                break;
            case RemoveLobby:
                RemoveLobby();
            case LobbyJoined:
                UsersJoined();
            case OppertunityActive:
                Opportuntysend(request.opp);
                break;
            default:
                System.out.println("Request not found...");

        }

    }


    public void Opportuntysend(Opportunity opp) {


        try {
            Request request = new Request(OppertunityActive, opp);
            Map<ObjectOutputStream, String> list = manger.sockets;

            if (list.containsValue(lobby.LobbyID)) {
                for (Map.Entry<ObjectOutputStream, String> entry : list.entrySet()) {
                    ObjectOutputStream key = entry.getKey();
                    String value = entry.getValue();
                    if (value.equals(lobby.getLobbyID())) {

                        key.writeObject(request);
                        key.flush();
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void CreateLobby(Lobby lobby) {
        System.out.println(manger.getLobbys().size());

        List<Lobby> lobbys = manger.getLobbys();


        for (Lobby item : lobbys) {
            if (item.getGame() == lobby.getGame()) {
                lobby.LobbyID = lobby.LobbyID + String.valueOf(lobbys.size());
            }
        }
        manger.AddLobby(lobby);
        manger.sockets.put(output, lobby.LobbyID);

        this.lobby = lobby;
        System.out.println(manger.getLobbys().size());
    }

    public void GetLobbys() {
        List<Lobby> lobbys = manger.getLobbys();
        Request request = new Request(RequestType.SendLobby, lobbys);
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void JoinLobby(Lobby lobby, Socket socket) {


        List<Lobby> lobbys = manger.getLobbys();


        for (Lobby item : lobbys) {
            if (item.LobbyID.equals(lobby.getLobbyID())) {
                manger.sockets.put(output, lobby.LobbyID);
                this.lobby = item;
                UsersJoined();
                break;

            }
        }


/*        try {
            output.writeObject(lobbys);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public synchronized void UsersJoined() {

        try {


            int i = 0;
            for (Lobby item : GameManager.Lobbys) {
                if (item.LobbyID.equals(lobby.getLobbyID())) {


                    for (Map.Entry<ObjectOutputStream, String> entry : manger.sockets.entrySet()) {
                        ObjectOutputStream key = entry.getKey();
                        String value = entry.getValue();
                        System.out.println(value);
                        if (value.equals(lobby.LobbyID)) {
                            i++;
                        }
                    }

                    System.out.println("aantal gebruikers in list " + i + " list size " + manger.sockets.size());

                    Request req = new Request(LobbyJoined, i);

                    for (Map.Entry<ObjectOutputStream, String> entry : manger.sockets.entrySet()) {
                        ObjectOutputStream key = entry.getKey();
                        String value = entry.getValue();
                        if (value == lobby.LobbyID) {

                            key.writeObject(req);
                            key.flush();
                        }

                    }
                    output.writeObject(req);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void StartGame() {

        List<Lobby> lobbys = manger.getLobbys();
        for (Lobby item : lobbys) {
            if (item.LobbyID.equals(lobby.getLobbyID())) {
                Multiplayer object = item.StartGame();
                try {

                    object.AddSets();

                    Request req = new Request(ServergameStart, object);

                    Map<ObjectOutputStream, String> list = manger.sockets;

                    if (list.containsValue(lobby.LobbyID)) {
                        for (Map.Entry<ObjectOutputStream, String> entry : list.entrySet()) {
                            ObjectOutputStream key = entry.getKey();
                            String value = entry.getValue();
                            if (value.equals(lobby.getLobbyID())) {

                                key.writeObject(req);
                                key.flush();
                            }

                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void RemoveLobby() {

        //If client disconnect to remove there name out the names list and there write outputstream
        if (lobby != null) {
            GameManager.Lobbys.remove(lobby);

        }
        if (Socket != null) {
            GameManager.sockets.remove(output);
        }
        lobby = null;


    }


    public void LeaveLobby() {

        //If client disconnect to remove there name out the names list and there write outputstream
        if (Socket != null) {
            GameManager.sockets.remove(output);
        }
        lobby = null;

    }

    public void UpdateGame(PlayerData player) {
        try {
            Request request = new Request(GameUpdate, player);
            Map<ObjectOutputStream, String> list = manger.sockets;

            if (list.containsValue(lobby.LobbyID)) {
                for (Map.Entry<ObjectOutputStream, String> entry : list.entrySet()) {
                    ObjectOutputStream key = entry.getKey();
                    String value = entry.getValue();
                    if (key.equals(output)) {
                        if (value.equals(lobby.getLobbyID())) {
                            key.writeObject(request);
                            key.flush();
                        }
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
