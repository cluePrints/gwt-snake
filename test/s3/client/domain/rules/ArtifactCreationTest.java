package s3.client.domain.rules;

import junit.framework.Assert;

import org.junit.Test;

import s3.client.domain.GameState;
import s3.client.domain.Position;

public class ArtifactCreationTest{
	@Test
	public void shouldNotHaveSamePositionsGenerated() {
		GameState game = new GameState();
		Position byApple = new AppleCreation().inventRandomPos(game);
		Position byStrawberry = new StrawberryCreation().inventRandomPos(game);
		
		Assert.assertNotSame(byApple, byStrawberry);
	}
}
