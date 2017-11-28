package Controller;

import Model.SaveProps.Settings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    AnchorPane anchor;
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
        anchor.setStyle(" -fx-background-image: url('/space.png')");
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
        }
    }
}
