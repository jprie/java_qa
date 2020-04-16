package at.wifiwien.javaswe.strawberry_fields.controller;

import at.wifiwien.javaswe.strawberry_fields.exception.MoveException;
import at.wifiwien.javaswe.strawberry_fields.model.Position;

public class MoveAtFenceException extends MoveException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Position position;

	public MoveAtFenceException(String message, Position position) {
		super(message);
		
		this.position = position;

	}
	
	public Position getPosition() {
		
		return position;
	}
	
	
	
}
