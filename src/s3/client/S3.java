package s3.client;

import s3.client.controller.Clock;
import s3.client.controller.Controller;
import s3.client.domain.GameState;
import s3.client.platform.GWTPlatform;
import s3.client.presentation.MainView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.RootPanel;

public class S3 implements EntryPoint {
	FocusWidget focusWidget;
	
	MainView view = new MainView();
	GameState game = new GameState();
	
	Controller controller = new Controller(game, view);
	Clock clock = new Clock(controller, game, new GWTPlatform());
	
	public void onModuleLoad() {
		RootPanel.get().add(view);
		controller.init();
		
		focusWidget = view.getFocusWidget();
		Timer focusTimer = new Timer() {
			public void run() {
				focusWidget.setFocus(true);
			};
		};
		focusTimer.scheduleRepeating(500);
		clock.resume();
	}
}
