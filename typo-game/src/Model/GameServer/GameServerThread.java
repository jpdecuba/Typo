package Model.GameServer;

import Model.Shared.Request;
import Server.DBServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameServerThread extends Thread{
    protected Socket socket;

    public GameServerThread(Socket clientSocket) {
        this.socket =  clientSocket;
    }

    public void run() {

        try {
            System.err.println("Connecting started");
            // Data streams
            ObjectInputStream input;
            ObjectOutputStream output;



            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream (socket.getOutputStream());


            //this.DB = new DBServer(socket,output);

            while (socket.isConnected()) {

                //System.out.println("in loop");

                Object msg = (Object) input.readObject();
                //String password = input.readLine();
                if(msg.getClass() == Request.class){

                    Request item = (Request) msg;
                    //DB.DBGet(item);

                }else {

                    output.writeUTF("No valid request");


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