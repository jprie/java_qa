package at.wifiwien.javaswe.strawberry_fields.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.model.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController extends CommonPropertiesController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button startNewGameButton;

	@FXML
	private Button openSettingsButton;

	@FXML
	private Button exitGameButton;

	@FXML
	void initialize() {
		assert startNewGameButton != null : "fx:id=\"startNewGameButton\" was not injected: check your FXML file 'StartScreen.fxml'.";
		assert openSettingsButton != null : "fx:id=\"openSettingsButton\" was not injected: check your FXML file 'StartScreen.fxml'.";
		assert exitGameButton != null : "fx:id=\"exitGameButton\" was not injected: check your FXML file 'StartScreen.fxml'.";

	}

	@FXML
	void handleExitGameAction(ActionEvent event) {

		Platform.exit();
	}

	@FXML
	void handleOpenSettingsAction(ActionEvent event) {

		// start game in new window
		try {
			loadFXMLInNewStage(Constants.PATH_TO_SETTINGS_FXML);
		} catch (IOException e) {

			System.err.println("FATAL ERROR: Could not load: " + Constants.PATH_TO_SETTINGS_FXML);
		}
	}

	@FXML
	void handleStartNewGameAction(ActionEvent event) {

		// start game in new window
		try {
			// override existing game instance

			game = new Game();
			game.init();

			loadFXMLInNewStage(Constants.PATH_TO_GAME_FXML);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("FATAL ERROR: Could not load: " + Constants.PATH_TO_GAME_FXML);
		}
	}

	private void loadFXMLInNewStage(String pathToGameFxml) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(pathToGameFxml));
		Parent gameView = loader.load();

		Scene newScene = new Scene(gameView);

		Stage newStage = new Stage();
		newStage.setScene(newScene);
		newStage.showAndWait();

	}

}
