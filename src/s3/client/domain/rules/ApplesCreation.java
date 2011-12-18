package s3.client.domain.rules;

import java.util.Random;

import s3.client.artifact.Apple;
import s3.client.artifact.ArtifactTracker;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.domain.Snake;
import s3.client.scoring.Scoring;

public class ApplesCreation implements Rule {	
	private Random random = new Random();
	
	@Override
	public void evaluate(GameState state) {			
		if (random.nextInt(5) != 3)
			return;
		Position newPos = inventRandomPos(state);		
		
		Snake snake = state.getSnake();
		boolean isUsedBySnake = snake.hasSegment(newPos);
		if (isUsedBySnake) {
			System.out.println("Used by snake "+newPos);
			return;
		}
		
		ArtifactTracker artifacts = state.getArtifacts();
		Scoring scoring = state.getScoring();
		artifacts.tryPutAt(newPos, new Apple(newPos, scoring));
	}

	private Position inventRandomPos(GameState state) {
		int xBound = state.getHorizontalCellsCount();
		int yBound = state.getVerticalCellsCount();
		
		int newX = random.nextInt(xBound);
		int newY = random.nextInt(yBound);
		Position newPos = Position.at(newX, newY);
		return newPos;
	}
}
