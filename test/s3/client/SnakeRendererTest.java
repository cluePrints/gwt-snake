package s3.client;

import static s3.client.Position.at;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.gwt.user.client.ui.Image;

public class SnakeRendererTest {
	@Test
	public void shouldTolerateRemovingUnexistingElements() {
		SnakeRenderer unit = new SnakeRenderer(null);
		unit.cleanCell(Position.at(-1, -999));
	}
	
	@Test
	public void shouldNotAffectExistingElementsDrawMissingNewElements() {
		SnakeRenderer unit = new SnakeRenderer(null);
		Image existed = null;
		Position pos = at(3,3);
		unit.snakeSegmentSprites.put(pos, existed);
				
		unit.renderRefreshWith(Arrays.asList(pos));
		
		Assert.assertEquals(1, unit.snakeSegmentSprites.size());
		Assert.assertSame(existed, unit.snakeSegmentSprites.get(pos));
	}
	
	@Test
	public void shouldCleanElementsWithNotCurrentPositions() {
		SnakeRenderer unit = new SnakeRenderer(null);
		Image existed = null;
		Position pos = at(3,3);
		unit.snakeSegmentSprites.put(pos, existed);
				
		Set<Position> none = Collections.emptySet();
		unit.renderRefreshWith(none);
		
		Assert.assertEquals(0, unit.snakeSegmentSprites.size());
	}
}
