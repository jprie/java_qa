package at.wifiwien.javaswe.strawberry_fields.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import at.wifiwien.javaswe.strawberry_fields.model.item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Field implements Serializable {

	private final int width;
	private final int height;

	transient ObservableList<Square> squares = FXCollections.observableArrayList();

	/**
	 * reading object from ObjectOutputStream
	 * @param oos
	 */
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream ois) {

		try {
			ois.defaultReadObject();
			squares = FXCollections.observableArrayList((List<Square>)ois.readObject());
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * writing object to ObjectOutputStream
	 * @param oos
	 */
	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(new ArrayList<Square>(squares));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	
	
	
	
	
	
	
	public Field(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void init() {
		
		for (int row=0; row < height; row++) {
			for (int col=0; col <width; col++) {
				
				squares.add(new Square());
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
		squares.get(pos.y * width + pos.x).setItem(null);
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
