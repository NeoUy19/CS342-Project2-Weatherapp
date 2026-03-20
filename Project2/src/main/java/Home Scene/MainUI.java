import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ComboBox;
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
    private VBox vb, vb1, weatherForecastvb, currentDay, sevDay, dayLocvb;                //Declared variables
    private HBox hb,hb2,sevDayhb;
    private Button settings, searchBtn, dayBtn, nightBtn, backbtn;
    private BorderPane bp, bp2;
    private TextField searchTF;
    private Label currentWeather, location, curr, sevDayL, sftLab;
    private ScrollPane scrollPane;
    private ComboBox<String> cityDropdown;

    public Scene buildHome(){
        //searchTF = new TextField();     //Declared TextField
        //searchTF.setPromptText("Where to?");
        backbtn = new Button();
        cityDropdown = new ComboBox<>();
        searchBtn = new Button("Search");
        cityDropdown.setPromptText("Select a city to see");
        cityDropdown.getItems().addAll("San Francisco, CA", "New York, NY", "Austin, TX");
        settings = new Button("Settings");
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
        dayBtn = new Button("Day");
        nightBtn = new Button ("Night");
        weatherForecastvb = new VBox();
        ArrayList<Period> forecast = WeatherAPI.getForecast("LOT", 77, 70);


        curr = new Label();
        if (forecast != null) {
            Period today = forecast.get(0);
            String nameStr = today.name;
            currentWeather = new Label(today.temperature + "°  \n");
            location = new Label("Chicago");
            sftLab = new Label("" + today.name);
            location.setStyle("-fx-font-size: 25px; -fx-font-family: 'Georgia';");
            dayLocvb = new VBox (location, sftLab);
            curr.setText(forecast.get(0).shortForecast);
            curr.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
            currentWeather.setStyle("-fx-font-size: 96px; -fx-weight: bold;");
            curr.setWrapText(true);
            MainController controller = new MainController(forecast, weatherForecastvb);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        }else{
                curr.setText("Could not load weather");
            }

        sevDayhb = new DayHbox(dayBtn,sevDayL, nightBtn);
        sevDayhb.setMargin(nightBtn, new Insets(0,0,0,125));
        sevDayhb.setPrefSize(10,10);


        hb = new HBox(settings);
        scrollPane = new ScrollPane();
        hb.setAlignment(Pos.TOP_RIGHT);
        sevDay = new VBox(sevDayhb,scrollPane);
        currentDay = new VBox(dayLocvb, currentWeather, curr);
        currentDay.setMargin(location, new Insets(0,0,0,100));
        currentDay.setMargin(sftLab, new Insets(0,0,0,125));
        currentDay.setMargin(curr, new Insets(-25,0,0,0));
        //vb = new VBox( currentDay, searchTF, searchBtn);
        vb = new VBox( currentDay, cityDropdown, searchBtn);
        vb1 = new VBox(sevDay);
        vb.setMargin(currentWeather, new Insets(-25,0,0,90));     //Centers the large number
        vb.setMargin(searchBtn, new Insets(10,0,125,125));
        vb1.setMargin(sevDay,new Insets(-110,0,0,0));
        vb1.setStyle("-fx-background-color: white; -fx-background-radius: 15 15 15 15;");
        bp = new BorderPane();
        bp.setStyle("-fx-background-color: white;");
        bp.setTop(hb);
        bp.setCenter(vb);
        bp.setPadding(new Insets(20));
        bp.setBottom(vb1);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-radius: 15 15 15 15;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(280);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setFitToWidth(true);

        return new Scene(bp,360,640);
    }

    public Scene buildSF(){
        backbtn = new Button("Back");
        settings = new Button("Settings");
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
        dayBtn = new Button("Day");
        nightBtn = new Button ("Night");
        weatherForecastvb = new VBox();
        ArrayList<Period> forecast = WeatherAPI.getForecast("MTR", 85, 105);


        curr = new Label();
        if (forecast != null) {
            Period today = forecast.get(0);
            String nameStr = today.name;
            currentWeather = new Label(today.temperature + "°  \n");
            location = new Label("San Francisco");
            sftLab = new Label("" + today.name);
            location.setStyle("-fx-font-size: 25px; -fx-font-family: 'Georgia';");
            dayLocvb = new VBox (location, sftLab);
            curr.setText(forecast.get(0).shortForecast);
            curr.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
            currentWeather.setStyle("-fx-font-size: 96px; -fx-weight: bold;");
            curr.setWrapText(true);
            MainController controller = new MainController(forecast, weatherForecastvb);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        }else{
            curr.setText("Could not load weather");
        }

        sevDayhb = new DayHbox(dayBtn,sevDayL, nightBtn);
        sevDayhb.setMargin(nightBtn, new Insets(0,0,0,125));
        sevDayhb.setPrefSize(10,10);

        hb = new HBox(backbtn, settings);
        scrollPane = new ScrollPane();
        hb.setAlignment(Pos.TOP_RIGHT);
        sevDay = new VBox(sevDayhb,scrollPane);
        currentDay = new VBox(dayLocvb, currentWeather, curr);
        currentDay.setMargin(location, new Insets(0,0,0,100));
        currentDay.setMargin(sftLab, new Insets(0,0,0,125));
        currentDay.setMargin(curr, new Insets(-25,0,0,0));
        vb = new VBox(currentDay);
        vb1 = new VBox(sevDay);
        vb.setMargin(currentWeather, new Insets(-25,0,0,90));     //Centers the large number
        vb.setMargin(searchBtn, new Insets(10,0,125,125));
        vb1.setMargin(sevDay,new Insets(-110,0,0,0));
        vb1.setStyle("-fx-background-color: white; -fx-background-radius: 15 15 15 15;");
        bp = new BorderPane();
        bp.setStyle("-fx-background-color: white;");
        bp.setTop(hb);
        bp.setCenter(vb);
        bp.setPadding(new Insets(20));
        bp.setBottom(vb1);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-radius: 15 15 15 15;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(280);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setFitToWidth(true);

        return new Scene(bp,360,640);
    }

    public Scene buildNYC(){
        backbtn = new Button("Back");
        settings = new Button("Settings");
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
        dayBtn = new Button("Day");
        nightBtn = new Button ("Night");
        weatherForecastvb = new VBox();
        ArrayList<Period> forecast = WeatherAPI.getForecast("OKX", 33, 37);

        curr = new Label();
        if (forecast != null) {
            Period today = forecast.get(0);
            String nameStr = today.name;
            currentWeather = new Label(today.temperature + "°  \n");
            location = new Label("New York City");
            sftLab = new Label("" + nameStr);
            location.setStyle("-fx-font-size: 25px; -fx-font-family: 'Georgia';");
            dayLocvb = new VBox (location, sftLab);
            curr.setText(forecast.get(0).shortForecast);
            curr.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
            currentWeather.setStyle("-fx-font-size: 96px; -fx-weight: bold;");
            curr.setWrapText(true);
            MainController controller = new MainController(forecast, weatherForecastvb);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        }else{
            curr.setText("Could not load weather");
        }

        sevDayhb = new DayHbox(dayBtn,sevDayL, nightBtn);
        sevDayhb.setMargin(nightBtn, new Insets(0,0,0,125));
        sevDayhb.setPrefSize(10,10);


        hb = new HBox(backbtn,settings);
        scrollPane = new ScrollPane();
        hb.setAlignment(Pos.TOP_RIGHT);
        sevDay = new VBox(sevDayhb,scrollPane);
        currentDay = new VBox(dayLocvb, currentWeather, curr);
        currentDay.setMargin(location, new Insets(0,0,0,100));
        currentDay.setMargin(sftLab, new Insets(0,0,0,125));
        currentDay.setMargin(curr, new Insets(-25,0,0,0));
        vb = new VBox(currentDay);
        vb1 = new VBox(sevDay);
        vb.setMargin(currentWeather, new Insets(-25,0,0,90));     //Centers the large number
        vb.setMargin(searchBtn, new Insets(10,0,125,125));
        vb1.setMargin(sevDay,new Insets(-110,0,0,0));
        vb1.setStyle("-fx-background-color: white; -fx-background-radius: 15 15 15 15;");
        bp = new BorderPane();
        bp.setStyle("-fx-background-color: white;");
        bp.setTop(hb);
        bp.setCenter(vb);
        bp.setPadding(new Insets(20));
        bp.setBottom(vb1);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-radius: 15 15 15 15;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(280);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setFitToWidth(true);

        return new Scene(bp,360,640);
    }
    public Scene buildAustin(){
        backbtn = new Button("Back");
        settings = new Button("Settings");
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
        dayBtn = new Button("Day");
        nightBtn = new Button ("Night");
        weatherForecastvb = new VBox();
        ArrayList<Period> forecast = WeatherAPI.getForecast("EWX", 156, 90);

        curr = new Label();
        if (forecast != null) {
            Period today = forecast.get(0);
            String nameStr = today.name;
            currentWeather = new Label(today.temperature + "°  \n");
            location = new Label("Austin");
            sftLab = new Label("" + nameStr);
            location.setStyle("-fx-font-size: 25px; -fx-font-family: 'Georgia';");
            dayLocvb = new VBox (location, sftLab);
            curr.setText(forecast.get(0).shortForecast);
            curr.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
            currentWeather.setStyle("-fx-font-size: 96px; -fx-weight: bold;");
            curr.setWrapText(true);
            MainController controller = new MainController(forecast, weatherForecastvb);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        }else{
            curr.setText("Could not load weather");
        }

        sevDayhb = new DayHbox(dayBtn,sevDayL, nightBtn);
        sevDayhb.setMargin(nightBtn, new Insets(0,0,0,125));
        sevDayhb.setPrefSize(10,10);

        hb = new HBox(backbtn, settings);
        scrollPane = new ScrollPane();
        hb.setAlignment(Pos.TOP_RIGHT);
        sevDay = new VBox(sevDayhb,scrollPane);
        currentDay = new VBox(dayLocvb, currentWeather, curr);
        currentDay.setMargin(location, new Insets(0,0,0,100));
        currentDay.setMargin(sftLab, new Insets(0,0,0,125));
        currentDay.setMargin(curr, new Insets(-25,0,0,0));
        vb = new VBox(currentDay);
        vb1 = new VBox(sevDay);
        vb.setMargin(currentWeather, new Insets(-25,0,0,90));     //Centers the large number
        vb.setMargin(searchBtn, new Insets(10,0,125,125));
        vb1.setMargin(sevDay,new Insets(-110,0,0,0));
        vb1.setStyle("-fx-background-color: white; -fx-background-radius: 15 15 15 15;");
        bp = new BorderPane();
        bp.setStyle("-fx-background-color: white;");
        bp.setTop(hb);
        bp.setCenter(vb);
        bp.setPadding(new Insets(20));
        bp.setBottom(vb1);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-radius: 15 15 15 15;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(280);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scrollPane.setFitToWidth(true);

        return new Scene(bp,360,640);
    }


    public void searchCity(Stage primaryStage, Scene homeScene,Button searchBtn, ComboBox<String> cityDropdown){
        this.searchBtn = searchBtn;
        searchBtn.setOnAction(e->{
            String selectedCity = cityDropdown.getValue();
                if (selectedCity != null) {
                    if (selectedCity.contains("San Francisco, CA")) {
                        primaryStage.setScene(buildSF());
                        backbtn.setOnAction(t -> primaryStage.setScene(homeScene));
                    } else if (selectedCity.contains("New York, NY")) {
                        primaryStage.setScene(buildNYC());
                        backbtn.setOnAction(t->primaryStage.setScene(homeScene));
                    }else if (selectedCity.contains("Austin, TX")) {
                        primaryStage.setScene(buildAustin());
                        backbtn.setOnAction(t->primaryStage.setScene(homeScene));
                    }
                } else {
                    cityDropdown.setPromptText("Select a city to see");
                }
        });
    }

    public Button getSettings(){return settings;}
    public Label getCurr(){return curr;}
    public TextField getSearchTF(){return searchTF;}
    public Button getBackbtn() {return backbtn;}
    public Button getSearchBtn() {return searchBtn;}
    public ComboBox<String> getCityDropdown(){return cityDropdown;}
}