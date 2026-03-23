import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import weather.Period;
import java.util.ArrayList;
import javafx.scene.layout.VBox;

public class MainController{
    private boolean isDay = true;
    private Button dayBtn;
    private Button nightBtn, searchBtn;
    private VBox weatherForecastvb;
    private ArrayList<Period> forecast;

    private final int sunrise = 7; //hardcoded sunrise and sunset LOLLLLL
    private final int sunset = 19;

    public MainController(ArrayList<Period> forecast, VBox weatherForecastvb) {
        this.forecast = forecast;
        this.weatherForecastvb = weatherForecastvb;
    }
    /*Sets logic for the day and night buttons, when oone is clicked set isday on or off then call build forecast below*/
    public void setDay(Button dayBtn, Button nightBtn) {
        this.dayBtn = dayBtn;
        this.nightBtn = nightBtn;

        dayBtn.setOnAction(e -> {
            isDay = true;
            buildForecast();
        });
        nightBtn.setOnAction(e -> {
            isDay = false;
            buildForecast();
        });
    }

    public void buildForecast(){
        weatherForecastvb.getChildren().clear();
        if (forecast.get(0).name.contains("night")){ //This checks if the current forecast is night (totally could change it to hourly but too lazy)
            if(isDay()) { //day is clicked
                weatherForecastvb.getChildren().addAll( //adds all the current day forecasts to the vb in the 7 day scrollPane
                        new DayHbox(forecast.get(1),this),
                new DayHbox(forecast.get(3), this),
                new DayHbox(forecast.get(5), this),
                new DayHbox(forecast.get(7), this),
                new DayHbox(forecast.get(8), this),
                new DayHbox(forecast.get(11), this),
                new DayHbox(forecast.get(13), this));
            }
            else{ //night is clicked
                weatherForecastvb.getChildren().addAll( //add all the night forecasts
                        new DayHbox(forecast.get(2), this),
                new DayHbox(forecast.get(4), this),
                new DayHbox(forecast.get(6), this),
                new DayHbox(forecast.get(8), this),
                new DayHbox(forecast.get(10), this),
                new DayHbox(forecast.get(12), this));
                // there is one missing hbox here because the API does not go that far
            }
        }
        else {
            if (isDay()) { //day is clicked
                weatherForecastvb.getChildren().addAll(
                  new DayHbox(forecast.get(0), this),
                new DayHbox(forecast.get(2), this),
                new DayHbox(forecast.get(4), this),
                new DayHbox(forecast.get(6), this),
                new DayHbox(forecast.get(8), this),
                new DayHbox(forecast.get(10), this),
                new DayHbox(forecast.get(12), this));
            }
            else { //night is clicked
                weatherForecastvb.getChildren().addAll(
                          new DayHbox(forecast.get(1), this),
                        new DayHbox(forecast.get(3), this),
                        new DayHbox(forecast.get(5), this),
                        new DayHbox(forecast.get(7), this),
                        new DayHbox(forecast.get(8), this),
                        new DayHbox(forecast.get(11), this),
                        new DayHbox(forecast.get(13), this));
            }
        }
    }
    /*Set the background based on the weather and current time */
    public String getBackground(String hourlyShortForecast, int hourlyIsDay){
        String hourlyShort =  hourlyShortForecast.toLowerCase();
            if (hourlyShort.contains("thunder") || hourlyShort.contains("storm")) {
                return "-fx-background-color: linear-gradient(to bottom, #1c1c2e, #2d2d44);";//Creates a gradient that starts with the first hex at the top and second on the bottom
            }
            if (hourlyShort.contains("heavy rain") || hourlyShort.contains("heavy shower")) {
                if (hourlyIsDay > sunrise  &&  hourlyIsDay < sunset) { //these if statements are basically if its day or not
                    return "-fx-background-color: linear-gradient(to bottom, #3a4a5c, #5a6a7c);";
                } else {
                    return "-fx-background-color: linear-gradient(to bottom, #1a2a3a, #2a3a4a);";
                }
            }
            if (hourlyShort.contains("light rain") || hourlyShort.contains("drizzle") || hourlyShort.contains("light shower")) {
                return "-fx-background-color: linear-gradient(to bottom, #4a5a6a, #6a7a8a);";
            }
            if (hourlyShort.contains("rain") || hourlyShort.contains("shower")) {
                return "-fx-background-color: linear-gradient(to bottom, #3a4a5c, #5a6a7c);";
            }
            if (hourlyShort.contains("snow") || hourlyShort.contains("flurries")) {
                if (hourlyIsDay > sunrise  &&  hourlyIsDay < sunset) {
                    return "-fx-background-color: linear-gradient(to bottom, #a8c0cc, #d0e4f0);";
                } else {
                    return "-fx-background-color: linear-gradient(to bottom, #2a3a4a, #4a5a6a);";
                }
            }
            if (hourlyShort.contains("partly cloudy") || hourlyShort.contains("partly sunny")) {
                if (hourlyIsDay > sunrise  &&  hourlyIsDay < sunset) {
                    return "-fx-background-color: linear-gradient(to bottom, #4A90D9, #87CEEB);";
                } else {
                    return "-fx-background-color: linear-gradient(to bottom, #1a2a3a, #2a3a4a);";
                }
            }
            if (hourlyShort.contains("cloudy")) {
                if (hourlyIsDay > sunrise  &&  hourlyIsDay < sunset) {
                    return "-fx-background-color: linear-gradient(to bottom, #6a7a8a, #9aaaba);";
                } else {
                    return "-fx-background-color: linear-gradient(to bottom, #2a2a3a, #3a3a4a);";
                }
            }
            if (hourlyShort.contains("fog") || hourlyShort.contains("haze")) {
                return "-fx-background-color: linear-gradient(to bottom, #8a9aaa, #aabaca);";
            }
            if (hourlyShort.contains("wind") || hourlyShort.contains("breezy")) {
                if (hourlyIsDay > sunrise  &&  hourlyIsDay < sunset) {
                    return "-fx-background-color: linear-gradient(to bottom, #4A90D9, #87CEEB);";
                } else {
                    return "-fx-background-color: linear-gradient(to bottom, #0d1b2a, #1b2a3b);";
                }
            }
            if (hourlyShort.contains("sunny") || hourlyShort.contains("clear")) {
                if (hourlyIsDay > sunrise  &&  hourlyIsDay < sunset) {
                    return "-fx-background-color: linear-gradient(to bottom, #2980b9, #6dd5fa);";
                } else {
                    return "-fx-background-color: linear-gradient(to bottom, #0d1b2a, #1b2a3b);";
                }
            }
            if (hourlyIsDay > sunrise  &&  hourlyIsDay < sunset) {
                return "-fx-background-color: linear-gradient(to bottom, #4A90D9, #87CEEB);";
            } else {
                return "-fx-background-color: linear-gradient(to bottom, #0d1b2a, #1b2a3b);";
            }
    }

    public boolean isDay() {return isDay;}
}