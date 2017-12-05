package Model.Sockets;

import Model.Difficulty;
import Model.OppName;
import Model.Opportunity;
import Model.Player;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class GameServerThread extends Thread{
    protected Socket socket;

    public GameServerThread(Socket clientSocket) {
        this.socket =  clientSocket;
    }

    public void run() {


        try {
            System.err.println("Connecting started");
            //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            ObjectInputStream input;
            DataOutputStream output;

            input = new ObjectInputStream(socket.getInputStream());
            output = new DataOutputStream (socket.getOutputStream());

            while (socket.isConnected()) {

                System.out.println("in loop");

                SocketModel scktModel = (SocketModel) input.readObject();
                //String password = input.readLine();

                System.out.println(scktModel.toString());

                if (scktModel.getCmd() != null) {
                    System.out.println(scktModel.getCmd());
                    output.writeUTF("Command: " + scktModel.getCmd());

                } else {
                    output.writeUTF("Command did not arrive.");
                }
                if (socket.isClosed()) {
                    output.close();
                    input.close();

                }


            }
            output.close();
            input.close();




            socket.close();

        } catch (IOException ioException) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
