import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class SettingsUI{
    private Button returnButt,unitChange,lightingChange;
    private Label tabName;
    private VBox vb;
    public Scene buildSettings(){
        unitChange = new Button("Cel to Fahr");
        tabName = new Label("Accessability");
        returnButt = new Button("BACK");

        VBox vb = new VBox(20,tabName, unitChange,returnButt);
        return new Scene(vb,360,640);
    }

    public Button getReturnButt() {return returnButt;}

    public Button getLightingChange() {return lightingChange;}
}