package s3.client.presentation;

import static s3.client.domain.Position.at;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import s3.client.domain.Position;
import s3.client.presentation.SnakeRenderer;
import s3.client.presentation.SnakeRenderer.Sprite;

public class SnakeRendererTest {
	@Test
	public void shouldTolerateRemovingUnexistingElements() {
		SnakeRenderer unit = new SnakeRenderer(null);
		unit.cleanCell(Position.at(-1, -999));
	}
	
	@Test
	public void shouldNotAffectExistingElementsDrawMissingNewElements() {
		SnakeRenderer unit = new SnakeRenderer(null);
		Sprite existed = new SnakeRenderer.Sprite(null, SnakeRenderer.SNAKE);
		Position pos = at(3,3);
		unit.drawnSprites.put(pos, existed);
				
		unit.renderRefreshWith(Arrays.asList(pos), SnakeRenderer.SNAKE);
		
		Assert.assertEquals(1, unit.drawnSprites.size());
		Assert.assertSame(existed, unit.drawnSprites.get(pos));
	}
	
	@Test
	public void shouldCleanElementsWithNotCurrentPositions() {
		SnakeRenderer unit = new SnakeRenderer(null);
		Sprite existed = new SnakeRenderer.Sprite(null, SnakeRenderer.SNAKE);
		Position pos = at(3,3);
		unit.drawnSprites.put(pos, existed);
				
		Set<Position> none = Collections.emptySet();
		unit.renderRefreshWith(none, SnakeRenderer.SNAKE);
		
		Assert.assertEquals(0, unit.drawnSprites.size());
	}
}
