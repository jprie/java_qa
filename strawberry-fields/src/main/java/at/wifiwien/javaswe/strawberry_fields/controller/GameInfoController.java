package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameInfoController extends CommonPropertiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label namePlayer1Label;

    @FXML
    private Label scorePlayer1Label;

    @FXML
    private Label namePlayer2Label;

    @FXML
    private Label scorePlayer2Label;

    @FXML
    private Label strawberriesLeftLabel;

    @FXML
    private Label numberStrawberriesLeftLabel;

    @FXML
    void initialize() {
        assert namePlayer1Label != null : "fx:id=\"player1Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert scorePlayer1Label != null : "fx:id=\"strawberriesPlayer1Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert namePlayer2Label != null : "fx:id=\"player2Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert scorePlayer2Label != null : "fx:id=\"strawberriesPlayer2Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert strawberriesLeftLabel != null : "fx:id=\"strawberriesLeftLabel\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert numberStrawberriesLeftLabel != null : "fx:id=\"numberStrawberriesLeftLabel\" was not injected: check your FXML file 'GameInfo.fxml'.";

        initUIElements();
    }

	private void initUIElements() {
	
		// set player names
		namePlayer1Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER1).nameProperty().concat(":"));
		namePlayer2Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER2).nameProperty().concat(":"));
		
		// set game scores
		scorePlayer1Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER1).scoreProperty().asString());
		scorePlayer2Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER2).scoreProperty().asString());
		numberStrawberriesLeftLabel.textProperty().bind(game.strawberriesLeftProperty().asString());
		
	}
}
