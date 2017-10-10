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
import javafx.scene.layout.FlowPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
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
            difficulty(Difficulty.Beginner, "/Views/page2.fxml","TYPO Singleplayer - Difficulty: Beginner");
        }
        else if(button == ExpertBtn)
        {
            difficulty(Difficulty.Expert, "/Views/page2.fxml","TYPO Singleplayer - Difficulty: Expert");
        }
        else
        {
            difficulty(null, "/Views/sample.fxml","TYPO");
        }
    }

    private void difficulty(Difficulty difficulty, String page, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        Parent parent = loader.load();
        if(difficulty != null)
        {
            Singleplayer sp = new Singleplayer(difficulty);
            Controller2 controller = loader.getController();
            controller.setSession(sp);
        }

        Main.switchPage(parent, title);
    }
}
