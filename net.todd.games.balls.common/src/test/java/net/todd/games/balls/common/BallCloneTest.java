package net.todd.games.balls.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BallCloneTest {
	@Test
	public void testClone() {
		Ball ball1 = new Ball();
		ball1.setId("123");
		ball1.setColor(BallColor.Purple);
		ball1.setPositionX(4);
		ball1.setPositionY(5);
		ball1.setPositionZ(6);

		Ball ball2 = ball1.clone();
		assertFalse(ball1 == ball2);
		assertEquals("123", ball2.getId());
		assertEquals(255, ball2.getColor().getColor().getRed());
		assertEquals(0, ball2.getColor().getColor().getGreen());
		assertEquals(255, ball2.getColor().getColor().getBlue());
		assertEquals(4.0f, ball2.getPositionX());
		assertEquals(5.0f, ball2.getPositionY());
		assertEquals(6.0f, ball2.getPositionZ());
	}
}
