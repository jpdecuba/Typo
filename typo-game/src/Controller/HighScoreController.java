package Controller;

import Model.Difficulty;
import Model.Singleplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HighScoreController implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Button btnMode;
    @FXML
    Button btnBeginner_Easy;
    @FXML
    Button btnExpert_Normal;
    @FXML
    Button btnHard;

    @FXML
    public void btnModeClick() {
        if (btnMode.getText().equals("Singleplayer")){
            btnMode.setText("Multiplayer");
            btnBeginner_Easy.setText("Easy");
            btnExpert_Normal.setText("Normal");
            btnHard.setVisible(true);
        }
        else {
            btnMode.setText("Singleplayer");
            btnBeginner_Easy.setText("Beginner");
            btnExpert_Normal.setText("Expert");
            btnHard.setVisible(false);
        }
    }

    @FXML
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
//        if (button == BeginnerBtn)
//        {
//
//        }
//        else if(button == ExpertBtn) {
//
//        }
//        else {
//
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnMode.setText("Singleplayer");
        btnBeginner_Easy.setText("Beginner");
        btnExpert_Normal.setText("Expert");
        btnHard.setVisible(false);
    }
}
