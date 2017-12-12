package Controller;

import Model.Difficulty;
import Model.GameServer.Lobby;
import Model.Sockets.GameClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
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
    private List<HBox> lobbies = new ArrayList<>();
    private int index = 0;

    @FXML
    public void btnClick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameClient gc2 = new GameClient();
        GC = new GameClient();
        gc2.CreateLobby(Difficulty.Hard, "abc");

        List<Lobby> LB = GC.GetLobbys();
        for(Lobby item : LB){
            AddLobby(item);
            index++;
        }
    }

    public void AddLobby(Lobby lobby){
        Label mess = new Label(lobby.getLobbyID());
        Label diff = new Label(lobby.getGameDiff().toString());
        mess.setFont(Font.loadFont("file:typo-game/src/Roboto-Medium.ttf", 16));
        mess.setWrapText(true);
        Button btn = new Button();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GC.JoinLobby(lobby.getGameDiff(), lobby);
            }
        });
        if (lobby.getGameDiff() == Difficulty.Easy){
            diff.setTextFill(Paint.valueOf("BLUE"));
        }
        else if (lobby.getGameDiff() == Difficulty.Medium){
            diff.setTextFill(Paint.valueOf("GREEN"));
        }
        else{
            diff.setTextFill(Paint.valueOf("RED"));
        }
        btn.setStyle("-fx-background-color: black; -fx-background-radius: 15");
        btn.setTextFill(Paint.valueOf("b0c4de"));
        btn.setText("Join");
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        HBox hbox = new HBox(mess, diff, region1, btn);
        hbox.setPrefWidth(lobbyBox.getWidth());
        lobbies.add(hbox);
        lobbies.get(index).setMinWidth(lobbyBox.getWidth());
        lobbies.get(index).setAlignment(Pos.CENTER_LEFT);
        lobbyBox.getChildren().add(lobbies.get(index));
    }
}
