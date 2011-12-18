package s3.client.scoring;

public class Scoring {
	private int currentScore;
	private int bestScore;
	private ScoringStrategy strategy = new PointsPerArtifact(1);
	
	public void increase() {
		currentScore += strategy.points();
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	
	public int getBestScore() {
		return bestScore;
	}
	
	public void gameOver() {
		if (currentScore > bestScore) {
			bestScore = currentScore;
		}
		currentScore = 0;
	}
	
	public ScoringStrategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(ScoringStrategy strategy) {
		this.strategy = strategy;
	}
}
