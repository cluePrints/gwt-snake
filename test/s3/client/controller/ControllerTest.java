package s3.client.controller;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import s3.client.domain.CellContent;
import s3.client.domain.Direction;
import s3.client.domain.GameState;
import s3.client.presentation.View;

import com.google.gwt.event.dom.client.KeyCodes;

public class ControllerTest {	
	
	@Test
	public void shouldTriggerUpdatesForAllArtifactsTypesToCauseProperCleaning() {
		GameState game = new GameState();
		View view = createMockView();		
		expectRenderingCallForEachCellContentType(view);		
		Controller controller = new Controller(game, view);
		
		controller.renderArtifacts();
		
		verify(view);
	}

	@Test
	public void shouldUseLatestFromTickKeyEventsToDetermineNextDirection() {
		GameState game = new GameState();
		game.setSnakeDirection(Direction.UP);
		Controller controller = new Controller(game, createMockView());
		controller.onKeyDown(KeyCodes.KEY_LEFT);
		controller.onKeyDown(KeyCodes.KEY_RIGHT);
		controller.onGameClockTick();
		
		Assert.assertEquals(Direction.RIGHT, game.getSnakeDirection());
	}
	
	
	@Test
	public void shouldUseSameDirectionForTheNextTickSettingAsTheGame() {
		GameState game = new GameState();
		game.setSnakeDirection(Direction.DOWN);
		
		Controller controller = new Controller(game, createMockView());
		controller.onGameClockTick();
		
		Assert.assertEquals(Direction.DOWN, controller.newDirection);
	}
	
	@SuppressWarnings("unchecked")
	private void expectRenderingCallForEachCellContentType(View view) {
		view.renderSegments(isA(Collection.class), eq(CellContent.STRAWBERRY));
		view.renderSegments(isA(Collection.class), eq(CellContent.APPLE));
		replay(view);
	}

	private View createMockView() {
		View view = createMock(View.class);
		return view;
	}
}
