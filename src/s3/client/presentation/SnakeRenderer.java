package s3.client.presentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import s3.client.domain.CellContent;
import s3.client.domain.Position;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;

public class SnakeRenderer {	
	private AbsolutePanel playground;
	private String themeName = "theme1";
	private int segmentSizePx = 20;
	Map<Position, Sprite> drawnSprites = new HashMap<Position, Sprite>();
	static class Sprite {
		Image instance;
		CellContent type;
		Sprite(Image sprite, CellContent type) {
			super();
			this.instance = sprite;
			this.type = type;
		}
		@Override
		public String toString() {
			return "Sprite [instance=" + instance + ", type=" + type + "]";
		}
	}

	
	public SnakeRenderer(AbsolutePanel playground) {
		super();
		this.playground = playground;
	}
	
	public void renderRefreshWith(Collection<Position> newPositions, CellContent type) {
		clearSegmentsNotIn(newPositions, type);		
		drawNotYetRendered(newPositions, type);
	}
	
	public Collection<? extends UIObject> getStyledChildren() {
		Collection<UIObject> children = new HashSet<UIObject>();
		for (Sprite sprite : drawnSprites.values()) {
			children.add(sprite.instance);
		}
		return children;
	}
	
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	void plainRenderSnakeCells(Collection<Position> cells, CellContent type) {
		for (Position p : cells) {		
			Image cell = newImage(type);
			
			Sprite oldCell = drawnSprites.put(p, new Sprite(cell, type));
			if (oldCell != null) {
				System.out.println("Dropping "+oldCell);
			}
			removeFromTheField(oldCell);
			
			putAt(p, cell);
		}
	}
	
	void cleanCell(Position pos) {
		System.out.println("Removing "+pos);
		Sprite sprite = drawnSprites.get(pos);
		removeFromTheField(sprite);
	}

	private void removeFromTheField(Sprite sprite) {		
		if (sprite == null || sprite.instance == null)
			return;
		sprite.instance.removeFromParent();
	}
	
	private void putAt(Position p, Image cell) {
		int left = p.getX() * segmentSizePx;
		int top = p.getY() * segmentSizePx;			
		playground.add(cell, left, top);
	}

	private Image newImage(CellContent type) {
		Image cell = new Image();
		cell.setStylePrimaryName(themeName);
		cell.addStyleDependentName(type.getStyleName());
		cell.setPixelSize(segmentSizePx, segmentSizePx);
		System.out.println("Got "+drawnSprites.size()+" sprites drawn");
		return cell;
	}
	

	private void drawNotYetRendered(Collection<Position> newPositions, CellContent type) {
		Set<Position> segmentsToDraw = new HashSet<Position>(newPositions);
		segmentsToDraw.removeAll(drawnSprites.keySet());
		plainRenderSnakeCells(segmentsToDraw, type);
	}

	private void clearSegmentsNotIn(Collection<Position> newPositions, CellContent type) {
		Set<Position> segmentsToClean = new HashSet<Position>();
		for (Map.Entry<Position, Sprite> e : drawnSprites.entrySet()) {
			if (type.equals(e.getValue().type)) {
				segmentsToClean.add(e.getKey());
			}
		}
		
		segmentsToClean.removeAll(newPositions);
		for (Position segment : segmentsToClean) {
			Sprite sprite = drawnSprites.get(segment);
			if (sprite != null && type.equals(sprite.type)) {
				if (type.equals(CellContent.APPLE)) {
					System.out.println("Dropping "+type+"@"+segment);
				}
				removeFromTheField(drawnSprites.remove(segment));
			}
		}
	}

}
