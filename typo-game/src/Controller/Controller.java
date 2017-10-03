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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Button SingleplayerBtn;
    @FXML
    Button ExitBtn;

    @FXML
    public void test(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) SingleplayerBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/DifficultyView.fxml"));
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
        stage.setTitle("Mode: Singleplayer");
        stage.show();
    }

    @FXML
    public void Exit(){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
