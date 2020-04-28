package at.wifiwien.javaswe.strawberry_fields.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import at.wifiwien.javaswe.strawberry_fields.service.GameFileIOService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class GameModelLoadStoreTest {

	@Mock
	GameFileIOService fileService;
	
	GameModel model;
	
	@BeforeEach
	void initTest() {
		
		model = new GameModelImpl(fileService);
		model.newGame();
	}
	
	@Test
	void storeGame() {
		
		
		Mockito.lenient().when(fileService.save(model.getGame())).thenReturn(true);
		
		boolean ok = model.saveGame();
		
		assertEquals(true, ok);
		
	}
	
	@Test
	void loadGame() {
		
		Game game = new Game(new Settings("John", "Andrea", 8, 8, 12));
		
		Mockito.lenient().when(fileService.load()).thenReturn(Optional.of(game));
		
		model.loadGame();
		
		assertEquals(game, model.getGame());
		
	}
	
	@AfterEach
	void tearDown() {
		
		System.out.println("Test done");
	}
	
	@AfterAll
	static void tearDownAll() {
		
		System.out.println("Tests done!");
	}
	
	
}
