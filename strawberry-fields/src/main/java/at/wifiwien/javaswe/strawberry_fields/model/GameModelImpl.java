package at.wifiwien.javaswe.strawberry_fields.model;


import at.wifiwien.javaswe.strawberry_fields.service.GameFileIOService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * GameModel implememnts the interface the controllers are talking to
 * @author jprie
 *
 */
public class GameModelImpl implements GameModel {

	private ObjectProperty<Game> game = new SimpleObjectProperty<Game>();
	private Settings settings = new Settings("Peter", "Birgit", 15, 9, 20);
	
	
	private GameFileIOService fileService = new GameFileIOService();
	

	@Override
	public void newGame() {
		
		Game game = new Game(settings);
		game.init();
		
		this.game.set(game);
		
	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
		game.set(fileService.load().orElseThrow());
	}

	@Override
	public void saveGame() {
		
		fileService.save(game.get());
		
	}

	@Override
	public void updateSettings(Settings settings) {
		
		this.settings = settings;
		
	}

	@Override
	public Settings getSettings() {

		return settings;
	}

	@Override
	public final ObjectProperty<Game> gameProperty() {
		return this.game;
	}
	
	@Override
	public final Game getGame() {
		return this.gameProperty().get();
	}
	

	public final void setGame(final Game game) {
		this.gameProperty().set(game);
	}
	
	
	
	
	
}
