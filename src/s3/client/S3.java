package s3.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
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
	
	public void onModuleLoad() {
		playground = RootPanel.get("playground");
		setPlaygroundSize(playground);
		Image apple = new Image("theme1_apple.gif");
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		playground.add(apple);
		
		Timer t = new Timer() {
			
			@Override
			public void run() {
				tick();
				
			}
		};
		t.scheduleRepeating(500);
		
		
		
		
	}
	
	Direction direction = Direction.DOWN;
	
	private void tick() {
		renderSnake(s, playground);
		s.moveTo(direction);
	}
	
	Snake s = new Snake() {{
		append(new Position(5,5));
	}};
	
	private void setPlaygroundSize(RootPanel playground) {
		playground.setSize(fieldWidth*squareSize+"px", fieldHeght*squareSize+"px");
	}
	
	private void renderSnake(Snake snake, RootPanel playground) {
		for (Position p : snake.getSegments()) {
			Image snakeSegment = new Image("20x20square.png");
			snakeSegment.setPixelSize(squareSize, squareSize);
			int left = p.getX()*squareSize;
			int top = p.getY()*squareSize;
			playground.add(snakeSegment, left, top);
		}
	}
}
