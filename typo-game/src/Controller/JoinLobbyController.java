package Controller;

import Model.Sockets.GameClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinLobbyController implements Initializable {
    private GameClient GC;

    @FXML
    public void btnClick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GC = new GameClient();
    }
}
