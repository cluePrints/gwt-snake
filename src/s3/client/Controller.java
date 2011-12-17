package s3.client;

import s3.client.domain.Direction;
import s3.client.domain.GameState;
import s3.client.presentation.MainView;

public class Controller {
	GameState state;
	MainView view;
	
	public Controller(GameState state, MainView view) {
		super();
		this.state = state;
		this.view = view;
		view.setController(this);
	}
	
	public void pushFieldBoundary(Direction direction) {
		int width = state.getHorizontalCellsCount();
		int height = state.getVerticalCellsCount();
		int desiredWidth = width + direction.getDeltaX();
		int desiredHeight = height + direction.getDeltaY();
		
		state.tryResize(desiredWidth, desiredHeight);
		
		syncSizeWithView();
	}
	private void syncSizeWithView() {
		view.updatePlaygroundSize(state.getHorizontalCellsCount(), state.getVerticalCellsCount());
	}
}
