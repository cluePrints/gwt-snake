package s3.client;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Collection;

import org.junit.Test;

import s3.client.domain.CellContent;
import s3.client.domain.GameState;
import s3.client.presentation.View;

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

	public void expectRenderingCallForEachCellContentType(View view) {
		view.renderSegments(isA(Collection.class), eq(CellContent.STRAWBERRY));
		view.renderSegments(isA(Collection.class), eq(CellContent.APPLE));
		replay(view);
	}

	public View createMockView() {
		View view = createMock(View.class);
		view.setController(isA(Controller.class));
		view.updatePlaygroundSize(20, 20);
		return view;
	}
}
