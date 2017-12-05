package Controller;

import Model.Chat.Client;
import Model.Database.DBHighScore;
import Model.DatabaseClient;
import Model.Difficulty;
import Model.HighScore;
import Model.Repository.HighScoreRepository;
import Model.Singleplayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HighScoreController implements Initializable {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    DatabaseClient hsRep;
    private Image rocket = new Image("/rocket.png");
    private List<Label> messages = new ArrayList<>();
    private int index = 0;
    private Client client;
    @FXML
    AnchorPane anchor;
    @FXML
    VBox chatBox;
    @FXML
    ScrollPane scrollPane;
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
    Button btnSend;
    @FXML
    TextField messageBox;
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
        if (button == btnBeginner_Easy && btnMode.getText().equals("Singleplayer"))
        {
            FillGrid(Difficulty.Beginner);
        }
        else if(button == btnBeginner_Easy && btnMode.getText().equals("Multiplayer")) {
            FillGrid(Difficulty.Easy);
        }
        else if(button == btnExpert_Normal && btnMode.getText().equals("Singleplayer")) {
            FillGrid(Difficulty.Expert);
        }
        else if(button == btnExpert_Normal && btnMode.getText().equals("Multiplayer")) {
            FillGrid(Difficulty.Medium);
        }
        else {
            FillGrid(Difficulty.Hard);
        }
    }
    @FXML
    public void btnBackClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == btnBack)
        {
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/sample.fxml")), "TYPO");
        }
    }

    public void FillGrid(Difficulty difficulty){
        lvHighscores.getChildren().clear();
        Label col1 = new Label("#");
        Label col2 = new Label("Name");
        Label col3 = new Label("Score");
        col1.setTextFill(Color.WHITE);
        col2.setTextFill(Color.WHITE);
        col3.setTextFill(Color.WHITE);
        col1.setFont(Font.font(null, FontWeight.BOLD, 30));
        col2.setFont(Font.font(null, FontWeight.BOLD, 30));
        col3.setFont(Font.font(null, FontWeight.BOLD, 30));
        lvHighscores.add(col1, 0, 0);
        lvHighscores.add(col2, 1, 0);
        lvHighscores.add(col3, 2, 0);
        List<HighScore> hscores = hsRep.getHighScore();

        int count = 0;
        for (HighScore hs: hscores
                ) {
            if (count != 10 && hs.getDiff() == difficulty){
                count++;
                TextField tf = new TextField(String.valueOf(count));
                TextField tf1 = new TextField(hs.getName());
                TextField tf2 = new TextField(String.valueOf(hs.getScore()));
                tf.setFont(Font.font(null, FontWeight.NORMAL, 18));
                tf1.setFont(Font.font(null, FontWeight.NORMAL, 18));
                tf2.setFont(Font.font(null, FontWeight.NORMAL, 18));
                tf.setEditable(false);
                tf1.setEditable(false);
                tf2.setEditable(false);
                lvHighscores.add(tf, 0, count);
                lvHighscores.add(tf1, 1, count);
                lvHighscores.add(tf2, 2, count);
            }
        }
    }

    public void receiveMessage(String message){
        //System.out.println(message);
        Platform.runLater(() -> {
            Label mess = new Label(message);
            mess.setFont(Font.font("Verdana", 16));
            messages.add(mess);
            messages.get(index).setMinWidth(chatBox.getWidth());
            messages.get(index).setAlignment(Pos.CENTER_LEFT);
            chatBox.getChildren().add(messages.get(index));
            index++;
        });

    }

    public void initChatBox(){
        btnSend.setOnAction(evt->{
            try {
                if (Main.settings.getProperty("name") != null) {
                    client.sendMessage(Main.settings.getProperty("name") + ": " + messageBox.getText());
                }else{
                    client.sendMessage("Anonymous: " + messageBox.getText());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            messageBox.clear();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle(" -fx-background-image: url('/space.png')");
        btnBeginner_Easy.setText("Beginner");
        btnExpert_Normal.setText("Expert");
        btnHard.setVisible(false);
        hsRep = new DatabaseClient(null);
        FillGrid(Difficulty.Beginner);
        btnMode.setText("Singleplayer");
        btnSend.setStyle(" -fx-background-image: url('/rocket.png'); -fx-background-size: 45px 45px; -fx-rotate: 90; ");
        initChatBox();
        scrollPane.vvalueProperty().bind(chatBox.heightProperty());
        try {
            client = new Client("DESKTOP-354OS6S", 1099, this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
