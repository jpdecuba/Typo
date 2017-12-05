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

    public static void main(String args[]) {
        new GameClient();
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

    public String CreateLobby(Difficulty diff, String lobbyID) {

        try {
            Request request = new Request(CreateLobby,new Lobby(socket,diff,lobbyID));
            output.writeObject(request);

            String response = input.readUTF();
            System.out.println(response);
            return response;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public List<Lobby> GetLobby (Difficulty diff, String lobbyID) {

        try {
            Request request = new Request(GetLobby,lobbyID);
            output.writeObject(request);

            Object response = objectInput.readObject();
            if (response.getClass() == Request.class) {
                Request responseReq = (Request) response;
                return request.lobbys;
            }
            return null;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
