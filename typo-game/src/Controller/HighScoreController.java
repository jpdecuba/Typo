package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class HighScoreController implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Button BeginnerBtn;
    @FXML
    Button ExpertBtn;
    @FXML
    Button BackBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
