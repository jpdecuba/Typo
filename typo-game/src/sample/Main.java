package sample;

import Model.SaveProps.SetSerialize;
import Model.SaveProps.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Properties;

public class Main extends Application {

    public static Stage Stage;
    public static Properties settings;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.settings = Settings.GetProperties();
        if(this.settings == null)
        {
            Settings.SaveSettings(null);
            this.settings = Settings.GetProperties();
        }
        Stage = primaryStage;
        applySettings(FXMLLoader.load(getClass().getResource("/Views/sample.fxml")));
        Stage.show();

        Platform.runLater(()->{
            if(!SetSerialize.SaveSets())
                System.out.println("The single player sets could not be saved.");
        });
    }

    public static void changeSettings(Properties properties, Parent parent)
    {
        if(Settings.SaveSettings(properties))
        {
            settings = properties;
            applySettings(parent);
        }

    }

    private static void applySettings(Parent parent)
    {
        switch (settings.getProperty("ScreenMode"))
        {
            case "Fullscreen":
                screenMode(StageStyle.DECORATED, true, parent);
                break;
            case "Borderless":
                screenMode(StageStyle.UNDECORATED, false, parent);
                break;
            default:
                screenMode(StageStyle.DECORATED, false, parent);
                break;
        }
    }

    private static void screenMode(StageStyle style, boolean fullscreen, Parent parent)
    {
        Stage.close();
        Stage Stage2 = new Stage();
        Stage2.getIcons().add(new Image("/Y.png"));
        Stage2.setTitle("TYPO");
        Stage2.setScene(new Scene(parent));
        Stage2.initStyle(style);
        Stage2.setMaximized(true);
        Stage2.setFullScreen(fullscreen);
        Stage = Stage2;
        Stage.show();
    }

    public static void switchPage(Parent parent, String title)
    {
        Main.Stage.getScene().setRoot(parent);
        Main.Stage.setTitle(title);
        Main.Stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
