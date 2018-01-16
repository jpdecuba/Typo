package Server;

import Model.Sockets.GameServerThread;
import sun.nio.ch.ThreadPool;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseServer {
    private ServerSocket serverSocket;
    private Socket socket = null;


    private ExecutorService executor = Executors.newCachedThreadPool();

    public DatabaseServer() throws Exception {
        ServerSocketFactory socketFactory = (ServerSocketFactory) ServerSocketFactory.getDefault();
        serverSocket = (ServerSocket) socketFactory.createServerSocket(7676);
    }

    private void runServer() {
        System.err.println("Waiting for connections...");

        while (true) {
            try {
                System.out.println("waiting for client...");
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            executor.submit(new DatabaseThread(socket));
        }
    }

    public static void main(String args[]) throws Exception {
        DatabaseServer server = new DatabaseServer();
        server.runServer();
    }
}
