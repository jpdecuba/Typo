package Model.GameServer;

import Model.Multiplayer;
import Model.Publisher.Publisher;
import Model.Shared.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.util.List;

public class GameLogic {

    private Socket Socket;

    private ObjectOutputStream output;

    private GameManger manger;

    private Lobby lobby;

    public GameLogic(Socket socket, ObjectOutputStream output) {
        Socket = socket;
        this.output = output;
    }



    public void MSG(Request request){
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
                Lobby item = new Lobby(Socket,request.diff,request.LobbyID);
                JoinLobby(item, Socket);
                break;
        }

    }


    public void CreateLobby(Lobby lobby){
        manger.AddLobby(lobby);
        this.lobby = lobby;
    }

    public void GetLobbys(){
       List<Lobby> lobbys =  manger.getLobbys();
        try {
            output.writeObject(lobbys);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void JoinLobby(Lobby lobby, Socket socket){
        List<Lobby> lobbys =  manger.getLobbys();


        for(Lobby item : lobbys){
            if(item.getGame() == lobby.getGame()){
                item.setPlayer2(socket);
                this.lobby = item;
            }
        }


        try {
            output.writeObject(lobbys);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StartGame(){

        List<Lobby> lobbys =  manger.getLobbys();
        for(Lobby item : lobbys ){
            if(item.getGame() == lobby.getGame()){
                Multiplayer object = item.StartGame();
                try {
                    output.writeObject(object);

                    ObjectOutputStream output2 = new ObjectOutputStream(item.getPlayer2().getOutputStream());
                    output2.writeObject(object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
