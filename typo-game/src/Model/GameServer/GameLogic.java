package Model.GameServer;

import Model.Multiplayer;
import Model.Player;
import Model.Shared.Request;
import Model.Shared.RequestType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import static Model.Shared.RequestType.GameUpdate;
import static Model.Shared.RequestType.ServergameStart;

public class GameLogic {

    private Socket Socket;

    private ObjectOutputStream output;

    private GameManager manger;

    private Lobby lobby;

    public GameLogic(Socket socket, ObjectOutputStream output) {
        Socket = socket;
        this.output = output;
    }



    public void MSG(Request request){
        System.out.println(request.msg);
        switch (request.msg){
            case GetLobby:
                GetLobbys();
                break;
            case CreateLobby:
                CreateLobby(request.lobby);
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
            default:
                System.out.println("Request not found...");

        }

    }


    public void CreateLobby(Lobby lobby){
        System.out.println(manger.getLobbys().size());

        List<Lobby> lobbys =  manger.getLobbys();


        for(Lobby item : lobbys){
            if(item.getGame() == lobby.getGame()){
                lobby.LobbyID = lobby.LobbyID + String.valueOf(lobbys.size());
            }
        }
        manger.AddLobby(lobby);
        manger.sockets.put(Socket,lobby.LobbyID);
        this.lobby = lobby;
        System.out.println(manger.getLobbys().size());
    }

    public void GetLobbys(){
       List<Lobby> lobbys =  manger.getLobbys();
        Request request = new Request(RequestType.SendLobby, lobbys);
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void JoinLobby(Lobby lobby, Socket socket){


        List<Lobby> lobbys =  manger.getLobbys();


        for(Lobby item : lobbys){
            if(item.getGame() == lobby.getGame()){
                manger.sockets.put(Socket,lobby.LobbyID);
                this.lobby = item;
            }
        }


/*        try {
            output.writeObject(lobbys);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void StartGame(){

        List<Lobby> lobbys =  manger.getLobbys();
        for(Lobby item : lobbys ){
            if(item.getGame() == lobby.getGame()){
                Multiplayer object = item.StartGame();
                try {

                    Request req = new Request(ServergameStart,object);

                    Map<Socket,String> list = manger.sockets;

                    if(list.containsValue(lobby.LobbyID))
                    {
                        for(Map.Entry<Socket, String> entry : list.entrySet()) {
                            Socket key = entry.getKey();
                            String value = entry.getValue();
                            if(value == lobby.LobbyID){
                                ObjectOutputStream ouput1 = new ObjectOutputStream(key.getOutputStream());
                                ouput1.writeObject(req);
                                ouput1.flush();
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
            GameManager.sockets.remove(Socket);
        }


    }


    public void LeaveLobby() {

        //If client disconnect to remove there name out the names list and there write outputstream
        if (Socket != null) {
            GameManager.sockets.remove(Socket);
        }


    }

    public void UpdateGame(Player player){
        try {
            Request request = new Request(GameUpdate,player);
            Map<Socket,String> list = manger.sockets;

            if(list.containsValue(lobby.LobbyID))
            {
                for(Map.Entry<Socket, String> entry : list.entrySet()) {
                    Socket key = entry.getKey();
                    String value = entry.getValue();
                    if(value == lobby.LobbyID){
                        ObjectOutputStream ouput1 = new ObjectOutputStream(key.getOutputStream());
                        ouput1.writeObject(request);
                        ouput1.flush();
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
