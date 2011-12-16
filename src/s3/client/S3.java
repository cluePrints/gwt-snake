package s3.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.RootPanel;

public class S3 implements EntryPoint {
	int squareSize = 20;
	RootPanel playground;
	SnakeRenderer renderer;
	FocusWidget focusWidget;
	
	GameState game = new GameState();
	
	public void onModuleLoad() {
		playground = RootPanel.get("playground");
		playground.setStylePrimaryName("theme1");
		playground.addStyleDependentName("playground");
		
		renderer = new SnakeRenderer(playground);
		updatePlaygroundSize();
		
		focusWidget = new Button();
		playground.add(focusWidget);
		
		final Timer t = new Timer() {			
			@Override
			public void run() {
				tick();				
			}
		};
		t.scheduleRepeating(500);
		
		focusWidget.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				playground.setStylePrimaryName("theme2");
			}
		});
			
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
		focusWidget.setFocus(true);
		renderer.cleanCell(game.lastSnakeCell());
		game.tick();
		renderer.renderCells(game.getSnakeSegments());
	}
	
	private void updatePlaygroundSize() {
		int width = game.getHorizontalCellsCount()*squareSize;
		int height = game.getVerticalCellsCount()*squareSize;
		playground.setPixelSize(width, height);
	}
}
