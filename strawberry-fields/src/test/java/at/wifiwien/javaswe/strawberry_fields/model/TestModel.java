package at.wifiwien.javaswe.strawberry_fields.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import at.wifiwien.javaswe.strawberry_fields.exception.MoveException;

class TestModel {

	@Test
	void testFieldInitialization() {

		GameModel model = new GameModelImpl();
		model.newGame();

		//System.out.println(model.getGame().getField());

		assertEquals(model.getGame().getField().getItemAtPosition(new Position(0, 0)), model.getGame().getPlayers().get(0).getItem());
	}

	@Test
	void testMoving() {

		GameModel model = new GameModelImpl();
		model.newGame();
		
		try {
			model.getGame().move(new Move(1, Move.Direction.RIGHT));
		} catch (MoveException e) {
			System.out.println("Move could not be done: " + e.getMessage());
		}
		
		assertEquals(model.getGame().getField().getItemAtPosition(new Position(1, 0)), model.getGame().getPlayers().get(0).getItem());
	}
	
	@Test
	void testTogglePlayersTurn() {

		GameModel model = new GameModelImpl();
		model.newGame();
		
		assertEquals(model.getGame().getPlayersTurn(), Game.INDEX_PLAYER1);
		
		try {
			model.getGame().move(new Move(1, Move.Direction.RIGHT));
		} catch (MoveException e) {
			System.out.println("Move could not be done: " + e.getMessage());
		}
		
		assertEquals(model.getGame().getPlayersTurn(), Game.INDEX_PLAYER2);

		
	}

}
