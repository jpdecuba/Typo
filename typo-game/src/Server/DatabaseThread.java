package Server;

import Model.Shared.Request;
import Model.Sockets.SocketModel;

import java.io.*;
import java.net.Socket;

public class DatabaseThread extends Thread{
    protected Socket socket;

    private DBServer DB;

    public DatabaseThread(Socket clientSocket) {
        this.socket =  clientSocket;

    }



    public void run() {


        try {
            System.err.println("Connecting started");
            // Data streams
            ObjectInputStream input;
            ObjectOutputStream output;


            output = new ObjectOutputStream (socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());



            this.DB = new DBServer(socket,output);

            while (socket.isConnected()) {

                System.out.println("in loop");

                Object msg = input.readObject();
                //String password = input.readLine();
                if(msg.getClass() == Request.class){

                    Request item = (Request) msg;
                    DB.DBGet(item);

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
