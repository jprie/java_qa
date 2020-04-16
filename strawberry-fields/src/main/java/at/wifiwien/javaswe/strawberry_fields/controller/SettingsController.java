package at.wifiwien.javaswe.strawberry_fields.controller;

import java.net.URL;
import java.util.ResourceBundle;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.model.Game;
import at.wifiwien.javaswe.strawberry_fields.model.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class SettingsController extends CommonPropertiesController {

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

		String namePlayer1 = namePlayer1Label.getText();
		String namePlayer2 = namePlayer2Label.getText();
		
		int numColumns = Integer.parseInt(numColumnsTextField.getText());
		int numRows = Integer.parseInt(numRowsTextField.getText());
		int numStrawberries = Integer.parseInt(numStrawberriesTextField.getText());
		
		
		if (namePlayer1 != null && !namePlayer1.contentEquals("") &&
				namePlayer2 != null && !namePlayer2.contentEquals("") &&
				numColumns > 0 && numRows > 0 &&
				numStrawberries >= model.getSettings().getMinNumStrawberries() &&
				numStrawberries <= model.getSettings().getMaxNumStrawberries()) { 
		Settings settings = new Settings(namePlayer1, namePlayer2, numColumns, numRows, numStrawberries);
		model.updateSettings(settings);
		}
		
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

		
		namePlayer1Label.setText(model.getSettings().getNamePlayer1());
		namePlayer2Label.setText(model.getSettings().getNamePlayer2());
		numColumnsTextField.setText(Integer.toString(model.getSettings().getNumColumns()));
		numRowsTextField.setText(Integer.toString(model.getSettings().getNumRows()));
		numStrawberriesTextField.setText(Integer.toString(model.getSettings().getNumStrawberries()));

		numStrawberriesSlider.setMax(model.getSettings().getMaxNumStrawberries());
		numStrawberriesSlider.setMin(model.getSettings().getMinNumStrawberries());

	}
}
