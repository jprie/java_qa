package at.wifiwien.javaswe.strawberry_fields.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.wifiwien.javaswe.strawberry_fields.application.Constants;
import at.wifiwien.javaswe.strawberry_fields.controller.MoveAtFenceException;
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
	
	private Settings settings;
	

	// configuration
	private final int numColumns;
	private final int numRows;
	private int numStrawberries;
	private List<Position> initPositions;
	
	// game state
	private Field field;
	private List<Player> players;
	private List<Position> currentPositions;
	private List<Position> fencePositions;
	private IntegerProperty strawberriesLeft = new SimpleIntegerProperty();
	private IntegerProperty playersTurn = new SimpleIntegerProperty();
	private ObjectProperty<Optional<Player>> winner = new SimpleObjectProperty<>();
	
	

	/**
	 * Helper to determine the bounds for number of strawberries 
	 * @return
	 */
	public double getMaxNumStrawberries() {

		return settings.getNumColumns()*settings.getNumRows()/Constants.MAX_NUM_STRAWBERRIES_DIVIDER;
	}
	
	public double getMinNumStrawberries() {

		return Constants.MIN_NUM_STRAWBERRIES;
	}


	/**
	 * Create a new game and set a default configuration
	 */
	public Game(Settings settings) {
	
		this.settings = settings;
		
		System.out.println("Create new game with settings: " + settings);
		this.numColumns = settings.getNumColumns();
		this.numRows = settings.getNumRows();
		this.numStrawberries = settings.getNumStrawberries();
	}
	
	/**
	 * Create the players and initialize the field 
	 */
	public void init() {
	
		// create players and pieces
		players = List.of(new Player(settings.getNamePlayer1(), new Piece()), new Player(settings.getNamePlayer2(), new Piece()));
		field = new Field(numColumns, numRows);
		field.init();
		
		setStrawberriesLeft(numStrawberries);
		layoutField();
		
	}

	/**
	 * Create positions for the items and put them on the field
	 */
	private void layoutField() {
		
		// create positions
		List<Position> piecePositions = List.of(new Position(0, 0), new Position(numColumns-1, numRows-1));
		currentPositions = new ArrayList<>(piecePositions);
		initPositions = new ArrayList<>(piecePositions);
		
		// create fence positions
		
		fencePositions = List.of(new Position(numColumns/2-2,  numRows/2), new Position(numColumns/2-1,  numRows/2), new Position(numColumns/2, numRows/2),
									new Position(numColumns/2+1, numRows/2), new Position(numColumns/2+2,  numRows/2));
		
		for (Position p : fencePositions) {
			field.setItemAtPosition(p, new Fence());
		}
		
		
		
		Random random = new Random();
		
		// generate random positions and filter for piece and fence positions
		List<Position> strawberryPositions = Stream.generate(() -> new Position(random.nextInt(numColumns), random.nextInt(numRows)))
												.filter(pos -> !pos.equals(piecePositions.get(INDEX_PLAYER1)) &&
																!pos.equals(piecePositions.get(INDEX_PLAYER2)))
												.filter(pos -> !fencePositions.contains(pos))
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
		
		// check if dest item of type fence
		Item destItem = field.getItemAtPosition(dest);
		if (destItem instanceof Fence) {
			
			throw new MoveAtFenceException("Cannot surpass fence at destination", dest);
		}
		
		// execute move
		Item item = field.removeItemFromPosition(src);
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
