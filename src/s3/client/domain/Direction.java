package s3.client.domain;

public enum Direction {
	DOWN(0,1),
	RIGHT(1,0),
	UP (0,-1),
	LEFT(-1,0);
	
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
}
