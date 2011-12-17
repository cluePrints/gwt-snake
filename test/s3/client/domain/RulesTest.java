package s3.client.domain;

import junit.framework.Assert;

import org.junit.Test;

import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.domain.Rules;
import s3.client.domain.Snake;
import static s3.client.domain.Position.*;

public class RulesTest {
	Rules rules = new Rules();
	
	@Test
	public void shouldNotSayGameOverWhenSnakeIsFarFromDying() {
		GameState state = new GameState();
		
		Assert.assertFalse(rules.gameOver(state));
	}
	
	@Test
	public void shouldBeGameOverIfSnakeLeftTheField() {
		GameState state = new GameState();
		
		state.snake = new Snake(at(-1, 0));
				
		Assert.assertTrue(rules.gameOver(state));
	}
	
	@Test
	public void shouldNotBeGameOverWhenSnakeIsJustAboutToLeave() {
		GameState state = new GameState();
		
		state.snake = new Snake(at(0, 0));
				
		Assert.assertFalse(rules.gameOver(state));
	}
	
	@Test
	public void shouldBeGameOverIfSnakeLeftTheFieldAnotherDir() {
		GameState state = new GameState();
		
		Position farThenBottomRight = at(state.getHorizontalCellsCount(), state.getVerticalCellsCount());
		state.snake = new Snake(farThenBottomRight);
				
		Assert.assertTrue(rules.gameOver(state));
	}

	@Test
	public void shouldNotBeGameOverWhenSnakeIsJustAboutToLeaveInAnotherDirection() {
		GameState state = new GameState();

		Position bottomRight = at(state.getHorizontalCellsCount()-1, state.getVerticalCellsCount()-1);
		state.snake = new Snake(bottomRight);
				
		Assert.assertFalse(rules.gameOver(state));
	}
	
	@Test
	public void shouldBeGameOverWhenSnakeCrossedItself() {
		GameState state = new GameState();
		state.snake = new Snake(at(1,1), at(1,2), at(2,2), at(2,1), at(1,1));
				
		Assert.assertTrue(rules.gameOver(state));
	}

}
