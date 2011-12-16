package s3.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class SnakeRenderer {
	private RootPanel playground;
	private String themeName = "theme1";
	private String snakeSegmentImageFile = "20x20square.png";
	private int segmentSizePx = 20;
	private Map<Position, Image> sprites = new HashMap<Position, Image>();
	
	public SnakeRenderer(RootPanel playground) {
		super();
		this.playground = playground;
	}

	public void renderCells(Collection<Position> cells) {
		for (Position p : cells) {		
			Image cell = new Image();
			cell.setStylePrimaryName(themeName);
			cell.addStyleDependentName("snakeSegment");
			cell.setPixelSize(segmentSizePx, segmentSizePx);

			Image oldCell = sprites.put(p, cell);
			removeFromParent(oldCell);
			
			int left = p.getX()*segmentSizePx;
			int top = p.getY()*segmentSizePx;			
			playground.add(cell, left, top);
		}
	}
	
	public void cleanCell(Position pos) {
		Image sprite = sprites.get(pos);
		removeFromParent(sprite);
	}

	private void removeFromParent(Image sprite) {
		if (sprite == null)
			return;
		sprite.removeFromParent();
	}
}
