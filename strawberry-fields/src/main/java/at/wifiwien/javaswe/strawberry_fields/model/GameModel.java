package at.wifiwien.javaswe.strawberry_fields.model;

import javafx.beans.property.ObjectProperty;

/**
 * Defines the interface to the model
 * @author jprie
 *
 */
public interface GameModel {
	
	void newGame();
	
	void loadGame();
	
	boolean saveGame();
	
	void updateSettings(Settings settings);
	
	Settings getSettings();

	ObjectProperty<Game> gameProperty();
	
	Game getGame();
}
