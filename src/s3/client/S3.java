package s3.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class S3 implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	int squareSize = 20;
	int fieldWidth=20;
	int fieldHeght=20;
	RootPanel playground;
	SnakeRenderer renderer;
	FocusWidget focusWidget;
	
	public void onModuleLoad() {
		playground = RootPanel.get("playground");
		playground.setStylePrimaryName("theme1");
		playground.addStyleDependentName("playground");
		
		renderer = new SnakeRenderer(playground);
		setPlaygroundSize(playground);
		
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
				switch (event.getNativeKeyCode()) {
				case KeyCodes.KEY_LEFT:
					direction = Direction.LEFT;
					break;
					
				case KeyCodes.KEY_RIGHT:
					direction = Direction.RIGHT;
					break;
					
				case KeyCodes.KEY_DOWN:
					direction = Direction.DOWN;
					break;
				
				case KeyCodes.KEY_UP:
					direction = Direction.UP;
					break;
				
				default:
					break;
				}				
			}
		});
	}
	
	Direction direction = Direction.DOWN;
	
	private void tick() {
		focusWidget.setFocus(true);
		renderer.cleanCell(s.getLast());
		s.moveTo(direction);
		renderer.renderCells(s.getSegments());
	}
	
	Snake s = new Snake(Position.at(5,6)) {{
		append(new Position(5,5));
		append(new Position(5,4));
		append(new Position(5,3));
		append(new Position(5,2));
	}};
	
	private void setPlaygroundSize(RootPanel playground) {
		playground.setSize(fieldWidth*squareSize+"px", fieldHeght*squareSize+"px");
	}
}
