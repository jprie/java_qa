package at.wifiwien.javaswe.strawberry_fields.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import at.wifiwien.javaswe.strawberry_fields.model.item.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Square implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient ObjectProperty<Item> item = new SimpleObjectProperty<>();

	/**
	 * reading Game from ObjectInputStream
	 * @param ois
	 */
	private void readObject(ObjectInputStream ois) {

		try {
			ois.defaultReadObject();
			item = new SimpleObjectProperty<Item>((Item)ois.readObject());
			
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
			oos.writeObject(item.get());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
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
