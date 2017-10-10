package Controller;

import Model.Database.DBHighScore;
import Model.Difficulty;
import Model.HighScore;
import Model.Repository.HighScoreRepository;
import Model.Singleplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HighScoreController implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    HighScoreRepository hsRep;
    @FXML
    Button btnMode;
    @FXML
    Button btnBeginner_Easy;
    @FXML
    Button btnExpert_Normal;
    @FXML
    Button btnHard;
    @FXML
    Button btnBack;
    @FXML
    GridPane lvHighscores;

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
    @FXML
    public void btnBackClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == btnBack)
        {
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/sample.fxml")), "TYPO");
        }
    }

    public void FillGrid(String difficulty){
        btnMode.setText("Singleplayer");
        btnBeginner_Easy.setText("Beginner");
        btnExpert_Normal.setText("Expert");
        btnHard.setVisible(false);
        Label col1 = new Label("Rank");
        Label col2 = new Label("Name");
        Label col3 = new Label("Score");
        col1.setFont(Font.font(null, FontWeight.BOLD, 30));
        col2.setFont(Font.font(null, FontWeight.BOLD, 30));
        col3.setFont(Font.font(null, FontWeight.BOLD, 30));
        lvHighscores.add(col1, 0, 0);
        lvHighscores.add(col2, 1, 0);
        lvHighscores.add(col3, 2, 0);
        List<HighScore> hscores = hsRep.GetHighScores();
        int count = 0;
        if(difficulty == "Beginner"){
            for (HighScore hs: hscores
                 ) {
                if (count != 10 && hs.getDiff() == Difficulty.Beginner){
                    count++;
                    TextField tf = new TextField(String.valueOf(count));
                    TextField tf1 = new TextField(hs.getName());
                    TextField tf2 = new TextField(String.valueOf(hs.getScore()));
                    tf1.setFont(Font.font(null, FontWeight.NORMAL, 18));
                    tf1.setFont(Font.font(null, FontWeight.NORMAL, 18));
                    tf1.setFont(Font.font(null, FontWeight.NORMAL, 18));
                    lvHighscores.add(tf, count, 0);
                    lvHighscores.add(tf1, count, 1);
                    lvHighscores.add(tf2, count, 2);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hsRep = new HighScoreRepository(new DBHighScore());
        FillGrid("Beginner");
    }
}
