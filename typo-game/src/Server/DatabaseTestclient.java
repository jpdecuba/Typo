package Server;

import Model.*;
import Model.Database.Database;
import Model.Shared.Request;
import Model.Shared.RequestType;
import Model.Sockets.GameClient;
import Model.Sockets.SocketModel;

import javax.net.SocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class DatabaseTestclient  {

    DatabaseTestclient() {
        try {
            SocketFactory socketFactory = (SocketFactory) SocketFactory.getDefault();
            Socket socket = (Socket) socketFactory.createSocket("localhost", 8700);

            ObjectOutputStream output;
            ObjectInputStream input;
            BufferedOutputStream socketwrite = new BufferedOutputStream(socket.getOutputStream());
            output = new ObjectOutputStream(socket.getOutputStream());



            Request item2 = new Request(Difficulty.Beginner, RequestType.Sets);
            Request item = new Request(Difficulty.Beginner, RequestType.HighScore);


            output.writeObject(item);

            //input = new ObjectInputStream(socket.getInputStream());
            BufferedInputStream socketRead = new BufferedInputStream(socket.getInputStream());
            input = new ObjectInputStream(socketRead);
            Object response = input.readObject();

            List<HighScore> list = (List<HighScore>) response;
            System.out.println(list.size());
            System.out.println(list.get(0).getScore());
            input.close();





            output.writeObject(item2);
            //output.flush();
            ObjectInputStream input2 = new ObjectInputStream(socketRead);
            Object response2 = input2.readObject();
            List<Set> list2 = (List<Set>) response2;
            System.out.println(list2.size());
            //System.out.println(list2.get(0).getCharacters().toString());



            output.close();
            input.close();
            socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // System.exit(0);
        }

    }


    public static void main(String args[]) {
        //new DatabaseTestclient();


        DatabaseClient client = new DatabaseClient(null);

        List<HighScore> item = client.getHighScore();
        System.out.println(item.size());


        List<Set> item2 = client.getSet(Difficulty.Expert);
        System.out.println(item2.size());

    }
}
