package at.wifiwien.javaswe.strawberry_fields.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import at.wifiwien.javaswe.strawberry_fields.exception.MoveException;

class TestModel {

	@Test
	void testFieldInitialization() {

		Game game = new Game();
		game.init();

		System.out.println(game.getField());

		assertEquals(game.getField().getItemAtPosition(new Position(0, 0)), game.getPlayers().get(0).getItem());
	}

	@Test
	void testMoving() {

		Game game = new Game();

		game.init();

		try {
			game.move(new Move(1, Move.Direction.RIGHT));
		} catch (MoveException e) {
			System.out.println("Move could not be done: " + e.getMessage());
		}
		
		assertEquals(game.getField().getItemAtPosition(new Position(1, 0)), game.getPlayers().get(0).getItem());
	}
	
	@Test
	void testTogglePlayersTurn() {

		Game game = new Game();
		game.init();
		
		assertEquals(game.getPlayersTurn(), Game.INDEX_PLAYER1);
		
		try {
			game.move(new Move(1, Move.Direction.RIGHT));
		} catch (MoveException e) {
			System.out.println("Move could not be done: " + e.getMessage());
		}
		
		assertEquals(game.getPlayersTurn(), Game.INDEX_PLAYER2);

		
	}

}
