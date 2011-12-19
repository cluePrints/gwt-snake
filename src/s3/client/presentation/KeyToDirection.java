package s3.client.presentation;

import static com.google.gwt.event.dom.client.KeyCodes.KEY_DOWN;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_LEFT;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_RIGHT;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_UP;
import static s3.client.domain.Direction.DOWN;
import static s3.client.domain.Direction.LEFT;
import static s3.client.domain.Direction.RIGHT;
import static s3.client.domain.Direction.UP;

import java.util.HashMap;
import java.util.Map;

import s3.client.domain.Direction;


public class KeyToDirection {
	private Map<Integer, Direction> keyToDirection = new HashMap<Integer, Direction>();
	{
		keyToDirection.put(KEY_DOWN, 	DOWN);
		keyToDirection.put(KEY_UP, 		UP);
		keyToDirection.put(KEY_LEFT, 	LEFT);
		keyToDirection.put(KEY_RIGHT, 	RIGHT);		
	};
	
	public Direction map(int keyCode) {
		return keyToDirection.get(keyCode);
	}
}
