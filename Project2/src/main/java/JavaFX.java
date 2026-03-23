import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import weather.Period;
import weather.WeatherAPI;

import java.util.ArrayList;

public class JavaFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("A&N Weather");

		/*Create each cities MainUI so they don't share controllers, each can access different things*/
		MainUI Chi = new MainUI();
		MainUI SanF = new MainUI();
		MainUI NewY = new MainUI();
		MainUI Aus = new MainUI();
		/*Each city gets their own Scene from their own UI for instant switches*/
		Scene Chicago = Chi.buildHome();
		Scene SF = SanF.buildSF();
		Scene NY = NewY.buildNYC();
		Scene Austin = Aus.buildAustin();
		/*Each cities MainUI passes each Scene and MainUI so the dropdown on each city can go to any other city*/
		Chi.searchCity(primaryStage, Chicago, Chi.getCityDropdown(), Chicago, SF, NY, Austin, Chi, SanF, NewY, Aus);
		SanF.searchCity(primaryStage, SF, SanF.getCityDropdown(), Chicago, SF, NY, Austin, Chi, SanF, NewY, Aus);
		NewY.searchCity(primaryStage, NY, NewY.getCityDropdown(), Chicago, SF, NY, Austin, Chi, SanF, NewY, Aus);
		Aus.searchCity(primaryStage, Austin, Aus.getCityDropdown(), Chicago, SF, NY, Austin, Chi, SanF, NewY, Aus);


		primaryStage.setScene(Chicago);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
