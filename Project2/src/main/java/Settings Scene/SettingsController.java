import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class SettingsController {
    private boolean darkMode = false;
    private boolean celOn = false;
    public void toggleDarkMode() {
        darkMode = !darkMode;
    }
    public void toggleCel(){
        celOn = !celOn;
    }

}