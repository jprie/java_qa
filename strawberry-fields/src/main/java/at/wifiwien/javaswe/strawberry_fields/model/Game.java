package at.wifiwien.javaswe.strawberry_fields.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.wifiwien.javaswe.strawberry_fields.exception.MoveException;
import at.wifiwien.javaswe.strawberry_fields.model.item.Item;
import at.wifiwien.javaswe.strawberry_fields.model.item.Piece;
import at.wifiwien.javaswe.strawberry_fields.model.item.Strawberry;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Game {
	
	public static final int INDEX_PLAYER1 = 0;
	public static final int INDEX_PLAYER2 = 1;
	
	// configuration
	private final int width;
	private final int height;
	private int numStrawberries;
	private List<Position> initPositions;
	
	// game state
	private Field field;
	private List<Player> players;
	private List<Position> currentPositions;
	private IntegerProperty strawberriesLeft = new SimpleIntegerProperty();
	private IntegerProperty playersTurn = new SimpleIntegerProperty();
	private ObjectProperty<Optional<Player>> winner = new SimpleObjectProperty<>();
	
	
	
	
	/**
	 * Create a new game and set a default configuration
	 */
	public Game() {
	
		this.width = 15;
		this.height = 10;
		this.numStrawberries = 2;
	}
	
	/**
	 * Create the players and initialize the field 
	 */
	public void init() {
	
		// create players and pieces
		players = List.of(new Player("Jan", new Piece()), new Player("Birgit", new Piece()));
		field = new Field(width, height);
		field.init();
		
		setStrawberriesLeft(numStrawberries);
		layoutField();
		
	}

	/**
	 * Create positions for the items and put them on the field
	 */
	private void layoutField() {
		
		// create positions
		List<Position> piecePositions = List.of(new Position(0, 0), new Position(width-1, height-1));
		currentPositions = new ArrayList<>(piecePositions);
		initPositions = new ArrayList<>(piecePositions);
		
		
		Random random = new Random();
		List<Position> strawberryPositions = Stream.generate(() -> new Position(random.nextInt(width), random.nextInt(height)))
												.filter(pos -> !pos.equals(piecePositions.get(INDEX_PLAYER1)) &&
																!pos.equals(piecePositions.get(INDEX_PLAYER2)))
												.distinct()
												.limit(numStrawberries)
												.collect(Collectors.toList());
		
		
		// layout items at positions
		field.setItemAtPosition(piecePositions.get(INDEX_PLAYER1), players.get(INDEX_PLAYER1).getItem());
		field.setItemAtPosition(piecePositions.get(INDEX_PLAYER2), players.get(INDEX_PLAYER2).getItem());
		
		assert(strawberryPositions.size() == numStrawberries);
		
		for (Position pos : strawberryPositions) {
			
			field.setItemAtPosition(pos, new Strawberry());
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
		
		int indexOpponent = getOpponentIndex();

		// check that src and dest are inside board
		if (!field.positionInsideField(src) || !field.positionInsideField(dest)) {
			
			throw new MoveException("source or destination outside of field");
		}
		
		// check if type of item at src is piece
		if (field.getItemAtPosition(src) != players.get(playersTurn.get()).getItem()) {
			
			throw new MoveException("Item at src position is not a piece of player" + (getPlayersTurn()+1));
		}
		
		Item item = field.removeItemFromPosition(src);
		Item destItem = field.getItemAtPosition(dest);
		
		field.setItemAtPosition(dest, item);
		currentPositions.set(playersTurn.get(), dest);
		
		// increment score when moving over strawberry
		if (destItem instanceof Strawberry) {
			
			int currentScore = players.get(getPlayersTurn()).getScore();
			players.get(getPlayersTurn()).setScore(currentScore+1);
			int strawberryCount = getStrawberriesLeft();
			setStrawberriesLeft(strawberryCount-1);
			
			// check if last strawberry taken
			if (strawberriesLeft.isEqualTo(0).get()) {
				
				determineWinner();
			}
			
		// put opponent's piece back to init position
		} else if (destItem == players.get(indexOpponent).getItem()) {
			
			// Note: Here we see that our design will not work for more the 2 players elegantly, because it is difficult to find out
			// that we catch a (any) piece which is not ours. This is going to be easier if we have a class for pieces and strawberries
			// and fences
			
			Position initPosition = initPositions.get(indexOpponent);
			field.setItemAtPosition(initPosition, destItem);
			
			// update current position
			currentPositions.set(indexOpponent, initPosition);
			
		}
	
		
		togglePlayersTurn();
		
	}
	
	/**
	 * Toggles the players turn between 0 and 1
	 */
	private void togglePlayersTurn() {
		
		playersTurn.set((playersTurn.get() + 1) % 2);
		
	}
	
	/**
	 * Returns the opponents index
	 * @return
	 */
	private int getOpponentIndex() {
		
		return (playersTurn.get() + 1) % 2;
	}

	/**
	 * Determine which player got a higher score
	 */
	private void determineWinner() {
		
		Player optWinner;
		
		if (players.get(INDEX_PLAYER1).getScore() > players.get(INDEX_PLAYER2).getScore()) {
			
			optWinner = players.get(INDEX_PLAYER1);
		} else if (players.get(INDEX_PLAYER2).getScore() > players.get(INDEX_PLAYER1).getScore()) {
			
			optWinner = players.get(INDEX_PLAYER2);
		} else {
			
			optWinner = null;
		}
		
		winner.set(Optional.ofNullable(optWinner));
		
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

	public final ObjectProperty<Optional<Player>> winnerProperty() {
		return this.winner;
	}
	

	public final Optional<Player> getWinner() {
		return this.winnerProperty().get();
	}
	

	public final void setWinner(final Optional<Player> winner) {
		this.winnerProperty().set(winner);
	}
	
	
	
	
	
	
}
