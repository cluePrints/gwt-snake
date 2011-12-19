package s3.client.controller;

import static s3.client.domain.Direction.DOWN;
import static s3.client.domain.Direction.LEFT;
import static s3.client.domain.Direction.RIGHT;

import org.junit.Assert;
import org.junit.Test;

import s3.client.domain.Direction;

public class KeyToDirectionStrategyTest {
	@Test
	public void shouldNotAllowToChangeDirectionToOpposite() {
		Direction expected = RIGHT;
		Direction actual = DirectionTransitions.from(RIGHT).to(LEFT);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotChangeDirectionIfItIsTheSame() {
		Direction before = DOWN;
		Direction expected = DOWN;
		
		Direction actual = DirectionTransitions.from(before).to(before);
		
		Assert.assertEquals(expected, actual);
	}
}
