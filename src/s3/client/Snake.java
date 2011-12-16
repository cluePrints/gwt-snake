package s3.client;

import java.util.LinkedList;

public class Snake {
	private LinkedList<Position> segments = new LinkedList<Position>();
	
	public void moveTo(Direction direction) {
		Position head = segments.getFirst();
		Position newHead = direction.moveFrom(head);
		segments.addFirst(newHead);		
		segments.removeLast();
	}
	
	public LinkedList<Position> getSegments() {
		return segments;
	}
	
	public void append(Position pos) {
		if (!okToAdd(pos)) {
			throw new IllegalArgumentException("");
		} 
		
		segments.addFirst(pos);
	}
	private boolean okToAdd(Position pos) {
		if (segments.isEmpty())
			return true;
		
		Position head = segments.getFirst();
		return head.adjacentWith(pos);
	}
	
	public boolean hasSegment(Position pos) {
		return segments.contains(pos);
	}
}
