package at.wifiwien.javaswe.strawberry_fields.model;

public class Move {
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public final int distance;
	public final Direction direction;
	
	public Move(int distance, Direction direction) {
		
		this.distance = distance;
		this.direction = direction;
	}
	
	
}
