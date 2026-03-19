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
    private HBox hb,hb2, d1hb, d2hb, d3hb, d4hb, d5hb, d6hb, d7hb;
    private Button settings, searchBtn;
    private BorderPane bp;
    private TextField searchTF;
    private Label currentWeather, location, curr, d1L,d2L, d3L, d4L, d5L,d6L,d7L;
    private ScrollPane scrollPane;

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
            location = new Label();
            location.setStyle("-fx-font-size: 25px; -fx-font-family: 'Georgia';");
            location.setText("Chicago");
            curr.setText(forecast.get(0).shortForecast);
            curr.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
            currentWeather.setStyle("-fx-font-size: 96px; -fx-weight: bold;");
            curr.setWrapText(true);
            if (forecast.get(0).name.contains("night") == true){
                d1L = new Label(forecast.get(1).name + " " + forecast.get(1).temperature + "°  " + forecast.get(1).shortForecast + "\n" + forecast.get(2).name + " " + forecast.get(2).temperature + "°  " + forecast.get(2).shortForecast);
                d1L.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
                d1L.setWrapText(true);  
                d2L = new Label(forecast.get(3).name + " " + forecast.get(3).temperature + "°  " + forecast.get(3).shortForecast + "\n" + forecast.get(4).name + " " + forecast.get(4).temperature + "°  " + forecast.get(4).shortForecast);
                d2L.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
                d2L.setWrapText(true);
                d3L = new Label(forecast.get(5).name + " " + forecast.get(5).temperature + "°  " + forecast.get(5).shortForecast + "\n" + forecast.get(6).name + " " + forecast.get(6).temperature + "°  " + forecast.get(6).shortForecast);
                d3L.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
                d3L.setWrapText(true);
                d4L = new Label(forecast.get(7).name + " " + forecast.get(7).temperature + "°  " + forecast.get(7).shortForecast + "\n" + forecast.get(8).name + " " + forecast.get(8).temperature + "°  " + forecast.get(8).shortForecast);
                d4L.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
                d4L.setWrapText(true);
                d5L = new Label(forecast.get(9).name + " " + forecast.get(9).temperature + "°  " + forecast.get(9).shortForecast + "\n" + forecast.get(10).name + " " + forecast.get(10).temperature + "°  " + forecast.get(10).shortForecast);
                d5L.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
                d5L.setWrapText(true);
                d6L = new Label(forecast.get(11).name + " " + forecast.get(11).temperature + "°  " + forecast.get(11).shortForecast + "\n" + forecast.get(12).name + " " + forecast.get(12).temperature + "°  " + forecast.get(12).shortForecast);
                d6L.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
                d6L.setWrapText(true);
                d7L = new Label(forecast.get(13).name + " " + forecast.get(13).temperature + "°  " + forecast.get(13).shortForecast + "\n");
                d7L.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
                d7L.setWrapText(true);
            }
            else {
                d1L = new Label(forecast.get(0).name + " " + forecast.get(0).temperature + "°  " + forecast.get(0).shortForecast + "\n" + forecast.get(1).name + " " + forecast.get(1).temperature + "°  " + forecast.get(1).shortForecast);
                d1L.setWrapText(true);
                d2L = new Label(forecast.get(2).name + " " + forecast.get(2).temperature + "°  " + forecast.get(2).shortForecast + "\n" + forecast.get(3).name + " " + forecast.get(3).temperature + "°  " + forecast.get(3).shortForecast);
                d2L.setWrapText(true);
                d3L = new Label(forecast.get(4).name + " " + forecast.get(4).temperature + "°  " + forecast.get(4).shortForecast + "\n" + forecast.get(5).name + " " + forecast.get(5).temperature + "°  " + forecast.get(5).shortForecast);
                d3L.setWrapText(true);
                d4L = new Label(forecast.get(6).name + " " + forecast.get(6).temperature + "°  " + forecast.get(6).shortForecast + "\n" + forecast.get(7).name + " " + forecast.get(7).temperature + "°  " + forecast.get(7).shortForecast);
                d4L.setWrapText(true);
                d5L = new Label(forecast.get(8).name + " " + forecast.get(8).temperature + "°  " + forecast.get(8).shortForecast + "\n" + forecast.get(9).name + " " + forecast.get(9).temperature + "°  " + forecast.get(9).shortForecast);
                d5L.setWrapText(true);
                d6L = new Label(forecast.get(10).name + " " + forecast.get(10).temperature + "°  " + forecast.get(10).shortForecast + "\n" + forecast.get(11).name + " " + forecast.get(11).temperature + "°  " + forecast.get(11).shortForecast);
                d6L.setWrapText(true);
                d7L = new Label(forecast.get(12).name + " " + forecast.get(12).temperature + "°  " + forecast.get(12).shortForecast + "\n" + forecast.get(13).name + " " + forecast.get(13).temperature + "°  " + forecast.get(13).shortForecast);
                d7L.setWrapText(true);
            }
        } else {
            curr.setText("Could not load weather");
        }

        d1hb = new HBox(d1L);
        d1hb.setPrefSize(100,75);
        d1hb.setStyle("-fx-background-color: lightblue; -fx-border-color: white; -fx-background-radius: 15 15 0 0;");
        d2hb = new HBox(d2L);
        d2hb.setPrefSize(100,75);
        d2hb.setStyle("-fx-background-color: lightblue;-fx-border-color: white; -fx-border-width: 1;");
        d3hb = new HBox(d3L);
        d3hb.setPrefSize(100,75);
        d3hb.setStyle("-fx-background-color: lightblue;-fx-border-color: white; -fx-border-width: 1;");
        d4hb = new HBox(d4L);
        d4hb.setPrefSize(100,75);
        d4hb.setStyle("-fx-background-color: lightblue;-fx-border-color: white; -fx-border-width: 1;");
        d5hb = new HBox(d5L);
        d5hb.setPrefSize(100,75);
        d5hb.setStyle("-fx-background-color: lightblue;-fx-border-color: white; -fx-border-width: 1;");
        d6hb = new HBox(d6L);
        d6hb.setPrefSize(100,75);
        d6hb.setStyle("-fx-background-color: lightblue;-fx-border-color: white; -fx-border-width: 1;");
        d7hb = new HBox(d7L);
        d7hb.setPrefSize(100,75);
        d7hb.setStyle("-fx-background-color: lightblue; -fx-border-color: white; -fx-background-radius: 0 0 15 15;");

        hb = new HBox(settings);
        scrollPane = new ScrollPane();
        hb.setAlignment(Pos.TOP_RIGHT);
        vb = new VBox(-10,location, currentWeather, curr, searchTF, searchBtn);
        vb1 = new VBox(scrollPane);
        vb.setMargin(currentWeather, new Insets(0,0,0,90));     //Centers the large number
        vb.setMargin(location, new Insets(0,0,0,110));
        vb.setMargin(searchBtn, new Insets(20,0,125,125));
        vb1.setMargin(scrollPane, new Insets(-90,0,0,0));
        bp = new BorderPane();
        bp.setStyle("-fx-background-color: white;");
        bp.setTop(hb);
        bp.setCenter(vb);
        bp.setPadding(new Insets(20));
        bp.setBottom(vb1);
        weatherForecastvb = new VBox(d1hb,d2hb,d3hb,d4hb,d5hb,d6hb,d7hb);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(300);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setFitToWidth(true);


        return new Scene(bp,360,640);
    }
    public Button getSettings(){return settings;}
    public Label getCurr(){return curr;}
    public TextField getSearchTF(){return searchTF;}
}