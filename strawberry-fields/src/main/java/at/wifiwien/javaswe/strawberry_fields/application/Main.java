package at.wifiwien.javaswe.strawberry_fields.application;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Constants.PATH_TO_START_SCREEN_FXML));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Constants.PATH_TO_APPLICATION_CSS).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws Exception {
	
		super.init();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
