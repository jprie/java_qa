package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.model.Square;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class FieldController extends CommonPropertiesController {

	public static int WIDTH = 15;
	public static int HEIGHT = 10;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TilePane fieldView;
	
	private ObservableList<Square> squares;

	@FXML
    void initialize() {
        assert fieldView != null : "fx:id=\"fieldView\" was not injected: check your FXML file 'Field.fxml'.";
        fieldView.setPrefColumns(WIDTH);
        
        
        // add callback to squares
        squares = FXCollections.observableArrayList((square) -> new Observable[] { square.itemProperty() });
        
        // add listener for updates
        squares.addListener(new ListChangeListener<Square>() {

			@Override
			public void onChanged(Change<? extends Square> c) {
				while(c.next()) {
					if (c.wasUpdated()) {
						updateSquare(c.getFrom());
					}
				}
				
			}
		});
        
        // bind model data
        Bindings.bindContentBidirectional(squares, game.getField().getSquares());
        
        
        assert(squares.size() == game.getField().getHeight() * game.getField().getWidth());
        
        generateSquares();
    }

	/**
	 * Generate squares to fill the field with imageViews where an item is
	 * positioned
	 */
	private void generateSquares() {

		StackPane squareView;
		List<StackPane> squareViews = new ArrayList<>();

		int height = game.getField().getHeight();
		int width = game.getField().getWidth();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				ImageView itemView = itemViewForItem(squares.get(row * width + col));
				
				squareView = new StackPane(itemView);
				squareView.getStyleClass().add("square-view");

				squareViews.add(squareView);

			}
		}

		fieldView.getChildren().addAll(squareViews);

	}

	/**
	 * Given a square returns an imageView
	 * @param square
	 * @return
	 */
	private ImageView itemViewForItem(Square square) {
		String itemPath = imagePathForItem(square.getItem());
		Image itemImage = new Image(getClass().getResourceAsStream(itemPath));
		ImageView itemView = new ImageView(itemImage);
		itemView.setPreserveRatio(true);
		itemView.setFitHeight(60);
		return itemView;
	}
	
	/**
	 * Update the square at the given index
	 * @param index
	 */
	private void updateSquare(int index) {
		
		StackPane squareView = (StackPane)fieldView.getChildren().get(index);
		
		squareView.getChildren().remove(0);
		squareView.getChildren().add(itemViewForItem(squares.get(index)));
		
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

}
