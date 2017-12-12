package Model.Sockets;

import Model.Difficulty;
import Model.GameServer.Lobby;
import Model.OppName;
import Model.Opportunity;
import Model.Player;
import Model.Shared.Request;
import Model.Shared.RequestType;

import javax.net.SocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.List;

import static Model.Shared.RequestType.*;

public class GameClient {

    ObjectOutputStream output;
    DataInputStream input;
    ObjectInputStream objectInput;
    SocketFactory socketFactory;
    Socket socket;

    public GameClient() {
        OpenSocket();
    }


    public void OpenSocket() {
        try {
            socketFactory = (SocketFactory) SocketFactory.getDefault();
            socket = (Socket) socketFactory.createSocket("localhost", 4444);

            output = new ObjectOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
            objectInput = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String StartGame(Difficulty diff) {

        try {
            Request request = new Request(diff,RequestType.StartGame);
            output.writeObject(request);

            String response = input.readUTF();
            System.out.println(response);
            return response;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public void CreateLobby(Difficulty diff, String lobbyID) {

        try {
            Request request = new Request(CreateLobby,new Lobby(diff,lobbyID));
            output.writeObject(request);

           /* String response = input.readUTF();
            System.out.println(response);*/


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public List<Lobby> GetLobbys () {

        try {
            Request request = new Request(null,GetLobby);
            output.writeObject(request);

            Object response = objectInput.readObject();
            if (response.getClass() == Request.class) {
                Request responseReq = (Request) response;
                return responseReq.lobbys;
            }
            return null;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String JoinLobby(Difficulty diff, Lobby lobby) {
        try {
            Request request = new Request(diff,JoinLobby,lobby);
            output.writeObject(request);

            String response = input.readUTF();
            System.out.println(response);
            return response;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public String UpdateGame(Player player) {
        try {
            Request request = new Request(RequestType.GameUpdate,player);
            output.writeObject(request);

            String response = input.readUTF();
            System.out.println(response);
            return response;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }


    public void CloseSocket() {
        try {
            output.close();
            input.close();
            socket.close();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
