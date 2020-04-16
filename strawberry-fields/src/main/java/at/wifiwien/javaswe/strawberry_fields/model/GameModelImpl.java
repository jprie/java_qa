package at.wifiwien.javaswe.strawberry_fields.model;

/**
 * GameModel represents the model holding all the parts together
 * @author jprie
 *
 */
public class GameModelImpl implements GameModel {

	private Game game;
	private Settings settings = new Settings("Peter", "Birgit", 15, 9, 20);
	
	

	@Override
	public void newGame() {
		
		game = new Game(settings);
		game.init();
		
	}

	@Override
	public Game loadGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSettings(Settings settings) {
		
		this.settings = settings;
		
	}

	@Override
	public Game getGame() {
		
		return game;
	}

	@Override
	public Settings getSettings() {

		return settings;
	}
	
	
	
}
