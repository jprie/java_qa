package at.wifiwien.javaswe.strawberry_fields.model;


import at.wifiwien.javaswe.strawberry_fields.service.GameFileIOService;

/**
 * GameModel implememnts the interface the controllers are talking to
 * @author jprie
 *
 */
public class GameModelImpl implements GameModel {

	private Game game;
	private Settings settings = new Settings("Peter", "Birgit", 15, 9, 20);
	
	
	private GameFileIOService fileService = new GameFileIOService();
	

	@Override
	public void newGame() {
		
		game = new Game(settings);
		game.init();
		
	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
		game = fileService.load().orElseThrow();
	}

	@Override
	public void saveGame() {
		
		fileService.save(game);
		
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
