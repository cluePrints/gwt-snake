package s3.client.scoring;

public class PointsPerArtifact implements ScoringStrategy {
	private int points;
	
	public PointsPerArtifact(int points) {
		super();
		this.points = points;
	}

	public int points() {
		return points;
	}
}