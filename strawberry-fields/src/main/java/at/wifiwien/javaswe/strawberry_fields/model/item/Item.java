package at.wifiwien.javaswe.strawberry_fields.model.item;

import java.io.Serializable;

public class Item implements Serializable {
	
	// type used as constant var for switch case
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public enum Type {
		PIECE, STRAWBERRY, FENCE
	}
	
	private Type type;
	private String face;

	public Item(Type type, String face) {
		this.face = face;
		this.type = type;
	}
	
	

	public Type getType() {
		return type;
	}



	public String getFace() {
		return face;
	}



	@Override
	public String toString() {

		return face;
	}
}
