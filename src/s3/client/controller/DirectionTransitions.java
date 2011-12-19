package s3.client.controller;

import static s3.client.domain.Direction.DOWN;
import static s3.client.domain.Direction.LEFT;
import static s3.client.domain.Direction.RIGHT;
import static s3.client.domain.Direction.UP;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import s3.client.domain.Direction;


public class DirectionTransitions {
	private static Map<Direction, Set<Direction>> allowedDirectionTransitions = new HashMap<Direction, Set<Direction>>();
	{
		allowedDirectionTransitions.put(LEFT, 	EnumSet.of(UP, DOWN));
		allowedDirectionTransitions.put(RIGHT, 	EnumSet.of(UP, DOWN));
		allowedDirectionTransitions.put(UP, 	EnumSet.of(LEFT, RIGHT));
		allowedDirectionTransitions.put(DOWN, 	EnumSet.of(LEFT, RIGHT));
	}	
	
	private Direction from;

	public static DirectionTransitions from(Direction current) {
		return new DirectionTransitions(current);
	}
	
	public Direction to(Direction desired) {
		Collection<Direction> allowed = allowedDirectionTransitions.get(from);
		if (!allowed.contains(desired))
			return from;
		
		return desired;
	}
	
	private DirectionTransitions(Direction from) {
		super();
		this.from = from;
	}
}
