package at.wifiwien.javaswe.strawberry_fields.model;

/**
 * Defines the interface to the model
 * @author jprie
 *
 */
public interface GameModel {
	
	void newGame();
	
	Game loadGame();
	
	void saveGame(Game game);
	
	void updateSettings(Settings settings);
	
	Settings getSettings();
	
	Game getGame();
}
