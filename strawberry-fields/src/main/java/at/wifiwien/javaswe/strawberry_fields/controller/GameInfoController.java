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
    private Label player1Label;

    @FXML
    private Label strawberriesPlayer1Label;

    @FXML
    private Label player2Label;

    @FXML
    private Label strawberriesPlayer2Label;

    @FXML
    private Label strawberriesLeftLabel;

    @FXML
    private Label numberStrawberriesLeftLabel;

    @FXML
    void initialize() {
        assert player1Label != null : "fx:id=\"player1Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert strawberriesPlayer1Label != null : "fx:id=\"strawberriesPlayer1Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert player2Label != null : "fx:id=\"player2Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert strawberriesPlayer2Label != null : "fx:id=\"strawberriesPlayer2Label\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert strawberriesLeftLabel != null : "fx:id=\"strawberriesLeftLabel\" was not injected: check your FXML file 'GameInfo.fxml'.";
        assert numberStrawberriesLeftLabel != null : "fx:id=\"numberStrawberriesLeftLabel\" was not injected: check your FXML file 'GameInfo.fxml'.";

    }
}
