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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;
import Controller.*;

import java.io.IOException;

public class DifficultyController {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Button BeginnerBtn;
    @FXML
    Button ExpertBtn;
    @FXML
    Button BackBtn;

    @FXML
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == BeginnerBtn)
        {
            Singleplayer sp = new Singleplayer(Difficulty.Beginner);
            Stage stage;
            stage=(Stage) BeginnerBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/page2.fxml"));
            AnchorPane anchor = loader.load();
            SessionController controller = loader.getController();
            controller.setSession(sp);
            Scene scene = new Scene(anchor, screenSize.getWidth(), screenSize.getHeight());
            controller.setScene(scene);
            stage.setScene(scene);
            stage.setTitle("TYPO Singleplayer - Difficulty: Beginner");
            stage.show();
        }
        else if(button == ExpertBtn) {
            Singleplayer sp = new Singleplayer(Difficulty.Expert);
            Stage stage;
            stage=(Stage) BeginnerBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/page2.fxml"));
            AnchorPane anchor = loader.load();
            SessionController controller = loader.getController();
            controller.setSession(sp);
            Scene scene = new Scene(anchor, screenSize.getWidth(), screenSize.getHeight());
            controller.setScene(scene);
            stage.setScene(scene);
            stage.setTitle("TYPO Singleplayer - Difficulty: Expert");
            stage.show();
        }
        else {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/sample.fxml"));
            Scene scene = new Scene(parent, screenSize.getWidth(), screenSize.getHeight());
            Main.Stage.setScene(scene);
            Main.Stage.show();
        }
    }
}
