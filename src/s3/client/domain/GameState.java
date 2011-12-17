package s3.client.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import s3.client.artifact.Artifact;
import s3.client.scoring.Scoring;

public class GameState {
	private int horizontalCellsCount = 20;
	private int verticalCellsCount = 20;
	private Direction snakeDirection = Direction.DOWN;	
	private Scoring scoring = new Scoring();
	
	Map<Position, Artifact> bonuses = new HashMap<Position, Artifact>();		
	Snake snake;
	
	public GameState() {
		 reset();
	}
	
	public void moveSnake() {
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
	
	public Position latestFreeBottomRightPoint() {
		Collection<Position> usedPositions = new HashSet<Position>();
		usedPositions.addAll(snake.getSegments());
		usedPositions.addAll(bonuses.keySet());
		
		int maxX=0;
		int maxY=0;
		for (Position p : usedPositions) {
			if (p.getX() > maxX) {
				maxX = p.getX();
			}
			if (p.getY() > maxY) {
				maxY = p.getY();
			}
		}
		return Position.at(maxX, maxY);
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	public void resize(int desiredWidth, int desiredHeight) {
		
	}
	
	public void reset() {
		snake = new Snake(fieldCenter());
	}
	
	public Position getSnakeHead() {
		return snake.getHead();
	}
	
	public Scoring getScoring() {
		return scoring;
	}
	
	private Position fieldCenter() {
		return Position.at(horizontalCellsCount/2, verticalCellsCount/2);
	}
}
