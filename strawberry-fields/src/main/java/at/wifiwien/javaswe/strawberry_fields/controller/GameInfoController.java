package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.model.Game;
import at.wifiwien.javaswe.strawberry_fields.model.Player;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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

        // listener for game changes
        model.gameProperty().addListener((obs, og, ng) -> {
        	
        	model.getGame().winnerProperty().addListener(this::handleWinnerChanged);
        	initUIElements();
        });
        
        

    }
    
    void handleWinnerChanged(ObservableValue<? extends Optional<Player>> winner, Optional<Player> oldPlayer, Optional<Player> newPlayer) {
    	
    	String contentText;
    	
    	if (newPlayer.isPresent()) {
    		contentText = newPlayer.get().getName() + " wins!";	
    	} else {
    		contentText = "Draw";
    	}
    	
    	ButtonType ok = new ButtonType("Ok", ButtonData.OK_DONE);
    	
    	Dialog<Integer> dialog = new Dialog<Integer>();
    	dialog.setHeaderText("Game ended");
    	dialog.setContentText(contentText);
    	dialog.getDialogPane().getButtonTypes().add(ok);
    	dialog.showAndWait();
    	
    }

	private void initUIElements() {
	
		Game game = model.getGame();
		
		// set player names
		namePlayer1Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER1).nameProperty().concat(":"));
		namePlayer2Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER2).nameProperty().concat(":"));
		
		// set game scores
		scorePlayer1Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER1).scoreProperty().asString());
		scorePlayer2Label.textProperty().bind(game.getPlayers().get(Game.INDEX_PLAYER2).scoreProperty().asString());
		numberStrawberriesLeftLabel.textProperty().bind(game.strawberriesLeftProperty().asString());
		
		// set players turn
		namePlayer1Label.underlineProperty().bind(game.playersTurnProperty().isEqualTo(0));
		namePlayer2Label.underlineProperty().bind(game.playersTurnProperty().greaterThan(0));
		
	}
}
