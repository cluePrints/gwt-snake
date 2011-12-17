package s3.client.artifact;

import s3.client.domain.GameState;
import s3.client.domain.Position;

public class Apple implements Artifact {
	private Position position;
	private ArtifactTracker tracker;
	
	public Apple(Position position, ArtifactTracker tracker) {
		super();
		this.position = position;
		this.tracker = tracker;
	}

	@Override
	public void reflectConsumption(GameState state) {
		state.getSnake().append(position);
		tracker.removeAt(position);
	}
	
	@Override
	public String type() {
		return "apple";
	}
}