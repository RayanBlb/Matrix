package erreur;

import matrix.Direction;

public class ErreurDeplacementException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private Direction direction;
	
	public ErreurDeplacementException(Direction d) {
		super();
		this.direction = d;
	}

	public Direction getDirection() {
		return direction;
	}
}
