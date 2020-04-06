package at.wifiwien.javaswe.strawberry_fields.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.wifiwien.javaswe.strawberry_fields.exception.MoveException;
import at.wifiwien.javaswe.strawberry_fields.model.Square.Item;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
	
	public static final int INDEX_PLAYER1 = 0;
	public static final int INDEX_PLAYER2 = 1;
	
	// configuration
	private final int width;
	private final int height;
	private int numStrawberries;
	
	// game state
	private Field field;
	private List<Player> players;
	private List<Position> currentPositions;
	private IntegerProperty strawberriesLeft = new SimpleIntegerProperty();
	private IntegerProperty playersTurn = new SimpleIntegerProperty();
	
	
	
	
	
	/**
	 * Create a new game and set a default configuration
	 */
	public Game() {
	
		this.width = 15;
		this.height = 10;
		this.numStrawberries = 20;
	}
	
	/**
	 * Create the players and initialize the field 
	 */
	public void init() {
	
		// create players and pieces
		players = List.of(new Player("Jan", Item.PIECE_PLAYER1), new Player("Birgit", Item.PIECE_PLAYER2));
		field = new Field(width, height);
		field.init();
		
		layoutField();
		
	}

	/**
	 * Create positions for the items and put them on the field
	 */
	private void layoutField() {
		
		// create positions
		List<Position> piecePositions = List.of(new Position(0, 0), new Position(width-1, height-1));
		currentPositions = new ArrayList<>(piecePositions);
		
		
		Random random = new Random();
		List<Position> strawberryPositions = Stream.generate(() -> new Position(random.nextInt(width), random.nextInt(height)))
												.filter(pos -> !pos.equals(piecePositions.get(INDEX_PLAYER1)) &&
																!pos.equals(piecePositions.get(INDEX_PLAYER2)))
												.limit(numStrawberries)
												.collect(Collectors.toList());
		
		// layout items at positions
		field.setItemAtPosition(piecePositions.get(INDEX_PLAYER1), players.get(INDEX_PLAYER1).getItem());
		field.setItemAtPosition(piecePositions.get(INDEX_PLAYER2), players.get(INDEX_PLAYER2).getItem());
		
		assert(strawberryPositions.size() == numStrawberries);
		
		for (Position pos : strawberryPositions) {
			
			field.setItemAtPosition(pos, Item.STRAWBERRY);
		}
		
		
	}
	
	/**
	 * Moves a piece from its current position to a new position
	 * @param move
	 * @throws MoveException
	 */
	public void move(Move move) throws MoveException {
		
		Position src = currentPositions.get(playersTurn.get());
		
		Position dest = destinationFromMove(src, move);

		// check that src and dest are inside board
		if (!field.positionInsideField(src) || !field.positionInsideField(dest)) {
			
			throw new MoveException("source or destination outside of field");
		}
		
		// check if type of item at src is piece
		if (field.getItemAtPosition(src) != players.get(playersTurn.get()).getItem()) {
			
			throw new MoveException("Item at src position is not a piece of player" + (getPlayersTurn()+1));
		}
		
		Item item = field.removeItemFromPosition(src);
		field.setItemAtPosition(dest, item);
		
		currentPositions.set(playersTurn.get(), dest);
		
		togglePlayersTurn();
		
	}
	
	/**
	 * Toggles the players turn between 0 and 1
	 */
	private void togglePlayersTurn() {
		
		playersTurn.set((playersTurn.get() + 1) % 2);
		
	}

	/**
	 * Calculate destination position, given the src position and the requested move
	 * @param src
	 * @param move
	 * @return
	 */
	private Position destinationFromMove(Position src, Move move) {
		
		int x = 0;
		int y = 0;
		
		switch(move.direction) {
		case UP:
			y = -1; break;
		case DOWN: 
			y = 1; break;
		case LEFT:
			x = -1; break;
		case RIGHT:
			x = 1; break;
		}
		
		x *= move.distance;
		y *= move.distance;
		
		return new Position(src.x + x, src.y + y);
	}

	public Field getField() {
		
		return field;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public final IntegerProperty strawberriesLeftProperty() {
		return this.strawberriesLeft;
	}
	

	public final int getStrawberriesLeft() {
		return this.strawberriesLeftProperty().get();
	}
	

	public final void setStrawberriesLeft(final int strawberriesLeft) {
		this.strawberriesLeftProperty().set(strawberriesLeft);
	}

	public final IntegerProperty playersTurnProperty() {
		return this.playersTurn;
	}
	

	public final int getPlayersTurn() {
		return this.playersTurnProperty().get();
	}
	

	public final void setPlayersTurn(final int playersTurn) {
		this.playersTurnProperty().set(playersTurn);
	}
	
	
	
	
	
}
