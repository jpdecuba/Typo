package Model.Sockets;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private Socket socket = null;

    public GameServer() throws Exception {
        ServerSocketFactory socketFactory = (ServerSocketFactory) ServerSocketFactory.getDefault();
        serverSocket = (ServerSocket) socketFactory.createServerSocket(4040);
    }

    private void runServer() {
        System.err.println("Waiting for connections...");

        while (true) {
            try {
                System.out.println("wait");
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new GameServerThread(socket).start();
        }
    }

    public static void main(String args[]) throws Exception {
        GameServer server = new GameServer();
        server.runServer();
    }
}