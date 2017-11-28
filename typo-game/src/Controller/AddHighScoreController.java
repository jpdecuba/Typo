package Controller;

import Model.Database.DBHighScore;
import Model.Database.Database;
import Model.DatabaseClient;
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
import javafx.scene.layout.AnchorPane;
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
    AnchorPane anchor;
    @FXML
    Label scoreLbl;
    @FXML
    TextField textField;

    public void setPlayer(Player player, Difficulty difficulty){
        this.player = player;
        this.difficulty = difficulty;
        scoreLbl.setText("Score: " + String.valueOf(player.getScore()));
        textField.setText(Main.settings.getProperty("name"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle(" -fx-background-image: url('/space.png')");
    }

    public void btnSubmit(ActionEvent actionEvent) {
        DatabaseClient DBclient = new DatabaseClient(null);
        HighScore hs = null;
        if (textField.getText() != null && !textField.getText().isEmpty())
        {
            hs = new HighScore(textField.getText(), player.getScore(), difficulty, LocalDate.now());
        }
        else
        {
            hs = new HighScore("Unkn0wn", player.getScore(), difficulty, LocalDate.now());
        }
        DBclient.SetHighScore(hs);
        try {
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/HighScoreView.fxml")), "High Score");
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
