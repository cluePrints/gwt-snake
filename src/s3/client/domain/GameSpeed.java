package s3.client.domain;

public enum GameSpeed {
	LOW(500),
	NORMAL(400),
	HIGH(200),
	BLAZING(50);
	int timeQuant;

	private GameSpeed(int timeQuant) {
		this.timeQuant = timeQuant;
	}
	
	public int getTimeQuantMs() {
		return timeQuant;
	}
}
