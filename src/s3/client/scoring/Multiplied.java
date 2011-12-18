package s3.client.scoring;

public class Multiplied implements ScoringStrategy {
	private ScoringStrategy original;
	private int multiplier;
	
	public Multiplied(ScoringStrategy original, int multiplier) {
		super();
		this.original = original;
		this.multiplier = multiplier;
	}
	
	public ScoringStrategy getOriginal() {
		return original;
	}

	public int points() {
		return original.points() * multiplier;
	}
}