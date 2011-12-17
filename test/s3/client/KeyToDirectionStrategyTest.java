package s3.client;

import org.junit.Assert;
import org.junit.Test;

import s3.client.domain.Direction;
import s3.client.presentation.KeyToDirectionStrategy;

import com.google.gwt.event.dom.client.KeyCodes;

public class KeyToDirectionStrategyTest {
	KeyToDirectionStrategy unit = new KeyToDirectionStrategy();
	
	@Test
	public void shouldNotAllowToChangeDirectionToOpposite() {
		Direction before = Direction.RIGHT;
		Direction expected = Direction.RIGHT;
		
		Direction actual = unit.decide(before, KeyCodes.KEY_LEFT);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotChangeDirectionIfItIsTheSame() {
		Direction before = Direction.DOWN;
		Direction expected = Direction.DOWN;
		
		Direction actual = unit.decide(before, KeyCodes.KEY_DOWN);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldChangeDirectionOnProperKey() {
		Direction before = Direction.DOWN;
		Direction expected = Direction.LEFT;
		
		Direction actual = unit.decide(before, KeyCodes.KEY_LEFT);
		
		Assert.assertEquals(expected, actual);
	}
}
