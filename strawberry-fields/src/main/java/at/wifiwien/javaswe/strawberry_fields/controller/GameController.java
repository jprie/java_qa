package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.exception.MoveException;
import at.wifiwien.javaswe.strawberry_fields.model.Move;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class GameController extends CommonPropertiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane view;

    @FXML
    private FieldController fieldController;
    
    @FXML
    void initialize() {
        assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'Game.fxml'.";
        assert fieldController != null : "fx:id=\"field\" was not injected: check your FXML file 'Game.fxml'.";

        view.setOnKeyPressed(this::handleKeyPressedOnField);
        
        
        view.sceneProperty().addListener(this::handleSceneInvalidated);
    }
    
	/**
	 * Handles key events and forwards moves to the model
	 * 
	 * @param event
	 */
	@FXML
	private void handleKeyPressedOnField(KeyEvent event) {

		Optional<Move> move = moveFromEventCode(event.getCode());

		if (move.isPresent()) {
			try {

				model.getGame().move(move.get());

			} catch (MoveAtFenceException e) {
				
				System.out.println("MoveAtFenceException: " + e.getMessage());
				
				fieldController.animateTouchedFence(e.getPosition());
				
			} catch (MoveException e) {
				System.out.println("MoveException: " + e.getMessage());
				
				fieldController.animateRedBorder();

			} 

		}
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	private Optional<Move> moveFromEventCode(KeyCode keyCode) {

		Move move = null;
		switch (keyCode) {
		case UP:
			move = new Move(1, Move.Direction.UP);
			break;
		case DOWN:
			move = new Move(1, Move.Direction.DOWN);
			break;
		case LEFT:
			move = new Move(1, Move.Direction.LEFT);
			break;
		case RIGHT:
			move = new Move(1, Move.Direction.RIGHT);
			break;
		default:
			System.out.println("Unsupported key for movement: " + keyCode);

		}
		return Optional.ofNullable(move);
	}

	public void handleSceneInvalidated(Observable observable) {
		
		view.requestFocus();
	}
}
