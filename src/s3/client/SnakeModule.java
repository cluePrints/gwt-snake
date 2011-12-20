package s3.client;

import s3.client.controller.Clock;
import s3.client.controller.Controller;
import s3.client.domain.GameState;
import s3.client.platform.GWTPlatform;
import s3.client.presentation.GWTView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.RootPanel;

public class SnakeModule implements EntryPoint {
	GWTView view = new GWTView();
	GameState game = new GameState();
	
	Controller controller = new Controller(game, view);
	Clock clock = new Clock(controller, game, new GWTPlatform());
	
	public void onModuleLoad() {
		RootPanel root = RootPanel.get();
		root.add(view);
		root.addDomHandler(view, KeyDownEvent.getType());
		controller.initAndStartWith(clock);
		view.initAndRender();
	}
}
