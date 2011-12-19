package s3.client.artifact;

import junit.framework.Assert;

import org.junit.Test;

import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.platform.J2SEPlatform;
import s3.client.scoring.Scoring;

public class StrawberryTest{
	@Test
	public void shouldChangesScoring() {
		GameState game = new GameState();
		Scoring scoring = game.getScoring();
		
		Strawberry s = new Strawberry(Position.at(11,10), new J2SEPlatform());
		
		s.reflectConsumption(game);
		
		scoring.increase();
		Assert.assertEquals(2, scoring.getCurrentScore());		
	}
	
	@Test
	public void shouldRevertScoringBack() throws Exception{
		GameState game = new GameState();
		Scoring scoring = game.getScoring();
		
		Strawberry s = new Strawberry(Position.at(11,10), new J2SEPlatform());
		s.effectPeriodMs = 1;
		
		s.reflectConsumption(game);
		
		Thread.sleep(50);
		scoring.increase();
		
		Assert.assertEquals(1, scoring.getCurrentScore());		
	}
}
