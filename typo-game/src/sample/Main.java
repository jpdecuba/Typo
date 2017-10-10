package sample;

import Model.Serialize.SetSerialize;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static Stage Stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.getIcons().add(new Image("/Y.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/Views/sample.fxml"));
        primaryStage.setTitle("TYPO");
        primaryStage.setScene(new Scene(root));
        //primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        Stage = primaryStage;
        Stage.show();

        Platform.runLater(()->{
            if(!SetSerialize.SaveSets())
                System.out.println("The single player sets could not be saved.");
        });
    }

    public static void switchPage(Parent parent, String title) throws IOException
    {
        Main.Stage.getScene().setRoot(parent);
        Main.Stage.setTitle(title);
        Main.Stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
