package at.wifiwien.javaswe.strawberry_fields.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utils {


	/**
	 * Load the given fxml path in a new stage and run the given action after
	 * Controllers were initialized This helps with registering a listener for the
	 * game property in the initializer actually being ready whem the game changes
	 * for the first time
	 * 
	 * @param pathToFxml
	 * @param action
	 * @throws IOException
	 */
	public static void loadFXMLInNewStage(String pathToFxml, Runnable action) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utils.class.getResource(pathToFxml));
		Parent root = loader.load();

		Scene newScene = new Scene(root);

		Stage newStage = new Stage();
		newStage.setScene(newScene);

		if (action != null) {
			action.run();
		}

		newStage.showAndWait();

	}

	public static void loadFXMLInNewStage(String pathToFxml) throws IOException {
		
		loadFXMLInNewStage(pathToFxml, null);
		
	}
}
