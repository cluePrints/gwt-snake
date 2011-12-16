package s3.client;

import java.util.LinkedList;
import java.util.List;

public class Snake {
	private LinkedList<Position> segments = new LinkedList<Position>();
	
	public Snake(Position... positions) {
		for (Position p : positions) {
			append(p);
		}
	}
	
	public void moveTo(Direction direction) {
		Position head = segments.getFirst();
		Position newHead = head.getAdjacentCellToThe(direction);
		segments.addFirst(newHead);		
		segments.removeLast();
	}
	
	public List<Position> getSegments() {
		return segments;
	}
	
	public void append(Position pos) {
		if (!okToAdd(pos)) {
			throw new IllegalArgumentException("Illegal to add "+pos+" to "+segments);
		} 
		
		segments.addFirst(pos);
	}
	
	public Position getLast() {
		return segments.getLast();
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
