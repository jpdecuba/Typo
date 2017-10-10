package Controller;

import Model.*;
import Model.Threads.KeyPress;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Timer;

public class SessionController implements Initializable, Observer {
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Canvas canvas;
    @FXML
    AnchorPane anchor;
    @FXML
    Button Quit;
    @FXML
    Label ScoreLbl;

    @FXML
    Label ComboLbl;

    @FXML
    Label LivesLB1;
    @FXML
    Label CountDownLB1;

    Image img = new Image("/rocket.gif");


    private GraphicsContext gContext;
    private AnimationTimer loop;
    private Scene scene;
    private Session sp;
    private Player pl;
    private AnimationTimer timer;
    private int sets = 1;
    private Thread keypress;
    private int Lives;

    private int colourCount;

    @Override
    public void update(java.util.Observable o, Object arg) {
        loop.stop();
        timer.stop();
        //GamePlay = false;
        keypress.interrupt();
        System.out.println("end game");
        try {
            EndGame();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setSession(Session session) {
        this.sp = session;
        sp.AddPlayer(new Player());
        pl = sp.getPlayerOne();


    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void meth() {


        loop = new AnimationTimer() {
            double startX = 100;
            double y = 100;
            double x = startX;
            double speed = 30;

            @Override
            public void handle(long now) {
                //ScoreLbl.setText("SCORE: " + String.valueOf(sp.getPlayerOne().getScore()));
                x += speed;
            }
        };

        sp.addObserver(this);

        KeyPress keyFuction = new KeyPress(Main.Stage.getScene(), sp);

        keypress = new Thread(keyFuction);

        loop.start();


        //sp.sets.add(new Set("test"));
        //sp.sets.add(new Set("apple  "));
        try {
            sp.Start();
        } catch (Exception e) {
            System.out.println("not working");
        }

        begintimer();

        keypress.start();

    }


    private void SetValues() {


        if(Lives != sp.getPlayerOne().getLives() && Lives != 0) {
            Platform.runLater(() -> {

                gContext.setFill(Paint.valueOf("RED"));
                LivesLB1.setTextFill(Paint.valueOf("RED"));

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                // your code here
                                gContext.setFill(Paint.valueOf("BlACK"));
                                LivesLB1.setTextFill(Paint.valueOf("BlACK"));
                            }
                        },
                        300
                );


            });

        }



        ScoreLbl.setText("SCORE: " + String.valueOf(sp.getPlayerOne().getScore()));
        ComboLbl.setText("COMBO: " + String.valueOf(sp.getPlayerOne().getCombo()));
        LivesLB1.setText("LIVES:    " + String.valueOf(sp.getPlayerOne().getLives()));

        Lives = sp.getPlayerOne().getLives();

    }

    private void Letters() {
        int x = 100;
        int y = 100;
        double r = (canvas.getHeight() / sp.sets.size()) * sets;
        gContext.clearRect(0, 0, 3000, 3000);
        gContext.drawImage(img, canvas.getWidth() - 200, canvas.getHeight() - r - 200, 100, 100);
        try {
            List<Letter> L = sp.getCurrentSet().getCharacters();
            for (Letter item : L) {
                sets++;
                gContext.fillText(item.getCharacter(), x, y, 100);
                x += 35;

            }
        } catch (Exception e) {
            System.out.println("No more letters");
        }
    }


    public void begintimer() {

        timer = new AnimationTimer() {

            public void handle(long now) {
                SetValues();
                Letters();

            }
        };
        timer.start();

    }

    @FXML
    public void Quitgame() throws IOException {
        Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/sample.fxml")), "TYPO");
    }



    private void EndGame() throws IOException {
        Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/HighScoreView.fxml")), "HighScore");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gContext = canvas.getGraphicsContext2D();
        anchor.widthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
        });

        anchor.heightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
        });
        gContext.setFill(Color.BLACK);
        gContext.setFont(new Font("Arial", 30));


    }



}
