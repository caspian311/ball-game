package net.todd.games.balls.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BallToStringTest {
	@Test
	public void testBallToString() {
		Ball ball = new Ball();
		ball.setColorRed(1);
		ball.setColorGreen(2);
		ball.setColorBlue(3);
		ball.setPositionX(4);
		ball.setPositionY(5);

		assertEquals("Color: {1, 2, 3}, Position: {4, 5}", ball.toString());
		
		Ball ball2 = new Ball();
		ball2.setColorRed(5);
		ball2.setColorGreen(6);
		ball2.setColorBlue(7);
		ball2.setPositionX(8);
		ball2.setPositionY(9);

		assertEquals("Color: {5, 6, 7}, Position: {8, 9}", ball2.toString());
	}
}
