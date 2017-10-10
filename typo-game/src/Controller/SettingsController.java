package Controller;

import Model.Difficulty;
import Model.SaveProps.Settings;
import Model.Singleplayer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    Button BackBtn;
    @FXML
    Button SaveBtn;
    @FXML
    Slider VolumeSlider;
    @FXML
    ChoiceBox ScreenModeBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ScreenModeBox.setItems(FXCollections.observableArrayList("None", "Borderless", "Fullscreen"));
        Properties settings = Settings.GetProperties();
        VolumeSlider.setValue(Double.valueOf(settings.getProperty("Volume")));
        switch (settings.getProperty("ScreenMode"))
        {
            case "Borderless":
                ScreenModeBox.getSelectionModel().select("Borderless");
                break;
            case "Fullscreen":
                ScreenModeBox.getSelectionModel().select("Fullscreen");
                break;
            default:
                ScreenModeBox.getSelectionModel().select("None");
                break;
        }
    }

    @FXML
    public void btnClick(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button == BackBtn)
        {
            Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/sample.fxml")), "TYPO");
        }
        else if(button == SaveBtn)
        {
            Properties settings = new Properties();
            settings.setProperty("Volume", String.valueOf(VolumeSlider.getValue()));
            String selection = String.valueOf(ScreenModeBox.getSelectionModel().getSelectedItem());
            settings.setProperty("ScreenMode", selection);
            Main.changeSettings(settings ,FXMLLoader.load(getClass().getResource("/Views/sample.fxml")));

            //Main.switchPage(FXMLLoader.load(getClass().getResource("/Views/settings.fxml")), "Settings");
        }
    }

    @FXML
    public void borderless(ActionEvent e) throws IOException {
        throw new NotImplementedException();

        /*
        Stage stage;
        Parent root;
        stage=(Stage) BorderBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/settings.fxml"));
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();


        if (BorderBox.isSelected()) {
            s.set
            s = new Stage(StageStyle.UNDECORATED);
            stage.
        }
        else {
            s = new Stage(StageStyle.DECORATED);
        }
        s.show();
        */
    }
}
