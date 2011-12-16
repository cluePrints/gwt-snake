package s3.client;

public enum Direction {
	UP(0, -1),
	DOWN(0,1),
	LEFT(-1,0),
	RIGHT(1,0);
	private int deltaX;
	private int deltaY;
	private Direction(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	public int getDeltaX() {
		return deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}
	
	public Position moveFrom(Position base) {
		return Position.at(base.getX() + deltaX, base.getY() + deltaY);
	}
}
