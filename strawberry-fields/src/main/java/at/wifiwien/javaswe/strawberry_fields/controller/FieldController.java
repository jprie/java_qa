package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.model.Square;
import at.wifiwien.javaswe.strawberry_fields.model.Square.Item;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class FieldController {

	public static int WIDTH = 15;
	public static int HEIGHT = 10;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TilePane fieldView;

	private List<Square> squares;

	@FXML
    void initialize() {
        assert fieldView != null : "fx:id=\"fieldView\" was not injected: check your FXML file 'Field.fxml'.";
        fieldView.setPrefColumns(WIDTH);
        
        
        initModel();
        
        assert(squares.size() == WIDTH*HEIGHT);
        
        generateSquares();
        
        fieldView.setOnKeyPressed(this::handleKeyPressedOnField);
        
        // This does not work, since stage and scene have not been created yet
        // fieldView.requestFocus();
        
        fieldView.sceneProperty().addListener(this::handleSceneInvalidated);
    }

	/**
	 * Initialize the smallest possible model with squares
	 */
	private void initModel() {
		squares = Stream.generate(() -> new Square(Square.Item.EMPTY)).limit(WIDTH * HEIGHT)
				.collect(Collectors.toList());

		squares.set(0, new Square(Item.PIECE_PLAYER1));
		squares.set(WIDTH * HEIGHT - 1, new Square(Item.PIECE_PLAYER2));

		squares.set(17, new Square(Item.STRAWBERRY));
		squares.set(37, new Square(Item.STRAWBERRY));
	}

	/**
	 * Generate squares to fill the field with imageViews where an item is
	 * positioned
	 */
	private void generateSquares() {

		StackPane squareView;
		List<StackPane> squareViews = new ArrayList<>();

		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {

				String itemPath = imagePathForItem(squares.get(row * WIDTH + col).getItem());
				Image itemImage = new Image(getClass().getResourceAsStream(itemPath));
				ImageView itemView = new ImageView(itemImage);
				itemView.setPreserveRatio(true);
				itemView.setFitHeight(60);
				squareView = new StackPane(itemView);
				squareView.getStyleClass().add("square-view");

				squareViews.add(squareView);

			}
		}

		fieldView.getChildren().addAll(squareViews);

	}

	/**
	 * Get path for image corresponding to item
	 * @param item
	 * @return
	 */
	private String imagePathForItem(Square.Item item) {

		switch (item) {

		case PIECE_PLAYER1:
			return Constants.PATH_TO_IMAGE_PLAYER1;
		case PIECE_PLAYER2:
			return Constants.PATH_TO_IMAGE_PLAYER2;
		case STRAWBERRY:
			return Constants.PATH_TO_IMAGE_STRAWBERRY;
		case EMPTY:
			return "";
		}

		return null;
	}

	public void handleKeyPressedOnField(KeyEvent event) {

		switch (event.getCode()) {
		case UP:
			System.out.println(event.getCode()); break;
		case DOWN:
			System.out.println(event.getCode()); break;
		case LEFT:
			System.out.println(event.getCode()); break;
		case RIGHT:
			System.out.println(event.getCode()); break;
		default:
			System.out.println("Unsupported key pressed!");
		}

	}
	
	private void handleSceneInvalidated(Observable obs) {
	
		fieldView.requestFocus();
	}
}
