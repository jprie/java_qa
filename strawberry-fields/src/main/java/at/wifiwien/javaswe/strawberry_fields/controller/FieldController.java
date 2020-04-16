package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.model.Fence;
import at.wifiwien.javaswe.strawberry_fields.model.Position;
import at.wifiwien.javaswe.strawberry_fields.model.Square;
import at.wifiwien.javaswe.strawberry_fields.model.item.Item;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
				while (c.next()) {
					if (c.wasUpdated()) {
						updateSquare(c.getFrom());
					}
				}

			}
		});

		// bind model data
		Bindings.bindContentBidirectional(squares, game.getField().getSquares());

		assert (squares.size() == game.getField().getHeight() * game.getField().getWidth());

		generateSquares();
	}

	/**
	 * Generate squares to fill the field with imageViews where an item is
	 * positioned
	 */
	private void generateSquares() {

		Square square;
		StackPane squareView;
		List<StackPane> squareViews = new ArrayList<>();

		int height = game.getField().getHeight();
		int width = game.getField().getWidth();

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				int index = row * width + col;
				square = squares.get(index);
				squareView = new StackPane();

				if (square.getItem() != null) {
					ImageView itemView = itemViewForItem(square);
					squareView.getChildren().add(itemView);
				}

				squareView.getStyleClass().add("square-view");
//				if (squares.get(row * width + col).getItem() == Item.STRAWBERRY) System.out.println(i++);
				squareViews.add(squareView);

			}
		}

		fieldView.getChildren().addAll(squareViews);

	}

	/**
	 * Given a square returns an imageView
	 * 
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
	 * 
	 * @param index
	 */
	private void updateSquare(int index) {

		StackPane squareView = (StackPane) fieldView.getChildren().get(index);

		if (squareView.getChildren().size() > 0) {
			squareView.getChildren().remove(0);
		}

		if (squares.get(index).getItem() != null) {
			squareView.getChildren().add(itemViewForItem(squares.get(index)));
		}

	}

	/**
	 * Get path for image corresponding to item
	 * 
	 * @param item
	 * @return
	 */
	private String imagePathForItem(Item item) {

		switch (item.getType()) {

		case PIECE:
			return game.getPlayers().get(0).getItem() == item ? Constants.PATH_TO_IMAGE_PLAYER1
					: Constants.PATH_TO_IMAGE_PLAYER2;
		case STRAWBERRY:
			return Constants.PATH_TO_IMAGE_STRAWBERRY;
		case FENCE:
			return Constants.PATH_TO_IMAGE_FENCE;
		}

		return null;
	}

	public void animateRedBorder() {

		fieldView.setManaged(false);

		int[] t = { -10 };

		KeyFrame[] keyFrames = Stream.iterate(0, (i) -> i + 1)
				.limit(10)
				.map(i -> new Border(new BorderStroke(Color.ORANGERED, BorderStrokeStyle.SOLID, null, new BorderWidths(i))))
				.map(b -> new KeyFrame(Duration.millis(t[0] += 10), new KeyValue(fieldView.borderProperty(), b)))
				.collect(Collectors.toList()).toArray(new KeyFrame[5]);

		// reset counter

		Timeline timeline = new Timeline(keyFrames);

		timeline.setAutoReverse(true);
		timeline.setCycleCount(2);
		timeline.play();

		fieldView.setManaged(true);

	}

	/**
	 * Animate the item the piece tried to move over
	 * @param fence
	 */
	public void animateTouchedFence(Position pos) {
		
		StackPane squareView = (StackPane)fieldView.getChildren().get(pos.y*game.getField().getWidth()+pos.x);
		
		int[] t = { -10 };

		KeyFrame[] keyFrames = Stream.iterate(0, (i) -> i + 1)
				.limit(10)
				.map(i -> new Border(new BorderStroke(Color.ORANGERED, BorderStrokeStyle.SOLID, null, new BorderWidths(i))))
				.map(b -> new KeyFrame(Duration.millis(t[0] += 10), new KeyValue(squareView.borderProperty(), b)))
				.collect(Collectors.toList()).toArray(new KeyFrame[5]);

		// reset counter

		Timeline timeline = new Timeline(keyFrames);

		timeline.setAutoReverse(true);
		timeline.setCycleCount(2);
		timeline.play();
		
		
	}

}
