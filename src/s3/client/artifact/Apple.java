package s3.client.artifact;

import s3.client.domain.CellContent;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.scoring.Scoring;

public class Apple implements Artifact {
	private Position position;
	private Scoring scoring;
	
	public Apple(Position position, Scoring scoring) {
		super();
		this.position = position;
		this.scoring = scoring;
	}

	@Override
	public void reflectConsumption(GameState state) {
		state.getSnake().append(position);
		state.getArtifacts().removeAt(position);
		scoring.increase();
	}
	
	@Override
	public CellContent type() {
		return CellContent.APPLE;
	}
	
	@Override
	public boolean causesGrowth() {
		return true;
	}
}