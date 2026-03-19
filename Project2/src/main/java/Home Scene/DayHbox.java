import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import weather.Period;
import weather.WeatherAPI;
import javafx.scene.control.Button;
public class DayHbox extends HBox{

    public DayHbox(Period time){
        Label label = new Label (
                time.name + " " + time.temperature + "° \n" + time.shortForecast + "\n" + "Wind Speed: " + time.windSpeed + " " + time.windDirection
        );
        label.setStyle("-fx-font-size: 15px; -fx-font-family: 'Georgia';");
        label.setWrapText(true);

        getChildren().add(label);
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
}