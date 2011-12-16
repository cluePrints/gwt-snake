package s3.client;

import org.junit.Test;

public class SnakeRendererTest {
	@Test
	public void shouldTolerateRemovingUnexistingElements() {
		SnakeRenderer unit = new SnakeRenderer(null);
		unit.cleanCell(Position.at(-1, -999));
	}
}
