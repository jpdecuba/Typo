package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage Stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.getIcons().add(new Image("/Y.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/Views/sample.fxml"));
        primaryStage.setTitle("TYPO");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
        Stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
