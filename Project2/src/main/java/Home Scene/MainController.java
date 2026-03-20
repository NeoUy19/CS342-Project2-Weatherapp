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


    public MainController(ArrayList<Period> forecast, VBox weatherForecastvb) {
        this.forecast = forecast;
        this.weatherForecastvb = weatherForecastvb;
    }
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
        if (forecast.get(0).name.contains("night")){
            if(isDay()) { //day is clicked
                weatherForecastvb.getChildren().addAll(
                        new DayHbox(forecast.get(1),this),
                new DayHbox(forecast.get(3), this),
                new DayHbox(forecast.get(5), this),
                new DayHbox(forecast.get(7), this),
                new DayHbox(forecast.get(8), this),
                new DayHbox(forecast.get(11), this),
                new DayHbox(forecast.get(13), this));
            }
            else{
                weatherForecastvb.getChildren().addAll(
                        new DayHbox(forecast.get(2), this),
                new DayHbox(forecast.get(4), this),
                new DayHbox(forecast.get(6), this),
                new DayHbox(forecast.get(8), this),
                new DayHbox(forecast.get(10), this),
                new DayHbox(forecast.get(12), this));
            }
        }
        else {
            if (isDay()) {
                weatherForecastvb.getChildren().addAll(
                  new DayHbox(forecast.get(0), this),
                new DayHbox(forecast.get(2), this),
                new DayHbox(forecast.get(4), this),
                new DayHbox(forecast.get(6), this),
                new DayHbox(forecast.get(8), this),
                new DayHbox(forecast.get(10), this),
                new DayHbox(forecast.get(12), this));
            }
            else {
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

    public boolean isDay() {return isDay;}
}