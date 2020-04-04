package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GameController extends CommonPropertiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane view;

    @FXML
    void initialize() {
        assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'Game.fxml'.";

    }
}
