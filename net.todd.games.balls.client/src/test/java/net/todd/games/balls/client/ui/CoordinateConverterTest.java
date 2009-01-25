package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Point;

import org.junit.Test;

public class CoordinateConverterTest {
	@Test
	public void testToXYDoesNotReturnNull() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.toXY(0.0f, 0.0f, 0.0f);
		assertNotNull(point);
	}

	@Test
	public void testToXYGivenOrigin() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.toXY(0.0f, 0.0f, 0.0f);
		assertEquals(10, point.x);
		assertEquals(10, point.y);
	}

	@Test
	public void testToXYTopLeft() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.toXY(-10.0f, 0.0f, 10.0f);
		assertEquals(0, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testToXYTopRight() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.toXY(10.0f, 0.0f, 10.0f);
		assertEquals(20, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testToXYBottomLeft() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.toXY(-10.0f, 0.0f, -10.0f);
		assertEquals(0, point.x);
		assertEquals(20, point.y);
	}

	@Test
	public void testToXYBottomRight() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.toXY(10.0f, 0.0f, -10.0f);
		assertEquals(20, point.x);
		assertEquals(20, point.y);
	}
}
