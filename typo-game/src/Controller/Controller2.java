package Controller;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML
    Canvas canvas;
    GraphicsContext gContext;
    AnimationTimer loop;
    Scene scene;


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
                gContext.clearRect(0,0, 3000, 3000);
                gContext.fillText("A" ,x, y, 30);

                x+=speed;
            }
        };

        loop.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gContext = canvas.getGraphicsContext2D();
        gContext.setFill(Color.BLACK);
        gContext.setFont(new Font("Arial", 30));
//        gContext.fillRect(10, 10, 20, 20);
    }
}
