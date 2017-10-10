package Controller;

import Model.Database.DBHighScore;
import Model.Difficulty;
import Model.HighScore;
import Model.Player;
import Model.Repository.HighScoreRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AddHighScoreController implements Initializable {
    private Player player;
    private Difficulty difficulty;
    private HighScoreRepository hsRep;
    @FXML
    Label scoreLbl;
    @FXML
    TextField textField;

    public void setPlayer(Player player, Difficulty difficulty){
        this.player = player;
        this.difficulty = difficulty;
        scoreLbl.setText("Score: " + String.valueOf(player.getScore()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnSubmit(ActionEvent actionEvent) {
        if (textField.getText() != "")
        hsRep = new HighScoreRepository(new DBHighScore());
        HighScore hs = new HighScore(textField.getText(), player.getScore(), difficulty, LocalDate.now());
        hsRep.Save(hs);
        try {
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/sample.fxml")), "High Score");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnCancel(ActionEvent actionEvent) {
        try {
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/sample.fxml")), "TYPO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
