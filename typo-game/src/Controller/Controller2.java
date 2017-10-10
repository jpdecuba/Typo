package Controller;

import Model.*;
import Model.Threads.KeyPress;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller2 implements Initializable, Observer {
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

    Image img = new Image("/rocket.gif");



    private GraphicsContext gContext;
    private AnimationTimer loop;
    private Session sp;
    private Player pl;
    private AnimationTimer timer;
    private int sets = 1;
    private Thread keypress;

    @Override
    public void update(java.util.Observable o, Object arg) {
        loop.stop();
        timer.stop();
        //GamePlay = false;
        keypress.interrupt();
        System.out.println("end game");


    }

    public void setSession(Session session){
        this.sp =  session;
        sp.AddPlayer(new Player());
        pl = sp.getPlayerOne();


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
                x+=speed;
            }
        };

        sp.addObserver(this);

        KeyPress keyFuction = new KeyPress(Main.Stage.getScene(),sp);

        keypress = new Thread(keyFuction);

        loop.start();


        //sp.sets.add(new Set("test"));
        //sp.sets.add(new Set("apple  "));
        try {
            sp.Start();
        }
        catch (Exception e){
            System.out.println("not working");
        }

        begintimer();



        keypress.start();



    }


    private void SetValues(){

        ScoreLbl.setText("SCORE: " + String.valueOf(sp.getPlayerOne().getScore()));
        ComboLbl.setText("COMBO: " + String.valueOf(sp.getPlayerOne().getCombo()));
    }

    private void Letters(){
        int x = 100;
        int y = 100;
        double r = (canvas.getHeight() / sp.sets.size()) * sets;
        gContext.clearRect(0,0, 3000, 3000);
        gContext.drawImage(img, canvas.getWidth() - 200, canvas.getHeight() - r - 200, 100, 100);
        sets++;
        try{
            List<Letter> L = sp.getCurrentSet().getCharacters();
            for(Letter item : L){
                gContext.fillText(item.getCharacter() ,x, y, 100);
                x += 20;

            }
        }catch (Exception e){
            System.out.println("No more letters");
        }
    }




    public  void begintimer(){

        timer = new AnimationTimer(){

            public void handle(long now)
            {
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
        gContext.setFont(new Font("Verdana", 30));
//        Thread.sleep(4000);
    }
}
