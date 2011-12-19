package s3.client.domain;

import org.junit.Assert;
import org.junit.Test;

import s3.client.domain.Direction;
import s3.client.domain.Position;
import s3.client.domain.Snake;

public class SnakeTest {
	@Test(expected=IllegalArgumentException.class)
	public void shouldRejectToAddNonAdjacentCells() {
		Snake snake = new Snake(Position.at(3, 3));
		snake.append(Position.at(4,4));
	}
	
	@Test
	public void shouldAppendAdjacentCell() {
		Snake snake = new Snake(Position.at(5, 3));
		snake.append(Position.at(4,3));
		
		Assert.assertEquals(2, snake.getSegments().size());
	}
	
	@Test
	public void shouldCorrectlyMoveSingleCellSnake() {
		Position startingPos = Position.at(5, 3);
		Position expectedPos = Position.at(5, 4);
		Snake snake = new Snake(startingPos);		
		snake.moveTo(Direction.DOWN);
		
		Assert.assertEquals(1, snake.getSegments().size());
		Assert.assertEquals(expectedPos, snake.getSegments().get(0));
	}
}
