package s3.client.domain.rules;

import java.util.Random;

import s3.client.artifact.Artifact;
import s3.client.artifact.ArtifactTracker;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.domain.Snake;

abstract class ArtifactCreation implements Rule {
	/*
	 * We need this static to prevent two artifact types from having 
	 * same random positions generated due to usage of the two copies
	 * of the same random sequence(due to the same seed).
	 * 
	 * Relying on timestamp won't help as resolution won't notice the change
	 * between creation sites. 
	 */
	private static Random random = new Random();
	private float probability = 0.1F;
	
	@Override
	public final void evaluate(GameState game) {			
		if (random.nextFloat() > probability)
			return;
		
		Position newPos = inventRandomPos(game);		
		
		Snake snake = game.getSnake();
		boolean isUsedBySnake = snake.hasSegment(newPos);
		if (isUsedBySnake) {
			System.out.println("Used by snake "+newPos);
			return;
		}
		
		Artifact newbie = newArtifact(newPos, game);
		ArtifactTracker artifacts = game.getArtifacts();
		artifacts.tryPutAt(newPos, newbie);
	}
	
	abstract Artifact newArtifact(Position position, GameState game); 

	Position inventRandomPos(GameState state) {
		int xBound = state.getHorizontalCellsCount();
		int yBound = state.getVerticalCellsCount();
		
		int newX = random.nextInt(xBound);
		int newY = random.nextInt(yBound);
		Position newPos = Position.at(newX, newY);
		return newPos;
	}
}
