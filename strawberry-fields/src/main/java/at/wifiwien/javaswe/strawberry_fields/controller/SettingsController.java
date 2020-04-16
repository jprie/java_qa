package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class SettingsController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField namePlayer1Label;

	@FXML
	private TextField namePlayer2Label;

	@FXML
	private TextField numColumnsTextField;

	@FXML
	private TextField numRowsTextField;

	@FXML
	private TextField numStrawberriesTextField;

	@FXML
	private Slider numStrawberriesSlider;

	@FXML
	private Button cancelButton;

	@FXML
	private Button saveButton;

	@FXML
	void handleCancelAction(ActionEvent event) {

		closeWindow(event);

	}

	private void closeWindow(ActionEvent event) {

		Stage currentStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		currentStage.close();

	}

	@FXML
	void handleSaveAction(ActionEvent event) {

		Game.getSettings().setNamePlayer1(namePlayer1Label.getText());
		Game.getSettings().setNamePlayer2(namePlayer2Label.getText());
		Game.getSettings().setNumColumns(Integer.parseInt(numColumnsTextField.getText()));
		Game.getSettings().setNumRows(Integer.parseInt(numRowsTextField.getText()));
		Game.getSettings().setNumStrawberries(Integer.parseInt(numStrawberriesTextField.getText()));
		closeWindow(event);

	}

	@FXML
	void initialize() {
		assert namePlayer1Label != null : "fx:id=\"namePlayer1Label\" was not injected: check your FXML file 'Settings.fxml'.";
		assert namePlayer2Label != null : "fx:id=\"namePlayer2Label\" was not injected: check your FXML file 'Settings.fxml'.";
		assert numColumnsTextField != null : "fx:id=\"numColumnsTextField\" was not injected: check your FXML file 'Settings.fxml'.";
		assert numRowsTextField != null : "fx:id=\"numRowsTextField\" was not injected: check your FXML file 'Settings.fxml'.";
		assert numStrawberriesTextField != null : "fx:id=\"numStrawberriesTextField\" was not injected: check your FXML file 'Settings.fxml'.";
		assert numStrawberriesSlider != null : "fx:id=\"numStrawberriesSlider\" was not injected: check your FXML file 'Settings.fxml'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'Settings.fxml'.";
		assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'Settings.fxml'.";

		// converter
		StringConverter<Number> converter = new NumberStringConverter();

		// bindbidirectional for slider and label, s.t. can be altered by text or slider
		numStrawberriesTextField.textProperty().bindBidirectional(numStrawberriesSlider.valueProperty(), converter);

		numStrawberriesSlider.valueProperty()
				.addListener((obs, oldval, newVal) -> numStrawberriesSlider.setValue(newVal.intValue()));

		initGUIElements();
	}

	/**
	 * Set text fields to existing settings in game class Don't use bindings here.
	 * thus values get updated only when save action received
	 */
	private void initGUIElements() {

		namePlayer1Label.setText(Game.getSettings().getNamePlayer1());
		namePlayer2Label.setText(Game.getSettings().getNamePlayer2());
		numColumnsTextField.setText(Integer.toString(Game.getSettings().getNumColumns()));
		numRowsTextField.setText(Integer.toString(Game.getSettings().getNumRows()));
		numStrawberriesTextField.setText(Integer.toString(Game.getSettings().getNumStrawberries()));

		numStrawberriesSlider.setMax(Game.getMaxNumStrawberries());
		numStrawberriesSlider.setMin(Game.getMinNumStrawberries());

	}
}
