package at.wifiwien.javaswe.strawberry_fields.model;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	private Field field;
	private List<Player> players;
	private IntegerProperty strawberriesLeft = new SimpleIntegerProperty(); 
	
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
	
	
	
}
