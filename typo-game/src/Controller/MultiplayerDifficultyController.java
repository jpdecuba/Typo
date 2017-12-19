package Controller;

import Model.Difficulty;
import Model.Multiplayer;
import Model.Singleplayer;
import Model.Sockets.GameClient;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MultiplayerDifficultyController implements Initializable, Observer {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    AnchorPane anchor;
    @FXML
    Button EasyBtn;
    @FXML
    Button MediumBtn;
    @FXML
    Button HardBtn;
    @FXML
    Button BackBtn;
    @FXML
    TextField textField;
    @FXML
    Label lblName;
    @FXML
    Label countdownLbl;

    private GameClient GC;
    private Difficulty diff;
    private int remaining = 5;
    private Multiplayer mp;

    @FXML
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == EasyBtn)
        {
            createLobby(Difficulty.Easy);
        }
        else if(button == MediumBtn)
        {
            createLobby(Difficulty.Medium);
        }
        else if(button == HardBtn)
        {
            createLobby(Difficulty.Hard);
        }
        else
        {
            if (GC == null) {
                difficulty(null, "/Views/NewOnlineView.fxml", "TYPO");
            }
            else{
                GC.RemoveLobby();
                difficulty(null, "/Views/NewOnlineView.fxml", "TYPO");

            }
        }
    }

    public void createLobby(Difficulty difficulty){
        this.diff = difficulty;
        if (textField.getText() == null || textField.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Please add a name for the lobby!");
            alert.showAndWait();
        }
        else if (GC != null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Lobby already created, please wait until someone joins your lobby.");
            alert.showAndWait();
        }
        else {
            GC = new GameClient();
            GC.gcl.addObserver(this);
            GC.CreateLobby(difficulty, textField.getText());
            lblName.setText("Waiting for opponent to join");
        }
    }

    private void difficulty(Difficulty difficulty, String page, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        Parent parent = loader.load();
        if(difficulty != null)
        {
            mp.setDifficulty(diff);
            MultiplayerController controller = loader.getController();
            controller.setSession(mp, GC);
        }

        Main.switchPage(parent, title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle(" -fx-background-image: url('/space.png')");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Integer.class) {
            Platform.runLater(()->{
                lblName.setText("Someone has joined: " + (int)arg);
                Countdown();
            });

        }
        else if (arg.getClass() == Multiplayer.class) {
            try {
                this.mp = (Multiplayer) arg;
                difficulty(diff, "/Views/MultiplayerView.fxml", "TYPO Multiplayer - Difficulty: " + diff);
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
                                    GC.StartGame(diff);
                                }
                            }
                        }));
        timeline.playFromStart();
    }
}
