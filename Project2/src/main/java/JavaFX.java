import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import weather.Period;
import weather.WeatherAPI;

import java.util.ArrayList;

public class JavaFX extends Application {
	TextField temperature,weather;

	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("A&N Weather");

		MainUI home = new MainUI();
		Scene scene = home.buildHome();

		SettingsUI settings = new SettingsUI();
		Scene settingsPage = settings.buildSettings();

		home.getSettings().setOnAction(e->{
			primaryStage.setScene(settingsPage);
		});
		settings.getReturnButt().setOnAction(e->{
			primaryStage.setScene(scene);
		});
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
