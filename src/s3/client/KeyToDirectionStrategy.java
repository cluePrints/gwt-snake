package s3.client;

import static com.google.gwt.event.dom.client.KeyCodes.KEY_DOWN;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_LEFT;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_RIGHT;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_UP;
import static s3.client.Direction.DOWN;
import static s3.client.Direction.LEFT;
import static s3.client.Direction.RIGHT;
import static s3.client.Direction.UP;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class KeyToDirectionStrategy {
	private Map<Integer, Direction> keyToDirection = new HashMap<Integer, Direction>();
	{
		keyToDirection.put(KEY_DOWN, 	DOWN);
		keyToDirection.put(KEY_UP, 		UP);
		keyToDirection.put(KEY_LEFT, 	LEFT);
		keyToDirection.put(KEY_RIGHT, 	RIGHT);		
	};
	
	private Map<Direction, Set<Direction>> allowedDirectionTransitions = new HashMap<Direction, Set<Direction>>();
	{
		allowedDirectionTransitions.put(LEFT, 	EnumSet.of(UP, DOWN));
		allowedDirectionTransitions.put(RIGHT, 	EnumSet.of(UP, DOWN));
		allowedDirectionTransitions.put(UP, 	EnumSet.of(LEFT, RIGHT));
		allowedDirectionTransitions.put(DOWN, 	EnumSet.of(LEFT, RIGHT));
	}
	
	public Direction decide(Direction current, int keyCode) {
		Direction desired = keyToDirection.get(keyCode);
		boolean noActionsForKey = (desired == null);
		if (noActionsForKey)
			return current;
		
		Collection<Direction> allowed = allowedDirectionTransitions.get(current);
		if (!allowed.contains(desired))
			return current;
		
		return desired;
	}
}
