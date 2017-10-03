package Controller;

import Model.Difficulty;
import Model.Singleplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

public class SettingsController {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Button BackBtn;
    @FXML
    CheckBox BorderBox;

    Stage s;
    Parent root;

    @FXML
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == BackBtn)
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/sample.fxml"));
            Scene scene = new Scene(parent, screenSize.getWidth(), screenSize.getHeight());
            Main.Stage.setScene(scene);
            Main.Stage.show();
        }
    }

    @FXML
    public void borderless(ActionEvent e) throws IOException {
        throw new NotImplementedException();

        /*
        Stage stage;
        Parent root;
        stage=(Stage) BorderBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/settings.fxml"));
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();


        if (BorderBox.isSelected()) {
            s.set
            s = new Stage(StageStyle.UNDECORATED);
            stage.
        }
        else {
            s = new Stage(StageStyle.DECORATED);
        }
        s.show();
        */
    }
}
