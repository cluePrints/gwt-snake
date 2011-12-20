package s3.client.presentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import s3.client.domain.CellContent;
import s3.client.domain.Position;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public class SpriteRenderer {	
	private AbsolutePanel playground;
	private String themeName = "theme1";
	private int segmentSizePx = 20;
	Map<Position, Sprite> drawnSprites = new HashMap<Position, Sprite>();
	
	public SpriteRenderer(AbsolutePanel playground) {
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
			Widget cell = newImage(type);
			
			Sprite oldCell = drawnSprites.put(p, new Sprite(cell, type));
			removeFromTheField(oldCell);
			
			putAt(p, cell);
		}
	}
	
	void cleanCell(Position pos) {
		Sprite sprite = drawnSprites.get(pos);
		removeFromTheField(sprite);
	}

	private void removeFromTheField(Sprite sprite) {		
		if (sprite == null || sprite.instance == null)
			return;
		sprite.instance.removeFromParent();
	}
	
	private void putAt(Position p, Widget cell) {
		int left = p.getX() * segmentSizePx;
		int top = p.getY() * segmentSizePx;			
		playground.add(cell, left, top);
	}

	/**
	 * Images with bg-image won't work in IE as in other browsers(it will show it as a broken image)
	 * so using something other:) 
	 */
	private Label newImage(CellContent type) {
		Label cell = new Label();
		cell.setStylePrimaryName(themeName);
		cell.addStyleDependentName(StyleNames.forCellContent(type));
		cell.setPixelSize(segmentSizePx, segmentSizePx);
		return cell;
	}
	

	private void drawNotYetRendered(Collection<Position> newPositions, CellContent type) {
		Set<Position> segmentsToDraw = new HashSet<Position>(newPositions);
		segmentsToDraw.removeAll(drawnSprites.keySet());
		plainRenderSnakeCells(segmentsToDraw, type);
	}

	private void clearSegmentsNotIn(Collection<Position> newPositions, CellContent type) {
		Set<Position> segmentsToClean = byType(type);
		
		segmentsToClean.removeAll(newPositions);
		
		cleanPositionsWithCellsOfType(segmentsToClean, type);
	}

	private void cleanPositionsWithCellsOfType(Set<Position> segmentsToClean,
			CellContent type) {
		for (Position segment : segmentsToClean) {
			Sprite sprite = drawnSprites.get(segment);
			if (sprite != null && type.equals(sprite.type)) {
				removeFromTheField(drawnSprites.remove(segment));
			}
		}
	}

	private Set<Position> byType(CellContent type) {
		Set<Position> segmentsToClean = new HashSet<Position>();
		for (Map.Entry<Position, Sprite> e : drawnSprites.entrySet()) {
			if (type.equals(e.getValue().type)) {
				segmentsToClean.add(e.getKey());
			}
		}
		return segmentsToClean;
	}
	
	static class Sprite {
		Widget instance;
		CellContent type;
		Sprite(Widget sprite, CellContent type) {
			super();
			this.instance = sprite;
			this.type = type;
		}
		@Override
		public String toString() {
			return "Sprite [instance=" + instance + ", type=" + type + "]";
		}
	}
}
