package s3.client;

import junit.framework.Assert;
import static s3.client.domain.Direction.*;

import org.junit.Test;

import s3.client.domain.Position;

public class PositionTest {
	@Test
	public void shouldNotBeAdjacentByDiagonal() {
		Position a = Position.at(3,3);
		Position b = Position.at(4,4);
		Assert.assertFalse(a.adjacentWith(b));
		Assert.assertFalse(b.adjacentWith(a));
	}
	
	@Test
	public void shouldBeAdjacentByHorizontal() {
		Position a = Position.at(3,3);
		Position b = Position.at(4,3);
		Assert.assertTrue(a.adjacentWith(b));
		Assert.assertTrue(b.adjacentWith(a));
	}
	
	@Test
	public void shouldBeAdjacentByVertical() {
		Position a = Position.at(3,4);
		Position b = Position.at(3,3);
		Assert.assertTrue(a.adjacentWith(b));
		Assert.assertTrue(b.adjacentWith(a));
	}
	
	@Test
	public void shouldNotBeAdjacentIfFarAway() {
		Position a = Position.at(3,4);
		Position b = Position.at(3,2);
		Assert.assertFalse(a.adjacentWith(b));
		Assert.assertFalse(b.adjacentWith(a));
	}
	
	@Test
	public void shouldNotBeAdjacentToItSelf() {
		Position a = Position.at(3,3);
		Position b = Position.at(3,3);
		Assert.assertFalse(a.adjacentWith(b));
		Assert.assertFalse(b.adjacentWith(a));
	}
	
	@Test
	public void shouldReturnAdjacentCell() {
		Position initial = Position.at(3,3);
		Position expectedRightAdjacent = Position.at(4,3);
		Position actual = initial.getAdjacentCellToThe(RIGHT);
		
		Assert.assertEquals(expectedRightAdjacent, actual);
	}
}
