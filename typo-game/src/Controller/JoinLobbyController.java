package Controller;

import Model.Difficulty;
import Model.GameServer.Lobby;
import Model.Sockets.GameClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JoinLobbyController implements Initializable {
    private GameClient GC;

    @FXML
    public void btnClick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameClient gc2 = new GameClient();
        GC = new GameClient();
        GC.CreateLobby(Difficulty.Easy, "test");
        gc2.CreateLobby(Difficulty.Easy, "abc");
        List<Lobby> LB = GC.GetLobbys();
        for(Lobby item : LB){
            System.out.println(item.getLobbyID());
        }
    }
}
