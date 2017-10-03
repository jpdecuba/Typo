package Controller;

import Model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;

public class Controller2 implements Initializable {
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

    Image img = new Image("/rocket.gif");



    private GraphicsContext gContext;
    private AnimationTimer loop;
    private Scene scene;
    private Session sp;
    private Player pl;
    private AnimationTimer timer;

    public void setSession(Session session){
        this.sp =  session;
        sp.AddPlayer(new Player());
        pl = sp.getPlayerOne();
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }

    @FXML
    public void meth() {
        loop = new AnimationTimer() {
            double startX = 100;
            double endX = 200;
            double y = 100;
            double x = startX;
            double speed = 3;

            @Override
            public void handle(long now) {
                sp.getPlayerOne().setCombo(2);
                sp.getPlayerOne().setTempPoints(40);
                sp.getPlayerOne().AwardPoints();
                //ScoreLbl.setText("SCORE: " + String.valueOf(sp.getPlayerOne().getScore()));
                gContext.clearRect(0,0, 3000, 3000);
//                gContext.drawImage(img,x,y, 100, 100);
//                gContext.fillText("A" ,x, y, 100);
                x+=speed;
            }
        };

        loop.start();

        sp.sets.add(new Set("test"));
        try {
            sp.Start();
        }
        catch (Exception e){
            System.out.println("not working");
        }

        begintimer();
        Platform.runLater(()-> {
            keypress();

        });

    }


    private void SetValues(){

        ScoreLbl.setText("SCORE: " + String.valueOf(sp.getPlayerOne().getScore()));
        ComboLbl.setText("COMBO: " + String.valueOf(sp.getPlayerOne().getCombo()));


    }

    private void Letters(){

        int x = 100;
        int y = 100;

        gContext.clearRect(0,0, 3000, 3000);

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



    public void keypress(){

        scene.setOnKeyPressed( event -> {

            System.out.println("key = " + event.getCode().toString());

            String s = event.getCode().toString();

            //char c = s.charAt(0);

            typechar(s);

    });



    }



    public synchronized void typechar (String c){

        Platform.runLater(()-> {
            System.out.println(sp.TypeCharacter(c, sp.getPlayerOne()));

        });
    }

    @FXML
    public void Quitgame() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Views/sample.fxml"));
        Scene scene = new Scene(parent, screenSize.getWidth(), screenSize.getHeight());
        Main.Stage.setScene(scene);
        Main.Stage.show();
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
//        Thread.sleep(4000);
    }
}
