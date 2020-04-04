package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class FieldController {

	public static int WIDTH = 8;
	public static int HEIGHT = 5;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TilePane fieldView;

    @FXML
    void initialize() {
        assert fieldView != null : "fx:id=\"fieldView\" was not injected: check your FXML file 'Field.fxml'.";

        generateSquares();
    }

	private void generateSquares() {
		
		StackPane squareView;
		List<StackPane> squares = new ArrayList<>();
		
		for (int i=0; i<HEIGHT; i++) {
			for (int j=0; j<WIDTH; j++) {
				
				ImageView itemView = new ImageView(new Image(getClass().getResourceAsStream(Constants.PATH_TO_STRAWBERRY_IMAGE)));
				itemView.setPreserveRatio(true);
				itemView.setFitHeight(60);
				squareView = new StackPane(itemView);
				squareView.getStyleClass().add("square-view");
				
				squares.add(squareView);
				
			}
		}
		
		fieldView.getChildren().addAll(squares);
		
	}
}
