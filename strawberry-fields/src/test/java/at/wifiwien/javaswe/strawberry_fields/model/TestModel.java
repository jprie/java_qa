package at.wifiwien.javaswe.strawberry_fields.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import at.wifiwien.javaswe.strawberry_fields.model.Square.Item;

class TestModel {

	@Test
	void test() {
		
		Game game = new Game();
		game.init();
		
		System.out.println(game.getField());
		
		
		assertEquals(game.getField().getItemAtPosition(new Position(0, 0)), Item.PIECE_PLAYER1);
	}

}
