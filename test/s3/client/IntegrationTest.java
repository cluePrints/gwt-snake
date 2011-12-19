package s3.client;

import static s3.client.domain.Position.at;
import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

import s3.client.artifact.Apple;
import s3.client.artifact.Strawberry;
import s3.client.controller.Controller;
import s3.client.domain.Direction;
import s3.client.domain.GameState;
import s3.client.domain.GameStatus;
import s3.client.domain.Position;
import s3.client.domain.Snake;
import s3.client.platform.J2SEPlatform;
import s3.client.platform.Platform;
import s3.client.presentation.View;

import com.google.gwt.event.dom.client.KeyCodes;

public class IntegrationTest {
	Position snakePos = at(2,2);
	Position applePos = at(2,3);
	Position strawberryPos = at(3,3);
	Direction snakeDir = Direction.RIGHT;
	Platform platform = new J2SEPlatform();

	GameState game;
	Controller controller;
	
	@Test
	public void test() {		
		controller = wireAndInitController();		
		checkMovingToEmptyCell();		
		checkStrawberryConsumption();		
		checkConsumingAppleAfterStrawberry();		
		checkHittingAWall();
	}

	private void checkHittingAWall() {
		controller.onGameClockTick();
		Assert.assertEquals(at(1, 3), game.getSnakeHead());
		controller.onGameClockTick();
		Assert.assertEquals(at(0, 3), game.getSnakeHead());		
		controller.onGameClockTick();
		Assert.assertEquals(0, game.getScoring().getCurrentScore());
		Assert.assertEquals(2, game.getScoring().getBestScore());
	}

	private void checkConsumingAppleAfterStrawberry() {
		controller.onKeyDown(KeyCodes.KEY_LEFT);
		controller.onGameClockTick();		
		Assert.assertEquals(at(2, 3), game.getSnakeHead());
		Assert.assertEquals(game.getScoring().getCurrentScore(), 2);
	}

	private void checkStrawberryConsumption() {
		controller.onKeyDown(KeyCodes.KEY_UP);
		controller.onKeyDown(KeyCodes.KEY_DOWN);
		controller.onGameClockTick();		
		Assert.assertEquals(at(3, 3), game.getSnakeHead());
		Assert.assertEquals(game.getScoring().getCurrentScore(), 0);
	}

	private void checkMovingToEmptyCell() {
		controller.onGameClockTick();
		Assert.assertEquals(at(3, 2), game.getSnakeHead());
		Assert.assertEquals(game.getScoring().getCurrentScore(), 0);
	}

	private Controller wireAndInitController() {
		View view = EasyMock.createMock(View.class);
		game = new GameState();
		game.setSnakeDirection(snakeDir);
		game.setSnake(new Snake(snakePos));
		game.getArtifacts().tryPutAt(applePos, new Apple(applePos, game.getScoring()));		
		game.getArtifacts().tryPutAt(strawberryPos, new Strawberry(strawberryPos, platform));
		
		Controller controller = new Controller(game, view);

		controller.init();
		return controller;
	}
}
