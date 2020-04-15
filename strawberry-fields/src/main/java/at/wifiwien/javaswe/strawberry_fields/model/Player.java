package at.wifiwien.javaswe.strawberry_fields.model;

import at.wifiwien.javaswe.strawberry_fields.model.item.Item;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

	private StringProperty name = new SimpleStringProperty();
	private IntegerProperty score = new SimpleIntegerProperty();
	private Item item;

	public Player(String name, Item item) {
		super();
		this.name.set(name);
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	

	public final IntegerProperty scoreProperty() {
		return this.score;
	}
	

	public final int getScore() {
		return this.scoreProperty().get();
	}
	

	public final void setScore(final int score) {
		this.scoreProperty().set(score);
	}
	
	
	

}
