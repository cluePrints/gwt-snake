package s3.client.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import s3.client.artifact.Artifact;
import s3.client.artifact.ArtifactRegistry;
import s3.client.scoring.Scoring;

public class GameState {
	private int horizontalCellsCount = 20;
	private int verticalCellsCount = 20;
	private GameSpeed speed = GameSpeed.LOW;
	private Direction snakeDirection;
	private Scoring scoring = new Scoring();
	private ArtifactRegistry artifacts = new ArtifactRegistry();
	private GameStatus status;

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

		int maxX = 0;
		int maxY = 0;
		for (Position p : usedPositions) {
			if (p.getX() > maxX) {
				maxX = p.getX();
			}
			if (p.getY() > maxY) {
				maxY = p.getY();
			}
		}
		return Position.at(maxX + 1, maxY + 1);
	}

	public Snake getSnake() {
		return snake;
	}

	public void tryResize(int desiredWidth, int desiredHeight) {
		Position upperBound = latestFreeBottomRightPoint();
		int width = Math.max(desiredWidth, upperBound.getX());
		int height = Math.max(desiredHeight, upperBound.getY());
		
		Position lowerBound = Position.at(5, 5);
		width = Math.max(width, lowerBound.getX());
		height = Math.max(height, lowerBound.getY());
		
		horizontalCellsCount = width;
		verticalCellsCount = height;
	}

	public void reset() {
		snake = new Snake(fieldCenter());
		artifacts.clear();
		snakeDirection = Direction.DOWN;
		status = GameStatus.IN_PROGRESS;
	}

	public Position getSnakeHead() {
		return snake.getHead();
	}

	public Scoring getScoring() {
		return scoring;
	}

	private Position fieldCenter() {
		return Position.at(horizontalCellsCount / 2, verticalCellsCount / 2);
	}

	public GameSpeed getSpeed() {
		return speed;
	}

	public void setSpeed(GameSpeed speed) {
		this.speed = speed;
	}

	public ArtifactRegistry getArtifacts() {
		return artifacts;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}
}
