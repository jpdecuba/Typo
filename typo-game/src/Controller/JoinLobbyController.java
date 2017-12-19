package Controller;

import Model.Difficulty;
import Model.GameServer.Lobby;
import Model.Multiplayer;
import Model.Shared.Request;
import Model.Sockets.GameClient;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class JoinLobbyController implements Initializable, Observer {

    @FXML
    AnchorPane anchor;
    @FXML
    VBox lobbyBox;
    @FXML
    ScrollPane scrollPane;
    @FXML
    Button BackBtn;
    @FXML
    Label countdownLbl;

    private GameClient GC;
    private List<HBox> lobbies = new ArrayList<>();
    private int index = 0;
    private int remaining = 5;
    private Multiplayer mp;

    @FXML
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == BackBtn) {
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/NewOnlineView.fxml")), "Mode: Multiplayer");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle(" -fx-background-image: url('/space.png')");
        //GameClient gc2 = new GameClient();
        GC = new GameClient();
        GC.gcl.addObserver(this);
        //gc2.CreateLobby(Difficulty.Hard, "abc");

         GC.GetLobbys();
      /*  for (Lobby item : LB) {
            AddLobby(item);
            index++;
        }*/
    }

    public void AddLobby(Lobby lobby) {
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
        if (lobby.getGameDiff() == Difficulty.Easy) {
            diff.setTextFill(Paint.valueOf("BLUE"));
        } else if (lobby.getGameDiff() == Difficulty.Medium) {
            diff.setTextFill(Paint.valueOf("GREEN"));
        } else {
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

    private void difficulty(Difficulty difficulty, String page, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        Parent parent = loader.load();
        if(difficulty != null)
        {
            MultiplayerController controller = loader.getController();
            controller.setSession(mp, GC);
        }

        Main.switchPage(parent, title);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == ArrayList.class) {
            ArrayList<Lobby> lobbas = (ArrayList<Lobby>) arg;
            for (Lobby lobby : lobbas){
                Platform.runLater(()->{
                    AddLobby(lobby);
                    index++;
                });
            }
        }
        else if (arg.getClass() == Integer.class) {
            Platform.runLater(()->{
                Countdown();
            });

        }
        else if (arg.getClass() == Multiplayer.class) {
            try {
                this.mp = (Multiplayer) arg;
                difficulty(mp.getDifficulty(), "/Views/MultiplayerView.fxml", "TYPO Multiplayer - Difficulty: " + mp.getDifficulty());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Countdown(){
        Timeline timeline;
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                countdownLbl.setText("Game will start in " + remaining);
                                remaining--;
                                if (remaining <= 0) {
                                    timeline.stop();

                                }
                            }
                        }));
        timeline.playFromStart();
    }
}
