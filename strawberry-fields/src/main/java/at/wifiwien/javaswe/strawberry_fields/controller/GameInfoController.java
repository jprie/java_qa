package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameInfoController {

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
		namePlayer1Label.setText("Jan" + ":");
		namePlayer2Label.setText("Birgit" + ":");
		
		// set game scores
		scorePlayer1Label.setText("0");
		scorePlayer2Label.setText("0");
		numberStrawberriesLeftLabel.setText("20");
		
	}
}
