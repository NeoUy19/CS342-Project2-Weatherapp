import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import weather.Period;
import weather.WeatherAPI;
import javafx.scene.control.Button;
public class DayHbox extends HBox{
    private MainController controller;
    public DayHbox(Period time,MainController controller){
        this.controller=controller;
        Label label = new Label (
                time.name + " " + time.temperature + "° \n" + time.shortForecast + "\n" + "Wind Speed: " + time.windSpeed + " " + time.windDirection
        );
        label.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
        label.setWrapText(true);

        ImageView icon = getWeatherIcon(time.shortForecast);
        getChildren().addAll(label, icon);
        setPrefSize(75,75);
        setStyle("-fx-background-color: lightblue;" +
                " -fx-border-color: white;" +
                " -fx-border-width: 1;" +
                "-fx-border-radius: 15 15 15 15;" +
                " -fx-background-radius: 15 15 15 15;");
    }

    public DayHbox(Button dayBtn, Label label, Button nightBtn){
        getChildren().addAll(dayBtn, label, nightBtn);
        setPrefSize(75,75);
        setStyle("-fx-background-color: lightblue;" +
                " -fx-border-color: white;" +
                " -fx-border-width: 1;" +
                "-fx-border-radius: 15 15 15 15;" +
                " -fx-background-radius: 15 15 15 15;");
    }
    private ImageView getWeatherIcon(String shortForecast){
        String forecast = shortForecast.toLowerCase();
        String imagePath;
        boolean isDay = controller.isDay();

        if (forecast.contains("thunder")) {
            imagePath = "/images/thunder.png";
        } else if (forecast.contains("storm")) {
            imagePath = "/images/storm.png";
        } else if (forecast.contains("heavy rain") || forecast.contains("heavy shower")) {
            if (isDay) {
                imagePath = "/images/heavy-rain.png";
            } else {
                imagePath = "/images/nightHeavyRain.png";
            }
        } else if (forecast.contains("light rain") || forecast.contains("drizzle") || forecast.contains("light shower")) {
            imagePath = "/images/light-rain.png";
        } else if (forecast.contains("rain") || forecast.contains("shower")) {
            imagePath = "/images/rain.png";
        } else if (forecast.contains("snow") || forecast.contains("flurries")) {
            if (isDay) {
                imagePath = "/images/snowDay.png";
            } else {
                imagePath = "/images/snowNight.png";
            }
        } else if (forecast.contains("partly cloudy") || forecast.contains("partly sunny")) {
            if (isDay) {
                imagePath = "/images/partly-cloudDay.png";
            } else {
                imagePath = "/images/cloudyNight.png";
            }
        } else if (forecast.contains("cloudy")) {
            if (isDay) {
                imagePath = "/images/cloudyMain.png";
            } else {
                imagePath = "/images/cloudyNight.png";
            }
        } else if (forecast.contains("fog") || forecast.contains("haze")) {
            imagePath = "/images/hazefog.png";
        } else if (forecast.contains("wind") || forecast.contains("breezy")) {
            imagePath = "/images/windy.png";
        } else if (forecast.contains("sunny") || forecast.contains("clear")) {
            if (isDay) {
                imagePath = "/images/sunny.png";
            } else {
                imagePath = "/images/clearNight.png";
            }
        } else {
            imagePath = "/images/sunny.png"; // fallback
        }

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}