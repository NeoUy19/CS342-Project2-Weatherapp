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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import weather.Period;
import weather.WeatherAPI;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
public class MainUI {
    private VBox vb, vb1, weatherForecastvb, currentDay, sevDay, dayLocvb, hourVB;                //Declared variables
    private HBox hb, hb2, sevDayhb, hourHB, hourlyHB;
    private Button settings, searchBtn, dayBtn, nightBtn, backbtn;
    private BorderPane bp, bp2;
    private Label currentWeather, location, curr, sevDayL, sftLab, hourTemp, hourTime;
    private ScrollPane scrollPane, hourScroll;
    private MenuButton cityDropdown;
    private ImageView hourIcons;

    private final int sunrise = 7;
    private final int sunset = 19;

    /*ITS CHICAGO*/
    public Scene buildHome() {
        cityDropdown = dropdownMenu(new String[]{"San Francisco, CA", "New York, NY", "Austin, TX"});
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 13px; -fx-font-family: 'Georgia'; -fx-text-fill: white;");
        ImageView dayIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/sunny.png")));
        dayIcon.setFitWidth(25);
        dayIcon.setFitHeight(25);
        dayIcon.setPreserveRatio(true);
        ImageView nightIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/clearNight.png")));
        nightIcon.setFitWidth(25);
        nightIcon.setFitHeight(25);
        nightIcon.setPreserveRatio(true);


        dayBtn = new Button();
        dayBtn.setGraphic(dayIcon);
        dayBtn.setStyle("-fx-background-color: transparent;");
        nightBtn = new Button();
        nightBtn.setGraphic(nightIcon);
        nightBtn.setStyle("-fx-background-color: transparent;");
        weatherForecastvb = new VBox();
        weatherForecastvb.setSpacing(8);
        ArrayList<Period> forecast = WeatherAPI.getForecast("LOT", 77, 70);
        ArrayList<Period> hourlyForecast = MyWeatherAPI.getHourlyForecast("LOT", 77, 70);
        ScrollPane hourScroll = buildHourlyScroll(hourlyForecast);
        MainController controller = new MainController(forecast, weatherForecastvb);

        if (forecast != null) {
            currentDay = buildCurrDay("Chicago",hourlyForecast);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        } else {
            curr.setText("Could not load weather");
        }

        sevDayhb = new DayHbox(dayBtn, sevDayL, nightBtn);
        sevDayhb.setAlignment(Pos.CENTER);
        sevDayhb.setPrefSize(360, 50);

        hb = new HBox(cityDropdown);
        hb.setAlignment(Pos.TOP_RIGHT);
        hb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        scrollPane = new ScrollPane();
        hb.setAlignment(Pos.TOP_RIGHT);
        VBox hourlyForecastVB = buildHourlyForecast(hourlyForecast,hourScroll);
        sevDay = new VBox(hourlyForecastVB, sevDayhb, scrollPane);
        sevDay.setSpacing(5);
        sevDay.setPadding(new Insets(10, 5, 5, 5));

        vb = new VBox(currentDay);
        vb.setAlignment(Pos.CENTER);
        vb1 = new VBox(sevDay);
        vb1.setStyle("-fx-background-color: transparent; -fx-background-radius: 15 15 15 15;");
        bp = new BorderPane();
        int currHour = Integer.valueOf(hourlyForecast.get(0).startTime.toString().substring(11, 13));
        bp.setStyle(controller.getBackground(hourlyForecast.get(0).shortForecast, currHour));
        bp.setTop(hb);
        bp.setCenter(vb);
        vb.setMaxWidth(Double.MAX_VALUE);
        vb.setFillWidth(true);
        BorderPane.setAlignment(vb, Pos.CENTER);
        BorderPane.setMargin(vb, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(vb1, new Insets(0, 20, 20, 20));
        BorderPane.setMargin(hb, new Insets(0));
        bp.setBottom(vb1);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(180);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        return new Scene(bp, 360, 640);
    }
    /*Create the san fran scene*/
    public Scene buildSF() {
        cityDropdown = dropdownMenu(new String[]{"Chicago, IL", "New York, NY", "Austin, TX"});
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 13px; -fx-font-family: 'Georgia'; -fx-text-fill: white;");
        ImageView dayIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/sunny.png")));
        dayIcon.setFitWidth(25);
        dayIcon.setFitHeight(25);
        dayIcon.setPreserveRatio(true);
        ImageView nightIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/clearNight.png")));
        nightIcon.setFitWidth(25);
        nightIcon.setFitHeight(25);
        nightIcon.setPreserveRatio(true);

        dayBtn = new Button();
        dayBtn.setGraphic(dayIcon);
        dayBtn.setStyle("-fx-background-color: transparent;");
        nightBtn = new Button();
        nightBtn.setGraphic(nightIcon);
        nightBtn.setStyle("-fx-background-color: transparent;");
        weatherForecastvb = new VBox();
        weatherForecastvb.setSpacing(8);
        ArrayList<Period> forecast = WeatherAPI.getForecast("MTR", 85, 105);
        ArrayList<Period> hourlyForecast = MyWeatherAPI.getHourlyForecast("MTR", 85, 105);
        ScrollPane hourScroll = buildHourlyScroll(hourlyForecast);


        MainController controller = new MainController(forecast, weatherForecastvb);
        if (forecast != null) {
            currentDay = buildCurrDay("San Francisco",hourlyForecast);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        } else {
            curr = new Label("Could not load weather");
            currentDay = new VBox(curr);
        }

        sevDayhb = new DayHbox(dayBtn, sevDayL, nightBtn);
        sevDayhb.setAlignment(Pos.CENTER);
        sevDayhb.setPrefSize(360, 50);

        hb = new HBox(cityDropdown);
        hb.setAlignment(Pos.TOP_RIGHT);
        hb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        scrollPane = new ScrollPane();

        VBox hourlyForecastVB = buildHourlyForecast(hourlyForecast,hourScroll);

        sevDay = new VBox(hourlyForecastVB, sevDayhb, scrollPane);
        sevDay.setSpacing(8);
        sevDay.setPadding(new Insets(10, 5, 5, 5));

        vb = new VBox(currentDay);
        vb.setAlignment(Pos.CENTER);
        vb1 = new VBox(sevDay);
        vb1.setStyle("-fx-background-color: transparent;");
        bp = new BorderPane();
        int currHour = Integer.valueOf(hourlyForecast.get(0).startTime.toString().substring(11, 13));
        bp.setStyle(controller.getBackground(hourlyForecast.get(0).shortForecast, currHour));
        bp.setTop(hb);
        bp.setCenter(vb);
        vb.setMaxWidth(Double.MAX_VALUE);
        vb.setFillWidth(true);
        bp.setBottom(vb1);
        BorderPane.setMargin(vb, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(vb1, new Insets(0, 20, 20, 20));
        BorderPane.setMargin(hb, new Insets(0));
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(180);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        return new Scene(bp, 360, 640);
    }
    /*Create the NY scene*/
    public Scene buildNYC() {
        cityDropdown = dropdownMenu(new String[]{"Chicago, IL", "San Francisco, CA", "Austin, TX"});
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 13px; -fx-font-family: 'Georgia'; -fx-text-fill: white;");
        ImageView dayIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/sunny.png")));
        dayIcon.setFitWidth(25);
        dayIcon.setFitHeight(25);
        dayIcon.setPreserveRatio(true);
        ImageView nightIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/clearNight.png")));
        nightIcon.setFitWidth(25);
        nightIcon.setFitHeight(25);
        nightIcon.setPreserveRatio(true);

        dayBtn = new Button();
        dayBtn.setGraphic(dayIcon);
        dayBtn.setStyle("-fx-background-color: transparent;");
        nightBtn = new Button();
        nightBtn.setGraphic(nightIcon);
        nightBtn.setStyle("-fx-background-color: transparent;");
        weatherForecastvb = new VBox();
        weatherForecastvb.setSpacing(8);
        ArrayList<Period> forecast = WeatherAPI.getForecast("OKX", 33, 37);
        ArrayList<Period> hourlyForecast = MyWeatherAPI.getHourlyForecast("OKX", 33, 37);
        ScrollPane hourScroll = buildHourlyScroll(hourlyForecast);

        MainController controller = new MainController(forecast, weatherForecastvb);
        if (forecast != null) {
            currentDay = buildCurrDay("New York",hourlyForecast);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        } else {
            curr = new Label("Could not load weather");
            currentDay = new VBox(curr);
        }

        sevDayhb = new DayHbox(dayBtn, sevDayL, nightBtn);
        sevDayhb.setAlignment(Pos.CENTER);
        sevDayhb.setPrefSize(360, 50);

        hb = new HBox(cityDropdown);
        hb.setAlignment(Pos.TOP_RIGHT);
        hb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        scrollPane = new ScrollPane();

        VBox hourlyForecastVB = buildHourlyForecast(hourlyForecast,hourScroll);

        sevDay = new VBox(hourlyForecastVB, sevDayhb, scrollPane);
        sevDay.setSpacing(8);
        sevDay.setPadding(new Insets(10, 5, 5, 5));

        vb = new VBox(currentDay);
        vb.setAlignment(Pos.CENTER);
        vb1 = new VBox(sevDay);
        vb1.setStyle("-fx-background-color: transparent;");
        bp = new BorderPane();
        int currHour = Integer.valueOf(hourlyForecast.get(0).startTime.toString().substring(11, 13));
        bp.setStyle(controller.getBackground(hourlyForecast.get(0).shortForecast, currHour));
        bp.setTop(hb);
        bp.setCenter(vb);
        vb.setMaxWidth(Double.MAX_VALUE);
        vb.setFillWidth(true);
        bp.setBottom(vb1);
        BorderPane.setMargin(vb, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(vb1, new Insets(0, 20, 20, 20));
        BorderPane.setMargin(hb, new Insets(0));
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(180);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        return new Scene(bp, 360, 640);
    }
    /*Create austin scene*/
    public Scene buildAustin() {
        cityDropdown = dropdownMenu(new String[]{"Chicago, IL", "San Francisco, CA", "New York, NY"});
        sevDayL = new Label("7-DAY FORECAST");
        sevDayL.setStyle("-fx-font-size: 13px; -fx-font-family: 'Georgia'; -fx-text-fill: white;");
        ImageView dayIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/sunny.png")));
        dayIcon.setFitWidth(25);
        dayIcon.setFitHeight(25);
        dayIcon.setPreserveRatio(true);
        ImageView nightIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/clearNight.png")));
        nightIcon.setFitWidth(25);
        nightIcon.setFitHeight(25);
        nightIcon.setPreserveRatio(true);

        dayBtn = new Button();
        dayBtn.setGraphic(dayIcon);
        dayBtn.setStyle("-fx-background-color: transparent;");
        nightBtn = new Button();
        nightBtn.setGraphic(nightIcon);
        nightBtn.setStyle("-fx-background-color: transparent;");
        weatherForecastvb = new VBox();
        weatherForecastvb.setSpacing(8);
        ArrayList<Period> forecast = WeatherAPI.getForecast("EWX", 156, 90);
        ArrayList<Period> hourlyForecast = MyWeatherAPI.getHourlyForecast("EWX", 156, 90);
        ScrollPane hourScroll = buildHourlyScroll(hourlyForecast);


        MainController controller = new MainController(forecast, weatherForecastvb);
        if (forecast != null) {
            currentDay = buildCurrDay("Austin",hourlyForecast);
            controller.setDay(dayBtn, nightBtn);
            controller.buildForecast();
        } else {
            curr = new Label("Could not load weather");
            currentDay = new VBox(curr);
        }

        sevDayhb = new DayHbox(dayBtn, sevDayL, nightBtn);
        sevDayhb.setAlignment(Pos.CENTER);
        sevDayhb.setPrefSize(360, 50);

        hb = new HBox(cityDropdown);
        hb.setAlignment(Pos.TOP_RIGHT);
        hb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        scrollPane = new ScrollPane();

        VBox hourlyForecastVB = buildHourlyForecast(hourlyForecast,hourScroll);

        sevDay = new VBox(hourlyForecastVB, sevDayhb, scrollPane);
        sevDay.setSpacing(8);
        sevDay.setPadding(new Insets(10, 5, 5, 5));

        vb = new VBox(currentDay);
        vb.setAlignment(Pos.CENTER);
        vb1 = new VBox(sevDay);
        vb1.setStyle("-fx-background-color: transparent;");
        bp = new BorderPane();
        int currHour = Integer.valueOf(hourlyForecast.get(0).startTime.toString().substring(11, 13));
        bp.setStyle(controller.getBackground(hourlyForecast.get(0).shortForecast, currHour));
        bp.setTop(hb);
        bp.setCenter(vb);
        vb.setMaxWidth(Double.MAX_VALUE);
        vb.setFillWidth(true);
        bp.setBottom(vb1);
        BorderPane.setMargin(vb, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(vb1, new Insets(0, 20, 20, 20));
        BorderPane.setMargin(hb, new Insets(0));
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setContent(weatherForecastvb);
        scrollPane.setMaxHeight(180);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        return new Scene(bp, 360, 640);
    }
    /*We are painfully aware that each of these cities could be put into the same
     constructor but hard coding and copy and paste was easier*/

    /*Create the dropdown menu on the top right that allows switching cities
    was orginally a combobox but combobox didnt allow an icon*/
    private MenuButton dropdownMenu(String[] cities) {
        ImageView mapIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/map.png")));
        mapIcon.setFitWidth(20);
        mapIcon.setFitHeight(20);
        mapIcon.setPreserveRatio(true);

        MenuButton menuButton = new MenuButton();
        menuButton.setGraphic(mapIcon);
        menuButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 0;"
        );

        for (String city : cities) { //this loop makes each city an item in its dropbox
            MenuItem item = new MenuItem(city);
            menuButton.getItems().add(item);
        }

        return menuButton;
    }
    /*Creates the hourly forecast scroller*/
    private ScrollPane buildHourlyScroll(ArrayList<Period> hourlyForecast) {
        ScrollPane hourScroll = new ScrollPane();
        if (hourlyForecast != null) {
            HBox hourHbox = new HBox(20); //space out each box
            for (int i = 0; i < 24; i++) { //loop the entire 24 hours adding the time (in military idk how to change)
                Period hour = hourlyForecast.get(i);
                Label hourTemp = new Label(hour.temperature + "°");
                hourTemp.setStyle("-fx-text-fill: white;");

                Label hourTime;
                if (i == 0) { //if were at 0 dont add the time just say current
                    hourTime = new Label("Current");

                } else {
                    hourTime = new Label(hour.startTime.toString().substring(11, 13)); //11 and 13 are indices of the hours of the huge line of text startTime prints out
                }
                hourTime.setStyle("-fx-text-fill: white;");
                /*Set images based on the hourly weather*/
                String hourlyShortForecast = hour.shortForecast.toLowerCase();
                String imagePath;
                if (hourlyShortForecast.contains("thunder")) {
                    imagePath = "/images/thunder.png";
                } else if (hourlyShortForecast.contains("storm")) {
                    imagePath = "/images/storm.png";
                } else if (hourlyShortForecast.contains("heavy rain") || hourlyShortForecast.contains("heavy shower")) {
                    if (Integer.valueOf(hour.startTime.toString().substring(11, 13)) < sunset && Integer.valueOf(hour.startTime.toString().substring(11, 13)) >= sunrise) {
                        imagePath = "/images/heavy-rain.png";
                    } else {
                        imagePath = "/images/nightHeavyRain.png";
                    }
                } else if (hourlyShortForecast.contains("light rain") || hourlyShortForecast.contains("drizzle") || hourlyShortForecast.contains("light shower")) {
                    imagePath = "/images/light-rain.png";
                } else if (hourlyShortForecast.contains("rain") || hourlyShortForecast.contains("shower")) {
                    imagePath = "/images/rain.png";
                } else if (hourlyShortForecast.contains("snow") || hourlyShortForecast.contains("flurries")) {
                    if (Integer.valueOf(hour.startTime.toString().substring(11, 13)) < sunset && Integer.valueOf(hour.startTime.toString().substring(11, 13)) >= sunrise) {
                        imagePath = "/images/snowDay.png";
                    } else {
                        imagePath = "/images/snowNight.png";
                    }
                } else if (hourlyShortForecast.contains("partly cloudy") || hourlyShortForecast.contains("partly sunny")) {
                    if (Integer.valueOf(hour.startTime.toString().substring(11, 13)) < sunset && Integer.valueOf(hour.startTime.toString().substring(11, 13)) >= sunrise) {
                        imagePath = "/images/partly-cloudDay.png";
                    } else {
                        imagePath = "/images/cloudyNight.png";
                    }
                } else if (hourlyShortForecast.contains("cloudy")) {
                    if (Integer.valueOf(hour.startTime.toString().substring(11, 13)) < sunset && Integer.valueOf(hour.startTime.toString().substring(11, 13)) >= sunrise) {
                        imagePath = "/images/cloudy.png";
                    } else {
                        imagePath = "/images/cloudy.png";
                    }
                } else if (hourlyShortForecast.contains("fog") || hourlyShortForecast.contains("haze")) {
                    imagePath = "/images/hazefog.png";
                } else if (hourlyShortForecast.contains("wind") || hourlyShortForecast.contains("breezy")) {
                    imagePath = "/images/windy.png";
                } else if (hourlyShortForecast.contains("sunny") || hourlyShortForecast.contains("clear")) {
                    if (Integer.valueOf(hour.startTime.toString().substring(11, 13)) < sunset && Integer.valueOf(hour.startTime.toString().substring(11, 13)) >= sunrise) {
                        imagePath = "/images/sunny.png";
                    } else {
                        imagePath = "/images/clearNight.png";
                    }
                } else {
                    imagePath = "/images/sunny.png";
                }
                ImageView hourIcons = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
                hourIcons.setFitWidth(15);
                hourIcons.setFitHeight(15);
                hourIcons.setPreserveRatio(true);
                VBox hourVB = new VBox(hourTemp, hourIcons, hourTime); //add everything to a vbox and then add to the hbox
                hourVB.setAlignment(Pos.CENTER);
                hourVB.setSpacing(4);
                hourHbox.getChildren().add(hourVB);
            }
            hourScroll.setContent(hourHbox);
            hourScroll.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-background-radius: 10;");
            hourScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            hourScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            hourScroll.setFitToHeight(true);
        }
        return hourScroll;
    }
    /*Build and style the entire hourly forecast, everythign between the current temp and 7 day forecast*/
    private VBox buildHourlyForecast(ArrayList<Period> hourlyForecast, ScrollPane hourlyScroll){
        Label hourly = new Label("Hourly Forecast");
        hourly.setStyle("-fx-text-fill: white; -fx-font-family: 'Georgia';");
        Label hourForecast = new Label(hourlyForecast.get(0).shortForecast);
        hourForecast.setStyle("-fx-text-fill: white; -fx-font-family: 'Georgia';");
        VBox hourlyBox = new VBox(hourly, hourForecast, hourlyScroll);
        hourlyBox.setSpacing(5);
        hourlyBox.setPadding(new Insets(8, 8, 8, 8));
        hourlyBox.setStyle("-fx-background-color: rgba(255,255,255,0.15);" +
                " -fx-border-color: rgba(255,255,255,0.4);" +
                " -fx-border-width: 1;" +
                "-fx-border-radius: 15 15 15 15;" +
                " -fx-background-radius: 15 15 15 15;");
        return hourlyBox;
    }
    /*Builds and styles the top of the screen (7*/
    private VBox buildCurrDay(String cityName,ArrayList<Period> hourlyForecast){
        Period today = hourlyForecast.get(0);
        currentWeather = new Label(today.temperature + "°");
        location = new Label(cityName);
        sftLab = new Label(today.shortForecast);
        sftLab.setStyle("-fx-font-family: 'Georgia'; -fx-text-fill: white;");
        location.setStyle("-fx-font-size: 25px; -fx-font-family: 'Georgia'; -fx-text-fill: white;");
        currentWeather.setStyle("-fx-font-size: 96px; -fx-text-fill: white;");
        currentWeather.setAlignment(Pos.CENTER);
        currentWeather.setMaxWidth(Double.MAX_VALUE);
        dayLocvb = new VBox(location, sftLab);
        dayLocvb.setAlignment(Pos.CENTER);
        VBox currDay = new VBox(dayLocvb,currentWeather);
        currDay.setAlignment(Pos.CENTER);
        currDay.setSpacing(5);
        return currDay;
    }
    /*Allows each item in the menu to be clickable and go to their respective scenes*/
    public void searchCity(Stage primaryStage, Scene homeScene, MenuButton cityDropdown,
                           Scene chi, Scene sf, Scene ny, Scene aus,
                           MainUI Chi, MainUI SanF, MainUI NewY, MainUI Aus){
        for (MenuItem item : cityDropdown.getItems()) { //set an on action for each item in the menu based on cityDropdown
            item.setOnAction(e -> {
                String city = item.getText();
                if (city.contains("San Francisco, CA")) {
                    primaryStage.setScene(sf);
                } else if (city.contains("New York, NY")) {
                    primaryStage.setScene(ny);
                } else if (city.contains("Austin, TX")) {
                    primaryStage.setScene(aus);
                } else if (city.contains("Chicago, IL")) {
                    primaryStage.setScene(chi);
                }
            });
        }
    }


    public Label getCurr(){return curr;}
    public Button getBackbtn() {return backbtn;}
    public MenuButton getCityDropdown(){return cityDropdown;}
}