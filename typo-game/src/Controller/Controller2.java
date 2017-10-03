package Controller;

import Model.*;
import javafx.animation.AnimationTimer;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML
    Canvas canvas;
    @FXML
    AnchorPane anchor;
    @FXML
    Button Quit;

    private GraphicsContext gContext;
    private AnimationTimer loop;
    private Scene scene;
    private Session sp;
    private Player pl;

    public void setSession(Session session){
        this.sp =  session;
        sp.AddPlayer(new Player());
        pl = (Player) sp.players.get(0);
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
            double speed = 4;

            @Override
            public void handle(long now) {
//                pl.setCombo(2);
//                pl.setTempPoints(40);
//                pl.AwardPoints();
                gContext.clearRect(0,0, 3000, 3000);
                gContext.fillText(String.valueOf(pl.getScore()) ,x, y, 100);
                x+=speed;
            }
        };

        loop.start();
        keypress();
    }

    public void keypress(){

        scene.setOnKeyPressed( event -> {

            System.out.println("key =" + event.getCode().toString());
        });
    }

    @FXML
    public void Quitgame() {
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
    }
}
