package s3.client.domain.rules;

import s3.client.domain.GameState;
import s3.client.domain.GameStatus;
import s3.client.domain.Position;
import s3.client.domain.Snake;

public class GameOver implements Rule {
	@Override
	public void evaluate(GameState game) {
		if (isOver(game)) {
			game.setStatus(GameStatus.OVER);
		}
	}
	
	boolean isOver(GameState game) {
		Snake snake = game.getSnake();
		boolean bitesItself = snake.crossesItself();		
		boolean leftTheBounds = leftTheBounds(game);
		
		return bitesItself || leftTheBounds;
	}

	private boolean leftTheBounds(GameState state) {
		Snake snake = state.getSnake();
		
		// head will be the first to leave the field
		Position headPos = snake.getHead();
		int x = headPos.getX();
		int y = headPos.getY();
		boolean leftTheHorizontalBounds = (x<0) || (x>=state.getHorizontalCellsCount());
		boolean leftTheVerticalBounds = (y<0) || (y>=state.getVerticalCellsCount());
		boolean leftTheBounds = leftTheHorizontalBounds || leftTheVerticalBounds;
		return leftTheBounds;
	}
}
