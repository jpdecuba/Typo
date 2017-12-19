package Model.Sockets;


import Model.Shared.Request;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class GameClientThread extends Thread {

    protected Socket socket;

    private ObjectInputStream input;


    private GameClientLogic logic;

    public GameClientThread(ObjectInputStream input, GameClientLogic logic,Socket socket) {
        this.input = input;
        this.logic = logic;
        this.socket = socket;
    }

    public void run() {

        try {

            while (socket.isConnected()) {

                System.out.println("in loop");


                Object msg = (Object) input.readObject();


                if (msg.getClass() == Request.class) {
                    Request item = (Request) msg;
                    logic.Msg(item);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
