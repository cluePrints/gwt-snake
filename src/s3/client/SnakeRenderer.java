package s3.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;

public class SnakeRenderer {
	private AbsolutePanel playground;
	private String themeName = "theme1";
	private int segmentSizePx = 20;
	Map<Position, Image> snakeSegmentSprites = new HashMap<Position, Image>();
	
	public SnakeRenderer(AbsolutePanel playground) {
		super();
		this.playground = playground;
	}
	
	public void renderRefreshWith(Collection<Position> newPositions) {
		clearSegmentsNotIn(newPositions);		
		drawNotYetRendered(newPositions);
	}
	
	public Collection<? extends UIObject> getStyledChildren() {
		return snakeSegmentSprites.values();
	}
	
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	void plainRenderSnakeCells(Collection<Position> cells) {
		for (Position p : cells) {		
			Image cell = newImage();
			
			Image oldCell = snakeSegmentSprites.put(p, cell);
			removeFromTheField(oldCell);
			
			putAt(p, cell);
		}
	}
	
	void cleanCell(Position pos) {
		Image sprite = snakeSegmentSprites.get(pos);
		removeFromTheField(sprite);
	}

	private void removeFromTheField(Image sprite) {		
		if (sprite == null)
			return;
		sprite.removeFromParent();
	}
	
	private void putAt(Position p, Image cell) {
		int left = p.getX() * segmentSizePx;
		int top = p.getY() * segmentSizePx;			
		playground.add(cell, left, top);
	}

	private Image newImage() {
		Image cell = new Image();
		cell.setStylePrimaryName(themeName);
		cell.addStyleDependentName("snakeSegment");
		cell.setPixelSize(segmentSizePx, segmentSizePx);
		return cell;
	}
	

	private void drawNotYetRendered(Collection<Position> newPositions) {
		Set<Position> segmentsToDraw = new HashSet<Position>(newPositions);
		segmentsToDraw.removeAll(snakeSegmentSprites.keySet());
		plainRenderSnakeCells(segmentsToDraw);
	}

	private void clearSegmentsNotIn(Collection<Position> newPositions) {
		Set<Position> segmentsToClean = new HashSet<Position>(snakeSegmentSprites.keySet());
		segmentsToClean.removeAll(newPositions);
		for (Position segment : segmentsToClean) {
			removeFromTheField(snakeSegmentSprites.remove(segment));
		}
	}

}
