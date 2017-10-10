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
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Button SingleplayerBtn;

    @FXML
    Button SettingsBtn;

    @FXML
    Button ExitBtn;

    @FXML
    public void test(ActionEvent event) throws IOException {
        Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/DifficultyView.fxml")), "Mode: Singleplayer");
    }

    @FXML
    public void setting(ActionEvent event) throws IOException {
        Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/settings.fxml")), "Settings");
    }

    @FXML
    public void Exit(){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
