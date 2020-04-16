package at.wifiwien.javaswe.strawberry_fields.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import at.wifiwien.javaswe.strawberry_fields.model.item.Piece;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient private StringProperty name = new SimpleStringProperty();
	transient private IntegerProperty score = new SimpleIntegerProperty();
	private Piece piece;

	/**
	 * reading Game from ObjectInputStream
	 * @param ois
	 */
	private void readObject(ObjectInputStream ois) {

		try {
			ois.defaultReadObject();
			name = new SimpleStringProperty((String)ois.readObject());
			score = new SimpleIntegerProperty(ois.readInt());
			
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
			oos.writeObject(name.get());
			oos.writeInt(score.get());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public Player(String name, Piece piece) {
		super();
		this.name.set(name);
		this.piece = piece;
	}
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
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
