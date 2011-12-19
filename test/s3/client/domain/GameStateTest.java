package s3.client.domain;

import static s3.client.domain.Position.at;

import org.junit.Assert;
import org.junit.Test;

import s3.client.artifact.Apple;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.domain.Snake;

public class GameStateTest {
	@Test
	public void shouldNotMakeSnakeLeaveTheFieldDueToResize() {
		GameState state = new GameState();
		state.snake = new Snake(at(3, 4), at(3, 5), at(3,6), at(2, 6), at(2,5), at(2,4), at(1,4));
		state.bonuses.clear();
		
		Position actual = state.latestFreeBottomRightPoint();
		Position expected = at(3+1,6+1);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotCoverTheSnakeByTheBoundOnResize() {
		GameState state = new GameState();
		state.snake = new Snake(at(6,7));
		
		state.tryResize(5,6);
		
		int width = state.getHorizontalCellsCount();
		int height = state.getVerticalCellsCount();
		Assert.assertEquals(7, width);
		Assert.assertEquals(8, height);
	}
	
	@Test
	public void shouldNotMakeBonusesLeaveTheFieldDueToResize() {
		GameState state = new GameState();
		Position bonusPosition = at(3,8);
		Position expected = at(3+1,8+1);
		state.snake = new Snake(at(1,1));
		state.bonuses.put(bonusPosition, new Apple(null, null));
		Position actual = state.latestFreeBottomRightPoint();
		Assert.assertEquals(expected, actual);
	}
}
