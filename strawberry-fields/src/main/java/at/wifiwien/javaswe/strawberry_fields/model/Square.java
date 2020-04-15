package at.wifiwien.javaswe.strawberry_fields.model;

import at.wifiwien.javaswe.strawberry_fields.model.item.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Square {
	
	ObjectProperty<Item> item = new SimpleObjectProperty<>();

	public Square() {
		super();
		
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
	
	@Override
	public String toString() {
	
		return item.get().toString();
	}
	
	
}
