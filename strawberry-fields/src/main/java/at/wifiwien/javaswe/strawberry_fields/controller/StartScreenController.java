package at.wifiwien.javaswe.strawberry_fields.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.application.Utils;
import at.wifiwien.javaswe.strawberry_fields.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
	private Button loadButton;

	@FXML
	void initialize() {
		assert startNewGameButton != null : "fx:id=\"startNewGameButton\" was not injected: check your FXML file 'StartScreen.fxml'.";
		assert openSettingsButton != null : "fx:id=\"openSettingsButton\" was not injected: check your FXML file 'StartScreen.fxml'.";
		assert exitGameButton != null : "fx:id=\"exitGameButton\" was not injected: check your FXML file 'StartScreen.fxml'.";

	}

	@FXML
	void handleExitGameAction(ActionEvent event) {

		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleOpenSettingsAction(ActionEvent event) {

		// start game in new window
		try {
			Utils.loadFXMLInNewStage(Constants.PATH_TO_SETTINGS_FXML, null);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("FATAL ERROR: Could not load: " + Constants.PATH_TO_SETTINGS_FXML);
		}
	}

	@FXML
	void handleStartNewGameAction(ActionEvent event) {

		// start game in new window
		try {
			// override existing game instance

			Utils.loadFXMLInNewStage(Constants.PATH_TO_GAME_FXML, () -> model.newGame());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("FATAL ERROR: Could not load: " + Constants.PATH_TO_GAME_FXML);
		}
	}


	@FXML
	public void handleLoadGameAction(ActionEvent event) {

		try {
			Utils.loadFXMLInNewStage(Constants.PATH_TO_GAME_FXML, () -> model.loadGame());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
