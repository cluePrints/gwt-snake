package s3.client.presentation;

import static s3.client.domain.Position.at;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import s3.client.domain.Position;
import static s3.client.domain.CellContent.*;
import s3.client.presentation.SpriteRenderer;
import s3.client.presentation.SpriteRenderer.Sprite;

public class SnakeRendererTest {
	@Test
	public void shouldTolerateRemovingUnexistingElements() {
		SpriteRenderer unit = new SpriteRenderer(null);
		unit.cleanCell(Position.at(-1, -999));
	}
	
	@Test
	public void shouldNotAffectExistingElementsDrawMissingNewElements() {
		SpriteRenderer unit = new SpriteRenderer(null);
		Sprite existed = new SpriteRenderer.Sprite(null, SNAKE);
		Position pos = at(3,3);
		unit.drawnSprites.put(pos, existed);
				
		unit.renderRefreshWith(Arrays.asList(pos), SNAKE);
		
		Assert.assertEquals(1, unit.drawnSprites.size());
		Assert.assertSame(existed, unit.drawnSprites.get(pos));
	}
	
	@Test
	public void shouldCleanElementsWithNotCurrentPositions() {
		SpriteRenderer unit = new SpriteRenderer(null);
		Sprite existed = new SpriteRenderer.Sprite(null, SNAKE);
		Position pos = at(3,3);
		unit.drawnSprites.put(pos, existed);
				
		Set<Position> none = Collections.emptySet();
		unit.renderRefreshWith(none, SNAKE);
		
		Assert.assertEquals(0, unit.drawnSprites.size());
	}
}
