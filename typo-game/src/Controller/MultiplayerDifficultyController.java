package Controller;

import Model.Difficulty;
import Model.Multiplayer;
import Model.Singleplayer;
import Model.Sockets.GameClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
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

    private GameClient GC;
    private Difficulty diff;

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
            Multiplayer mp = new Multiplayer(difficulty);
            MultiplayerController controller = loader.getController();
            controller.setSession(mp);
        }

        Main.switchPage(parent, title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle(" -fx-background-image: url('/space.png')");
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("test");
        if (arg.getClass() == Integer.class) {
            Platform.runLater(()->{
                lblName.setText("Someone has joined: " + (int)arg);
            });

        }
        //difficulty(diff, "/Views/MultiplayerView.fxml", "TYPO Multiplayer - Difficulty: " + diff);
    }
}
