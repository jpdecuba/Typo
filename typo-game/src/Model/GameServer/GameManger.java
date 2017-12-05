package Model.GameServer;

import java.net.Socket;
import java.util.*;

public class GameManger {
    protected static List<Lobby> Lobbys = new ArrayList<>();
    protected Map<Socket, String> sockets = new HashMap<>();
    public static List<Lobby> getLobbys() {
        return Lobbys;
    }



    public static void AddLobby(Lobby lobby){
        Lobbys.add(lobby);
    }
}
