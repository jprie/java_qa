package at.wifiwien.javaswe.strawberry_fields.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import at.wifiwien.javaswe.strawberry_fields.exception.MoveException;
import at.wifiwien.javaswe.strawberry_fields.model.item.Piece;
import at.wifiwien.javaswe.strawberry_fields.service.GameFileIOService;

@RunWith(JUnitPlatform.class)
class GameModelTest {

	private GameModel model;
	
	@BeforeEach
	void initGameModel() {
		System.out.println("Initialized test environment");
		model = new GameModelImpl(new GameFileIOService());
		model.newGame();

	}
	

	@Test
	void testFieldInitialization_Player1StartsAtDefinedPosition() {

		
		//System.out.println(model.getGame().getField());

		assertEquals(model.getGame().getField().getItemAtPosition(new Position(0, 0)), model.getGame().getPlayers().get(Game.INDEX_PLAYER1).getPiece());
		
	}

	@Test
	void testMovingPiece_moveFromInitPositionOneStepRight() {

		// Input
		Move move = new Move(1, Move.Direction.RIGHT);
		
		// Expected output
		Piece expOutput = model.getGame().getPlayers().get(Game.INDEX_PLAYER1).getPiece();
		
		try {
			model.getGame().move(move);
		} catch (MoveException e) {
			System.out.println("Move could not be done: " + e.getMessage());
		}
		
		// Actual Output
		Piece actualOutput = (Piece)model.getGame().getField().getItemAtPosition(new Position(1, 0));
		
		// Verify result
		assertEquals(expOutput, actualOutput);
	}
	
	@Test
	void testTogglePlayersTurn_Player1Moved_NowPlayerTwosTurn() {
		
		assertEquals(model.getGame().getPlayersTurn(), Game.INDEX_PLAYER1);
		
		try {
			model.getGame().move(new Move(1, Move.Direction.RIGHT));
		} catch (MoveException e) {
			System.out.println("Move could not be done: " + e.getMessage());
		}
		
		assertEquals(model.getGame().getPlayersTurn(), Game.INDEX_PLAYER2);
	}
	
	@AfterAll
	static void clearAll() {
		
		System.out.println("Called after all");
		
	}

}
