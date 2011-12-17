package s3.client.scoring;

public class Scoring {
	private int currentScore;
	private int bestScore;
	private ScoringStrategy strategy = new PointsPerArtifact(1);
	
	public void increase() {
		currentScore += strategy.points();
	}
}
