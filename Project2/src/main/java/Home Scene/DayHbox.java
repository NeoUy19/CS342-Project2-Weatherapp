import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import weather.Period;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import weather.WeatherAPI;
import javafx.scene.control.Button;

/*Custom HBox class because HBoxes were getting repetive*/
public class DayHbox extends HBox{
    private MainController controller;
    /*Creates a row in the 7 day forecast scroll panel*/
    public DayHbox(Period time,MainController controller){
        this.controller=controller;
        Label label = new Label ( //This label shows all the required info for Minimum score
                time.name + " " + time.temperature + "° \n" + time.shortForecast + "\n" + "Wind Speed: " + time.windSpeed + " " + time.windDirection
        );
        label.setStyle("-fx-font-size: 12px; -fx-font-family: 'Georgia'; -fx-text-fill: white;");
        label.setWrapText(true);

        /*Create the weather Icon for the box by calliing getWeatherIcon*/
        ImageView icon = getWeatherIcon(time.shortForecast);
        AnchorPane iconPane = new AnchorPane(icon);
        iconPane.setPrefWidth(50); /*Set sizing and dont allow it to move with AnchorPane*/
        iconPane.setMinWidth(50);
        iconPane.setMaxWidth(50);
        AnchorPane.setRightAnchor(icon, 5.0);
        AnchorPane.setTopAnchor(icon, 15.0);

        label.setMaxWidth(225); //Set label size this was a pain in the ass because the shortforecast would be too long (also made the font smaller so everything can fit
        label.setMinWidth(225);
        setMaxHeight(Double.MAX_VALUE); // allow HBox to grow if everything cant fit
        getChildren().addAll(label, iconPane);
        setPrefSize(360,75);
        setPadding(new Insets (5,5,5,5));
        setStyle("-fx-background-color: rgba(255,255,255,0.15);" + //White background with 15% opacity
                " -fx-border-color: rgba(255,255,255,0.4);" + // parameters are red,green,blue, and opacity
                " -fx-border-width: 1;" +
                "-fx-border-radius: 15 15 15 15;" + //round off borders
                " -fx-background-radius: 15 15 15 15;");
    }

    /* This is the HBox above the scroll panel with the 7day forecast and the flipping day and night*/
    public DayHbox(Button dayBtn, Label label, Button nightBtn){
        getChildren().addAll(dayBtn, label, nightBtn);
        setPrefSize(360, 50);
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setStyle("-fx-background-color: rgba(255,255,255,0.15);" +
                " -fx-border-color: rgba(255,255,255,0.4);" +
                " -fx-border-width: 1px;" +
                " -fx-border-radius: 15 15 15 15;" +
                " -fx-background-radius: 15 15 15 15;");
    }

    /*Set the icon for each HBox in the 7 day forecast ICONS FROM Freepik ON FLATICON S/O THEM*/
    private ImageView getWeatherIcon(String shortForecast){
        String forecast = shortForecast.toLowerCase();
        String imagePath;
        boolean isDay = controller.isDay(); //image changes based on weather or not the user clicked on the day or night button

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
                imagePath = "/images/cloudy.png";
            } else {
                imagePath = "/images/cloudy.png";
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
            imagePath = "/images/sunny.png";
        }

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}