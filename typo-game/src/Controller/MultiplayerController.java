package Controller;

import Model.*;
import Model.Database.DBHighScore;
import Model.Repository.HighScoreRepository;
import Model.Sockets.GameClient;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.util.Duration;
import sample.Main;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.List;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class MultiplayerController implements Initializable, Observer {

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @FXML
    Canvas canvas;
    @FXML
    AnchorPane anchor;
    @FXML
    javafx.scene.control.Button Quit;
    @FXML
    javafx.scene.control.Label ScoreLbl;
    @FXML
    javafx.scene.control.Label ComboLbl;
    @FXML
    javafx.scene.control.Label LivesLB1;
    @FXML
    Label timerLabeltimeStamp;
    @FXML
    ImageView star;

    private javafx.scene.image.Image img = new javafx.scene.image.Image("/rocket.gif");
    private javafx.scene.image.Image img2 = new javafx.scene.image.Image("/rocket2.gif");
    private javafx.scene.image.Image starImg = new Image("/star.png");
    private GraphicsContext gContext;
    private Multiplayer mp;
    private Player pl;
    private AnimationTimer loop;
    private AnimationTimer timer;
    private int hs = 0;
    private int remaining = 2;
    private int Lives;
    private MediaPlayer mpl = null;
    private MediaPlayer effect = null;
    private Media sEffect = new Media(new File("typo-game/src/chirp.mp3").toURI().toString());
    private EventHandler keypressevent;
    private EventHandler MouseClickEvent;
    private Opportunity Opp = new Opportunity(OppName.Empty, null);
    private boolean mirrored = false;
    private GameClient gc;

    public void setSession(Multiplayer mp, GameClient gameClient) {
        this.gc = gameClient;
        this.mp = mp;
        countdownTimer();
        mp.AddPlayer(new Player());
        mp.AddPlayer(new Player());
        pl = mp.getPlayerOne();
        hs = getHighscore();
        star.setVisible(false);
        star.setImage(starImg);
    }

    //Letters Drawing:
    private List<Letter> letters = new ArrayList<Letter>();
    private boolean rotated = false;
    //
    private void Letters(int x, int y, boolean rotate) {
        if(rotate && !rotated){ rotate(gContext, 180, x,y); rotated = true; }
        else if (!rotate && rotated){ gContext.restore(); rotated = false;}
        try { //Retrieve the letters from the current set
            letters = mp.getCurrentSet().getCharacters();
            for (Letter item : letters) {
                gContext.setFont(new Font("Verdana", 50));
                gContext.fillText(item.getCharacter(), x, y, 100);
                if(rotated){ x -= 35; } else { x += 35; }
            }
        } catch (Exception e) { System.out.println("No more letters"); }
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

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
        Media sound = new Media(new File("typo-game/src/DNBTTLoop3.wav").toURI().toString());
        mpl = new MediaPlayer(sound);
        mpl.setVolume(Double.valueOf(Main.settings.getProperty("Volume")) / 100);
        mpl.setCycleCount(AudioClip.INDEFINITE);
        mpl.play();
    }

    public void SetTimerLabel(){
        timerLabeltimeStamp.setText(String.valueOf(remaining));
        FadeTransition ft = new FadeTransition(Duration.millis(1000), timerLabeltimeStamp);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        ft.play();
    }

    //gamestart methode
    public void meth() {
        loop = new AnimationTimer() {
            double startX = 100;
            double y = 100;
            double x = startX;
            double speed = 30;

            @Override
            public void handle(long now) {
                ScoreLbl.setText("SCORE: " + String.valueOf(mp.getPlayerOne().getScore()));
                x += speed;
            }
        };
        mp.addObserver(this);
        loop.start();
        startEvents();
        try { mp.Start(); }
        catch (Exception e) { System.out.println("not working"); }
        begintimer();
    }

    @FXML
    public void Quitgame() {

    }

    //return highscore
    private int getHighscore() {
        HighScoreRepository hsRep = new HighScoreRepository(new DBHighScore());
        List<HighScore> highscores = hsRep.GetHighScores();
        for (HighScore h : highscores){
            if (h.getDiff() == mp.getDifficulty()){
                return h.getScore();
            }
        }
        return 0;
    }

    private void Rocket(){ //Show The Rocket on the screen
        double m1 = ((canvas.getHeight() - 300) / hs) * mp.getPlayerOne().getScore();
        double m2 = ((canvas.getHeight() - 300) / hs) * mp.getPlayerTwo().getScore();
        gContext.setFont(new Font("Verdana", 30));
        gContext.fillRect(canvas.getWidth() - 250, 100, 200, 5);
        gContext.fillRect(canvas.getWidth() - 100, 100, 5, canvas.getHeight() - 200);
        gContext.fillRect(canvas.getWidth() - 200, 100, 5, canvas.getHeight() - 200);
        gContext.fillText("High Score: " + hs, canvas.getWidth() - 280, 70);
        gContext.drawImage(img2, canvas.getWidth() - 146, canvas.getHeight() - m1 - 200, 100, 100);
        gContext.drawImage(img, canvas.getWidth() - 246, canvas.getHeight() - m2 - 200, 100, 100);
    }

    public void begintimer() {
        timer = new AnimationTimer() {
            public void handle(long now) {
                SetValues();
                gContext.clearRect(0, 0, screenSize.getWidth(), screenSize.getHeight());
                Rocket();
                Letters(100, 100, mirrored);
            }
        };
        timer.start();
    }

    //setvalues change collor based on lives change
    private void SetValues() {
        if(Lives != mp.getPlayerOne().getLives() && Lives != 0 &&Lives > mp.getPlayerOne().getLives()) {
            Platform.runLater(() -> {
                effect = new MediaPlayer(sEffect);
                effect.setVolume(Double.valueOf(Main.settings.getProperty("Volume")) / 100);
                effect.play();
                gContext.setFill(javafx.scene.paint.Paint.valueOf("RED"));
                LivesLB1.setTextFill(javafx.scene.paint.Paint.valueOf("RED"));

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                // your code here
                                gContext.setFill(javafx.scene.paint.Paint.valueOf("WHITE"));
                                LivesLB1.setTextFill(Paint.valueOf("WHITE"));
                            }
                        },
                        300
                );
            });
        }

        ScoreLbl.setText("SCORE: " + String.valueOf(mp.getPlayerOne().getScore()));
        ComboLbl.setText("COMBO: " + String.valueOf(mp.getPlayerOne().getCombo()));
        LivesLB1.setText("LIVES:    " + String.valueOf(mp.getPlayerOne().getLives()));

        Lives = mp.getPlayerOne().getLives();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle(" -fx-background-image: url('/space.png')");
        gContext = canvas.getGraphicsContext2D();
        anchor.widthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
        });

        anchor.heightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
        });
        gContext.setFill(Color.WHITE);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void startEvents() {
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

        MouseClickEvent = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {

                if(Opp != null){

                    ClickOpp((int)event.getX(),(int)event.getY());
                }



            }


        };

        Main.Stage.getScene().addEventFilter(KeyEvent.ANY, keypressevent);
        Main.Stage.getScene().addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, MouseClickEvent);

    }

    // typechar methode sends typed char to session
    public synchronized void typechar(String c) {

        mp.TypeCharacter(c, mp.getPlayerOne());

    }
    // ClickOpp methode sends click posion to session
    public synchronized void ClickOpp(int x,int y) {

        mp.mouseclick(x,y, mp.getPlayerOne());

    }
}
