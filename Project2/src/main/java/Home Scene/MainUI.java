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
import javafx.scene.control.ScrollPane;

import weather.Period;
import weather.WeatherAPI;

import java.util.ArrayList;
public class MainUI{
    private VBox vb, vb1, weatherForecastvb;                //Declared variables
    private HBox hb,hb2, Mondayhb, Tuesdayhb, Wednesdayhb, Thursdayhb, Fridayhb, Saturdayhb, Sundayhb;
    private Button settings, searchBtn;
    private BorderPane bp;
    private TextField searchTF;
    private Label currentWeather, curr, Mondaylabel;
    private ScrollPane scrollPane;

    public Scene buildHome(){
        searchTF = new TextField();     //Declared TextField
        searchTF.setPromptText("Where to?");
        settings = new Button("Settings");
        searchBtn = new Button("Search");

        scrollPane = new ScrollPane();

        Mondaylabel = new Label();
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
        Mondayhb = new HBox(Mondaylabel);
        hb.setAlignment(Pos.TOP_RIGHT);
        vb = new VBox(-10,currentWeather, curr, searchTF, searchBtn);
        vb1 = new VBox(scrollPane);
        vb.setMargin(currentWeather, new Insets(0,0,0,90));     //Centers the large number
        vb.setMargin(searchBtn, new Insets(20,0,125,125));
        vb1.setMargin(scrollPane, new Insets(0,0,120,0));
        bp = new BorderPane();
        bp.setTop(hb);
        bp.setCenter(vb);
        bp.setPadding(new Insets(20));
        bp.setBottom(vb1);
        weatherForecastvb = new VBox(20, Mondayhb);
        scrollPane.setContent(weatherForecastvb);

        return new Scene(bp,360,640);
    }
    public Button getSettings(){return settings;}
    public Label getCurr(){return curr;}
    public TextField getSearchTF(){return searchTF;}
}