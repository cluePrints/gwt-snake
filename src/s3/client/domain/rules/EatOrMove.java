package s3.client.domain.rules;

import s3.client.artifact.Artifact;
import s3.client.artifact.ArtifactTracker;
import s3.client.domain.Direction;
import s3.client.domain.GameState;
import s3.client.domain.Position;

public class EatOrMove implements Rule {
	@Override
	public void evaluate(GameState game) {				
		boolean ate = tryConsume(game);
		if (!ate) {
			game.moveSnake();
		}
	}

	public boolean tryConsume(GameState state) {
		ArtifactTracker artifacts = state.getArtifacts();
		Direction direction = state.getSnakeDirection();
		
		Position head = state.getSnakeHead();
		Position headFuture = head.getAdjacentCellToThe(direction);
		Artifact target = artifacts.getArtifactAt(headFuture);
		if (target == null)
			return false;		
		
		artifacts.removeAt(headFuture);
		target.reflectConsumption(state);
		return true;
	}
}
