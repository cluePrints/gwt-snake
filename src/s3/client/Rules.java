package s3.client;

public class Rules {
	public boolean gameOver(GameState state) {
		Snake snake = state.getSnake();
		boolean bitesItself = snake.crossesItself();		
		boolean leftTheBounds = leftTheBounds(state);
		
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
