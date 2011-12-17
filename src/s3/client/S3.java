package s3.client;

import s3.client.domain.Direction;
import s3.client.domain.GameState;
import s3.client.domain.Rules;
import s3.client.presentation.KeyToDirectionStrategy;
import s3.client.presentation.MainView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.RootPanel;

public class S3 implements EntryPoint {
	FocusWidget focusWidget;
	
	MainView view = new MainView();
	GameState game = new GameState();
	Controller controller = new Controller(game, view);
	Rules rules = new Rules();
	Timer t;
	
	public void onModuleLoad() {
		RootPanel.get().add(view);
		
		AbsolutePanel playground = view.getPlayground();
		//playground.setStylePrimaryName("theme1");
		playground.addStyleDependentName("playground");
		
		view.updatePlaygroundSize(game.getHorizontalCellsCount(), game.getVerticalCellsCount());
		
		focusWidget = view.getFocusWidget();
		
		t = new Timer() {			
			@Override
			public void run() {
				focusWidget.setFocus(true);
				tick();				
			}
		};
		t.run();
			
		focusWidget.addKeyDownHandler(new KeyDownHandler() {			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				Direction oldDirection = game.getSnakeDirection();
				Direction newDirection = new KeyToDirectionStrategy().decide(oldDirection, event.getNativeKeyCode());
				game.setSnakeDirection(newDirection);
			}
		});
	}
	
	private void tick() {
		game.moveSnake();
		if (rules.gameOver(game)) {
			game.reset();
		}
		view.renderSnakeSegments(game.getSnakeSegments());
		t.schedule(game.getSpeed().getTimeQuant());
	}
}
