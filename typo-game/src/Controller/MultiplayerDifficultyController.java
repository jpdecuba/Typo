package Controller;

import Model.Difficulty;
import Model.Multiplayer;
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

public class MultiplayerDifficultyController implements Initializable {
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
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == EasyBtn)
        {
            difficulty(Difficulty.Easy, "/Views/MultiplayerView.fxml","TYPO Singleplayer - Difficulty: Easy");
        }
        else if(button == MediumBtn)
        {
            //difficulty(Difficulty.Medium, "/Views/page2.fxml","TYPO Singleplayer - Difficulty: Medium");
        }
        else if(button == HardBtn)
        {
            //difficulty(Difficulty.Hard, "/Views/page2.fxml","TYPO Singleplayer - Difficulty: Hard");
        }
        else
        {
            difficulty(null, "/Views/NewOnlineView.fxml","TYPO");
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
}
