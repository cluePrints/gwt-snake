package s3.client.domain;

public enum GameSpeed {
	LOW(500),
	NORMAL(400),
	HIGH(200);
	int timeQuant;

	private GameSpeed(int timeQuant) {
		this.timeQuant = timeQuant;
	}
	
	public int getTimeQuant() {
		return timeQuant;
	}
}
