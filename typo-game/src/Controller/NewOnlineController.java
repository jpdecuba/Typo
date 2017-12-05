package Controller;

import Model.Difficulty;
import Model.Singleplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewOnlineController implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    AnchorPane anchor;
    @FXML
    Button JoinBtn;
    @FXML
    Button CreateBtn;
    @FXML
    Button BackBtn;

    @FXML
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == JoinBtn)
        {
            NextPage("/Views/JoinLobbyView.fxml","TYPO Join Lobby");
        }
        else if(button == CreateBtn)
        {
            NextPage("/Views/MultiplayerDifficultyView.fxml","TYPO Create Lobby");
        }
        else
        {
            NextPage("/Views/sample.fxml","TYPO");
        }
    }

    private void NextPage(String page, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        Parent parent = loader.load();
        Main.switchPage(parent, title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle(" -fx-background-image: url('/space.png')");
    }
}
