package Model.Sockets;


import Model.Shared.Request;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class GameClientThread extends Thread {

    protected Socket socket;

    private ObjectInputStream input;
    private DataOutputStream output;

    public GameClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            while (socket.isConnected()) {

                System.out.println("in loop");

                output = new DataOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());


                Object msg = (Object) input.readObject();


                if (msg.getClass() == Request.class) {
                    Request item = (Request) msg;


                }


            }


            output.close();
            input.close();


            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
