package at.wifiwien.javaswe.strawberry_fields.model;

import at.wifiwien.javaswe.strawberry_fields.model.Square.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Field {

	private final int width;
	private final int height;

	ObservableList<Square> squares = FXCollections.observableArrayList();

	public Field(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void init() {
		
		for (int row=0; row < height; row++) {
			for (int col=0; col <width; col++) {
				
				squares.add(new Square(Item.EMPTY));
			}
		}
		
	}
	
	@Override
	public String toString() {
		String toString = "";
		
		for (int row=0; row < height; row++) {
			for (int col=0; col <width; col++) {
				
				toString += squares.get(row*width + col);
			}
			toString += "\n";
		}
		return toString;
	}

	public void setItemAtPosition(Position pos, Item item) {
		
		squares.get(pos.y * width + pos.x).setItem(item);
	}
	
	public Item getItemAtPosition(Position pos) {
		
		return squares.get(pos.y * width + pos.x).getItem();
	}
	
	public Item removeItemFromPosition(Position pos) {
		
		Item item = squares.get(pos.y * width + pos.x).getItem();
		squares.get(pos.y * width + pos.x).setItem(Item.EMPTY);
		return item;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ObservableList<Square> getSquares() {
		return squares;
	}

	public boolean positionInsideField(Position pos) {
	 	
		return pos.x < width  && pos.x >= 0 &&
				pos.y < height && pos.y >= 0;
		
	}
	
	
}
