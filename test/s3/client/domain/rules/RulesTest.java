package s3.client.domain.rules;

import static s3.client.domain.Position.at;
import static s3.client.domain.GameStatus.*;
import junit.framework.Assert;

import org.junit.Test;

import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.domain.Snake;

public class RulesTest {
	GameOver rules = new GameOver();
	
	@Test
	public void shouldNotSayGameOverWhenSnakeIsFarFromDying() {
		GameState state = new GameState();
		
		Assert.assertFalse(rules.isOver(state));
	}
	
	@Test
	public void shouldBeGameOverIfSnakeLeftTheField() {
		GameState state = new GameState();
		
		state.setSnake(new Snake(at(-1, 0)));
				
		Assert.assertTrue(rules.isOver(state));
	}
	
	@Test
	public void shouldNotBeGameOverWhenSnakeIsJustAboutToLeave() {
		GameState state = new GameState();
		
		state.setSnake(new Snake(at(0, 0)));
				
		Assert.assertFalse(rules.isOver(state));
	}
	
	@Test
	public void shouldBeGameOverIfSnakeLeftTheFieldAnotherDir() {
		GameState state = new GameState();
		
		Position farThenBottomRight = at(state.getHorizontalCellsCount(), state.getVerticalCellsCount());
		state.setSnake(new Snake(farThenBottomRight));
				
		Assert.assertTrue(rules.isOver(state));
	}

	@Test
	public void shouldNotBeGameOverWhenSnakeIsJustAboutToLeaveInAnotherDirection() {
		GameState state = new GameState();

		Position bottomRight = at(state.getHorizontalCellsCount()-1, state.getVerticalCellsCount()-1);
		state.setSnake(new Snake(bottomRight));
				
		Assert.assertFalse(rules.isOver(state));
	}
	
	@Test
	public void shouldBeGameOverWhenSnakeCrossedItself() {
		GameState state = new GameState();
		state.setSnake(new Snake(at(1,1), at(1,2), at(2,2), at(2,1), at(1,1)));
				
		Assert.assertTrue(rules.isOver(state));
	}

}
