package net.todd.games.balls.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BallToStringTest {
	@Test
	public void testBallToString() {
		Ball ball = new Ball();
		ball.setId("123");
		ball.setColor(BallColor.Red);
		ball.setPositionX(4);
		ball.setPositionY(5);

		assertEquals("Ball[123]: Color: Red, Position: {4.0, 5.0}", ball.toString());

		Ball ball2 = new Ball();
		ball2.setId("456");
		ball2.setColor(BallColor.Blue);
		ball2.setPositionX(8);
		ball2.setPositionY(9);

		assertEquals("Ball[456]: Color: Blue, Position: {8.0, 9.0}", ball2.toString());
	}
}
