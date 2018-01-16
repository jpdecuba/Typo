package Model.SaveProps;

import java.io.*;
import java.util.Properties;

public class Settings {
    public static boolean SaveSettings(Properties settings) {
        if(settings == null) {
            settings = new Properties();
            settings.setProperty("Volume", "100");
            settings.setProperty("ScreenMode", "Fullscreen");
            settings.setProperty("name", "Anonymous");
            settings.setProperty("ip", "Localhost");
        }
        try {
            File file = new File(System.getenv("APPDATA") + "\\Typo");
            file.mkdirs();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.getCanonicalPath() + "\\Settings.properties"));
            settings.store(out, "Save settings");
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Properties GetProperties() {
        Properties properties = new Properties();
        try {
            File file = new File(System.getenv("APPDATA") + "\\Typo\\Settings.properties");
            InputStream input = new FileInputStream(file);
            properties.load(input);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String GetIP(){
        return "145.93.134.27";
    }
}
