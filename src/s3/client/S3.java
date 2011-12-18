package s3.client;

import static s3.client.domain.GameStatus.IN_PROGRESS;
import static s3.client.domain.GameStatus.OVER;
import static s3.client.domain.GameStatus.PAUSE;
import s3.client.domain.CellContent;
import s3.client.domain.Direction;
import s3.client.domain.GameState;
import s3.client.domain.rules.Rule;
import s3.client.domain.rules.Rules;
import s3.client.presentation.KeyToDirectionStrategy;
import s3.client.presentation.MainView;
import s3.client.scoring.Scoring;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyCodes;
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
	Rule rules = Rules.standard();
	Direction newDirection = game.getSnakeDirection();
	Timer t;
	
	public void onModuleLoad() {
		RootPanel.get().add(view);
		
		AbsolutePanel playground = view.getPlayground();
		playground.addStyleDependentName("playground");
		
		focusWidget = view.getFocusWidget();
		
		t = new Timer() {			
			@Override
			public void run() {
				tick();				
			}
		};
		t.run();
		
		Timer focusTimer = new Timer() {
			public void run() {
				focusWidget.setFocus(true);
			};
		};
		focusTimer.scheduleRepeating(500);
		
		focusWidget.addKeyDownHandler(new KeyDownHandler() {			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				Direction oldDirection = game.getSnakeDirection();
				newDirection = new KeyToDirectionStrategy().decide(oldDirection, event.getNativeKeyCode());				
				
				if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
					if (game.getStatus() == IN_PROGRESS) {
						t.cancel();
						game.setStatus(PAUSE);
					} else if (game.getStatus() == PAUSE) {
						game.setStatus(IN_PROGRESS);
						tick();
					}
				}
			}
		});
	}
	
	private void tick() {
		System.out.println("Tick start");
		game.setSnakeDirection(newDirection);
		
		rules.evaluate(game);
		if (game.getStatus() == OVER) {
			game.reset();
			view.getPlayground().clear();
		}
		
		controller.renderArtifacts();		
		view.renderSegments(game.getSnakeSegments(), CellContent.SNAKE);
		
		Scoring scoring = game.getScoring();
		view.reflectCurrentScore(scoring.getCurrentScore());
		view.reflectMaxScore(scoring.getBestScore());		
		
		t.schedule(game.getSpeed().getTimeQuant());
		
	}

}
