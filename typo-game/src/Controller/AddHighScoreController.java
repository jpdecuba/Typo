package Controller;

import Model.Player;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddHighScoreController implements Initializable {
    private Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
