package s3.client.artifact;

import s3.client.domain.GameState;

public class Apple implements Artifact {
	@Override
	public void reflectConsumption(GameState state) {
		state.getScoring().increase();
	}
}