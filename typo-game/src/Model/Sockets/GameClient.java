package Model.Sockets;

import Model.Difficulty;
import Model.OppName;
import Model.Opportunity;
import Model.Player;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameClient {

    public GameClient() {
        int i = 0;
        i++;

        try {
            SocketFactory socketFactory = (SocketFactory) SocketFactory.getDefault();
            Socket socket = (Socket) socketFactory.createSocket("localhost", 4040);

            ObjectOutputStream output;
            DataInputStream input;

            output = new ObjectOutputStream(socket.getOutputStream());
            input = new DataInputStream  (socket.getInputStream());

            SocketModel scktModel1 = new SocketModel(new Player(),null,"Start game");

            output.writeObject(scktModel1);
            String response = input.readUTF();
            System.out.println(response);

            SocketModel scktModel2 = new SocketModel(new Player(),null,null);

            output.writeObject(scktModel2);
            String response2 = input.readUTF();
            System.out.println(response2);

            output.close();
            input.close();
            socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // System.exit(0);
        }
    }


    public static void main(String args[]) {
        new GameClient();
    }
}
