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

import weather.Period;
import weather.WeatherAPI;

import java.util.ArrayList;
public class MainUI{
    private VBox vb;
    private HBox hb,hb2;
    private Button settings,curr;
    private BorderPane bp;
    private TextField searchTF;
    public Scene buildHome(){
        searchTF = new TextField();
        searchTF.setPromptText("Where to?");
        settings = new Button("Settings");


        curr = new Button();
        curr.setPrefSize(360,80);

        ArrayList<Period> forecast = WeatherAPI.getForecast("LOT", 77, 70);
        if (forecast != null) {
            Period today = forecast.get(0);
            curr.setText("Current City:" + today.temperature + "°  \n" + today.shortForecast);
            curr.setFont(Font.font(14)); // adjust size to fit
            curr.setWrapText(true);
        } else {
            curr.setText("Could not load weather");
        }
        hb = new HBox(settings);
        hb.setAlignment(Pos.TOP_RIGHT);
        vb = new VBox(20,hb,curr);

        bp = new BorderPane();
        bp.setCenter(vb);
        bp.setBottom(searchTF);
        bp.setPadding(new Insets(20));

        return new Scene(bp,360,640);
    }
    public Button getSettings(){return settings;}
    public Button getCurr(){return curr;}
    public TextField getSearchTF(){return searchTF;}
}