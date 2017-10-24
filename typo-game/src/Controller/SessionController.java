package Controller;

import Model.*;
import Model.Database.DBHighScore;
import Model.Repository.HighScoreRepository;
import Model.Threads.KeyPress;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

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
    Label timerLabeltimeStamp;

    private Image img = new Image("/rocket.gif");
    private GraphicsContext gContext;
    private AnimationTimer loop;
    private Scene scene;
    private Session sp;
    private Player pl;
    private AnimationTimer timer;
    private int hs = 0;
    private Thread keypress;
    private int Lives;
    private EventHandler keypressevent;
    private int remaining = 5;

    @Override
    public void update(java.util.Observable o, Object arg) {


        loop.stop();
        timer.stop();

        //GamePlay = false;

        //keyFuction.update();


        //keypress.isInterrupted();

        Main.Stage.getScene().removeEventFilter(KeyEvent.ANY, keypressevent);

        Main.Stage.getScene().removeEventHandler(KeyEvent.ANY, keypressevent);


        System.out.println("end game");


        EndGame();



    }

    //Start countdown timer for starting the game.
    public void countdownTimer(){
        Timeline timeline;
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                remaining--;
                                SetTimerLabel();
                                if (remaining <= 0) {
                                    timeline.stop();
                                    timerLabeltimeStamp.setVisible(false);
                                    meth();
                                }
                            }
                        }));
        SetTimerLabel();
        timeline.playFromStart();
    }

    //Change Timer text to second remaining and fade away.
    public void SetTimerLabel(){
        timerLabeltimeStamp.setText(String.valueOf(remaining));
        FadeTransition ft = new FadeTransition(Duration.millis(1000), timerLabeltimeStamp);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void setSession(Session session) {
        this.sp = session;
        sp.AddPlayer(new Player());
        pl = sp.getPlayerOne();
        hs = getHighscore();
        countdownTimer();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


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



        loop.start();

        startkeypress();



        try {
            sp.Start();
        } catch (Exception e) {
            System.out.println("not working");
        }

        begintimer();


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
        double r = ((canvas.getHeight() - 300) / hs) * sp.getPlayerOne().getScore();
        gContext.clearRect(0, 0, 3000, 3000);
        gContext.fillRect(canvas.getWidth() - 200, 100, 100, 5);
        gContext.fillRect(canvas.getWidth() - 152.5, 100, 5, canvas.getHeight() - 200);
        gContext.fillText("High Score: " + hs, canvas.getWidth() - 250, 70);
        gContext.drawImage(img, canvas.getWidth() - 197.5, canvas.getHeight() - r - 200, 100, 100);
        try {
            List<Letter> L = sp.getCurrentSet().getCharacters();
            for (Letter item : L) {
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



    private void EndGame() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AddHighScoreView.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();

        if(sp.getPlayerOne() != null)
        {
            AddHighScoreController controller = loader.getController();
            controller.setPlayer(sp.getPlayerOne(), sp.getDifficulty());
        }

        Main.switchPage(parent, "Add HighScore");

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private int getHighscore() {
        HighScoreRepository hsRep = new HighScoreRepository(new DBHighScore());
        List<HighScore> highscores = hsRep.GetHighScores();
        for (HighScore h : highscores){
            if (h.getDiff() == sp.getDifficulty()){
                return h.getScore();
            }
        }
        return 0;
    }


    public void startkeypress() {





        keypressevent = new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent keyEvent){


                String s = keyEvent.getCode().toString();
                if (keyEvent.getCode() != KeyCode.SHIFT) {
                    if (keyEvent.getEventType() == KEY_PRESSED) {

                        if (s.contains("DIGIT")) {
                            s = s.substring(5);
                        }


                        if (keyEvent.isShiftDown()) {
                            s.toUpperCase();
                        } else {
                            s = s.toLowerCase();
                        }

                        //char c = s.charAt(0);

                        //System.out.println("key = " + s);

                        typechar(s);
                    }
                }

            }


        };




        Main.Stage.getScene().addEventFilter(KeyEvent.ANY, keypressevent);



    }

    public synchronized void typechar(String c) {


        sp.TypeCharacter(c, sp.getPlayerOne());

    }


}
