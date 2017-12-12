package Controller;

import Model.Difficulty;
import Model.GameServer.Lobby;
import Model.Sockets.GameClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JoinLobbyController implements Initializable {

    @FXML
    AnchorPane anchor;
    @FXML
    VBox lobbyBox;
    @FXML
    ScrollPane scrollPane;

    private GameClient GC;
    private List<Label> messages = new ArrayList<>();
    private int index = 0;

    @FXML
    public void btnClick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameClient gc2 = new GameClient();
        GC = new GameClient();
        //GC.CreateLobby(Difficulty.Easy, "test");
        gc2.CreateLobby(Difficulty.Easy, "abc");

        List<Lobby> LB = GC.GetLobbys();
        for(Lobby item : LB){
            Label mess = new Label(item.getLobbyID());
            mess.setFont(Font.loadFont("file:typo-game/src/Roboto-Medium.ttf", 16));
            mess.setWrapText(true);
            messages.add(mess);
            messages.get(index).setMinWidth(lobbyBox.getWidth());
            messages.get(index).setAlignment(Pos.CENTER_LEFT);
            lobbyBox.getChildren().add(messages.get(index));
            index++;
        }
    }
}
