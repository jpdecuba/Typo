package Controller;

import Model.Session;
import javafx.animation.AnimationTimer;
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
import javafx.scene.layout.*;
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
    private Image rocket = new Image("/rocket.gif");
    private Image logo = new Image("/Logo Wit.png");
    @FXML
    AnchorPane anchor;

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
    ImageView Rocket;

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
        anchor.setStyle(" -fx-background-image: url('/space.png')");
        Rocket.setFitHeight(120);
        Rocket.setFitWidth(120);
        Logo.setFitHeight(120);
        Logo.setFitWidth(120);
        Rocket.setRotate(90);
        Rocket.setImage(rocket);
        Logo.setImage(logo);
        AnimationTimer aT = new AnimationTimer() {
            int X = -120;
            @Override
            public void handle(long now) {
                if(Rocket.getX() >= screenSize.getWidth()){
                    X = -120;
                }
                Rocket.setX(X);
                X += 1;
            }
        };
        aT.start();
    }

}
