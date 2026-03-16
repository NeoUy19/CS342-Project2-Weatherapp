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
import javafx.scene.control.TextArea;

import weather.Period;
import weather.WeatherAPI;

import java.util.ArrayList;
public class MainUI{
    private VBox vb, vb1;                //Declared variables
    private HBox hb,hb2;
    private Button settings, searchBtn;
    private BorderPane bp;
    private TextField searchTF;
    private Label currentWeather, curr;
    public Scene buildHome(){
        searchTF = new TextField();     //Declared TextField
        searchTF.setPromptText("Where to?");
        settings = new Button("Settings");

        searchBtn = new Button("Search");

        curr = new Label();
        curr.setPrefSize(360,80);       //Setting the label size

        ArrayList<Period> forecast = WeatherAPI.getForecast("LOT", 77, 70);     //Location of Chicago
        if (forecast != null) {
            Period today = forecast.get(0);
            currentWeather = new Label(today.temperature + "°  \n");
            currentWeather.setStyle("-fx-font-size: 96px; -fx-weight: bold;");
            curr.setText("Current City:" + today.temperature + "°  \n" + today.shortForecast);
            curr.setFont(Font.font(14)); // adjust size to fit
            curr.setWrapText(true);
        } else {
            curr.setText("Could not load weather");
        }
        hb = new HBox(settings);
        hb.setAlignment(Pos.TOP_RIGHT);
        vb = new VBox(-10,currentWeather, curr, searchTF);
        vb1 = new VBox(searchBtn);
        vb.setMargin(currentWeather, new Insets(0,0,0,90));     //Centers the large number
        vb1.setMargin(searchBtn, new Insets(0,0,335,125));
        bp = new BorderPane();
        bp.setTop(hb);
        bp.setCenter(vb);
        bp.setPadding(new Insets(20));
        bp.setBottom(vb1);

        return new Scene(bp,360,640);
    }
    public Button getSettings(){return settings;}
    public Label getCurr(){return curr;}
    public TextField getSearchTF(){return searchTF;}
}