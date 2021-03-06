package Model.GameServer;

import Model.Shared.Request;
import Server.DBServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class GameServerThread extends Thread{
    protected Socket socket;
    private GameLogic GL;

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


            this.GL = new GameLogic(socket,output);

            while (socket.isConnected()) {

                System.out.println("Wait for next message");

                Object msg = (Object) input.readObject();
                //String password = input.readLine();
                if(msg.getClass() == Request.class){

                    Request item = (Request) msg;
                    this.GL.MSG(item);

                }else {
                    System.out.println("No valid request");
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

        } catch(SocketException e){
            System.out.println("Socket exception");

        }
        catch (IOException ioException) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Socket closed error");
            }
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found error");
        }
        finally {
            //If client disconnect to remove there name out the names list and there write outputstream
            try {
                GL.RemoveLobby();
                socket.close();

            } catch (IOException e) {
            }

        }
    }
}
