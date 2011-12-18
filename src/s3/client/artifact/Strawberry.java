package s3.client.artifact;

import s3.client.domain.CellContent;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.platform.Platform;
import s3.client.scoring.Multiplied;
import s3.client.scoring.Scoring;
import s3.client.scoring.ScoringStrategy;

public class Strawberry implements Artifact {
	int effectPeriodSec = 15;
	private Position position;
	private Platform platform;
	private int multiplier = 2;
	
	public Strawberry(Position position, Platform platform) {
		super();
		this.position = position;
		this.platform = platform;
	}

	@Override
	public void reflectConsumption(GameState game) {
		game.getArtifacts().removeAt(position);
		
		Scoring scoring = game.getScoring();
		sheduleStrategyRestore(scoring);
		setMultipliedScoring(scoring);
	}
	
	@Override
	public CellContent type() {
		return CellContent.STRAWBERRY;
	}
	
	@Override
	public boolean causesGrowth() {
		return false;
	}

	private void setMultipliedScoring(Scoring scoring) {
		ScoringStrategy original = scoring.getStrategy();
		scoring.setStrategy(new Multiplied(original, multiplier));
	}

	private void sheduleStrategyRestore(Scoring scoring) {
		Runnable task = revertStrategyTask(scoring);
		int delayMs = effectPeriodSec * 1000;
		platform.scheduleLater(task, delayMs);
	}

	private Runnable revertStrategyTask(final Scoring scoring) {
		Runnable task = new Runnable(){
			public void run() {
				revertStackableStrategyIfPossible(scoring);
			}
		};
		return task;
	}
	
	private void revertStackableStrategyIfPossible(final Scoring scoring) {
		ScoringStrategy currStrategy = scoring.getStrategy();
		if (currStrategy instanceof Multiplied) {
			ScoringStrategy oldStrategy = ((Multiplied) currStrategy).getOriginal();
			scoring.setStrategy(oldStrategy);
		}
	};
}