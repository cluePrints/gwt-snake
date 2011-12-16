package s3.client;

public class Position {
	private final int x;
	private final int y;
	
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public static Position at(int x, int y) {
		return new Position(x, y);
	}

	public boolean adjacentWith(Position p) {
		return false;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
