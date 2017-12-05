package Model.GameServer;

import java.util.ArrayList;
import java.util.List;

public class GameManger {
    protected static List<Lobby> Lobbys = new ArrayList<>();

    public static List<Lobby> getLobbys() {
        return Lobbys;
    }

    public static void AddLobby(Lobby lobby){
        Lobbys.add(lobby);
    }
}
