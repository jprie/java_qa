package at.wifiwien.javaswe.strawberry_fields.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.application.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController extends CommonPropertiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane view;

    @FXML
    private Menu gameMenu;

    @FXML
    private MenuItem newGameMenuItem;

    @FXML
    private MenuItem loadGameMenuItem;

    @FXML
    private MenuItem saveGameMenuItem;

    @FXML
    private MenuItem quitGameMenuItem;

    @FXML
    private Menu editMenu;

    @FXML
    private MenuItem configureMenuItem;

    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem aboutMenuItem;

	@FXML
	private MenuItem settingsMenuItem;


    @FXML
    void initialize() {
        assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'Menu.fxml'.";
        assert gameMenu != null : "fx:id=\"gameMenu\" was not injected: check your FXML file 'Menu.fxml'.";
        assert newGameMenuItem != null : "fx:id=\"newGameMenuItem\" was not injected: check your FXML file 'Menu.fxml'.";
        assert loadGameMenuItem != null : "fx:id=\"loadGameMenuItem\" was not injected: check your FXML file 'Menu.fxml'.";
        assert saveGameMenuItem != null : "fx:id=\"saveGameMenuItem\" was not injected: check your FXML file 'Menu.fxml'.";
        assert quitGameMenuItem != null : "fx:id=\"quitGameMenuItem\" was not injected: check your FXML file 'Menu.fxml'.";
        assert editMenu != null : "fx:id=\"editMenu\" was not injected: check your FXML file 'Menu.fxml'.";
        assert settingsMenuItem != null : "fx:id=\"configureMenuItem\" was not injected: check your FXML file 'Menu.fxml'.";
        assert helpMenu != null : "fx:id=\"helpMenu\" was not injected: check your FXML file 'Menu.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'Menu.fxml'.";

    }
    
    @FXML
    void handleAbout(ActionEvent event) {

    }

    @FXML
    void handleExit(ActionEvent event) {

    	Stage stage = (Stage)view.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void handleNewGame(ActionEvent event) {

    	fieldLayoutDone.set(false);
    	
    	model.newGame();
    }

    @FXML
    void loadGame(ActionEvent event) {

    	fieldLayoutDone.set(false);
    	model.loadGame();
    }

    @FXML
    void saveGame(ActionEvent event) {

    	model.saveGame();
    }


	@FXML
	public void handleSettings(ActionEvent event) {
		
		try {
			Utils.loadFXMLInNewStage(Constants.PATH_TO_SETTINGS_FXML);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
