package s3.client;

import java.util.Collection;

public class GameState {
	private int horizontalCellsCount=20;
	private int verticalCellsCount=20;
	private Direction snakeDirection = Direction.DOWN;
	private Snake snake = new Snake(Position.at(5,2)) {{
		append(new Position(5,3));
		append(new Position(5,4));
		append(new Position(5,5));
		append(new Position(5,6));
	}};
	
	public Position lastSnakeCell() {
		return snake.getLast();
	}
	
	public void tick() {
		snake.moveTo(snakeDirection);
	}
	
	public Direction getSnakeDirection() {
		return snakeDirection;
	}
	
	public void setSnakeDirection(Direction snakeDirection) {
		this.snakeDirection = snakeDirection;
	}
	
	public Collection<Position> getSnakeSegments() {
		return snake.getSegments();
	}
	
	public int getHorizontalCellsCount() {
		return horizontalCellsCount;
	}
	
	public int getVerticalCellsCount() {
		return verticalCellsCount;
	}
}
