package at.wifiwien.javaswe.strawberry_fields.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Square {

	public enum Item {
		PIECE_PLAYER1,
		PIECE_PLAYER2,
		EMPTY,
		STRAWBERRY
	}
	
	ObjectProperty<Item> item = new SimpleObjectProperty<Square.Item>();

	public Square(Item item) {
		super();
		this.item.set(item);
	}

	public final ObjectProperty<Item> itemProperty() {
		return this.item;
	}
	

	public final Item getItem() {
		return this.itemProperty().get();
	}
	

	public final void setItem(final Item item) {
		this.itemProperty().set(item);
	}
	

	
	
}
