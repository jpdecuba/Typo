package Controller;

import Model.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    private Image img = new Image("/Y.png");
    @FXML
    Button SingleplayerBtn;

    @FXML
    Button MultiplayerBtn;

    @FXML
    Button HighscoreBtn;

    @FXML
    Button SettingsBtn;

    @FXML
    Button ExitBtn;

    @FXML
    ImageView Logo;

    @FXML
    public void btnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        if (button == SingleplayerBtn){
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/DifficultyView.fxml")), "Mode: Singleplayer");
        }
        else if(button == MultiplayerBtn){
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/DifficultyView.fxml")), "Mode: Multiplayer");
        }
        else if(button == HighscoreBtn){
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/HighScoreView.fxml")), "High Score");
        }
        else if(button == SettingsBtn){
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/settings.fxml")), "Settings");
        }
        else {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Logo.setImage(img);
        Logo.setFitHeight(70);
        Logo.setFitWidth(70);
    }


}
