package net.todd.games.balls.common;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import javax.vecmath.Color3f;

import org.junit.Test;

public class BallColorTest {
	@Test
	public void testTetColor3f() {
		Color3f color3f = BallColor.Red.getColor3f();
		float[] colorComponents = new float[3];
		color3f.get(colorComponents);
		assertEquals(colorComponents[0] * 256, Color.red.getRed(), 1);
		assertEquals(colorComponents[1] * 256, Color.red.getGreen(), 1);
		assertEquals(colorComponents[2] * 256, Color.red.getBlue(), 1);
	}
}
