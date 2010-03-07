package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;

public class CoordinateConverterScaleTest {
	@Test
	public void testScaleDoesNotReturnNull() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(0, 0), new Dimension(100, 100));
		assertNotNull(point);
	}

	@Test
	public void testScaleGivenOriginAndNoSize() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(0, 0), new Dimension(0, 0));
		assertEquals(0, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testScaleGivenOriginAnd100x100() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(0, 0), new Dimension(100, 100));
		assertEquals(0, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testScaleGivenTopRightPointAndNoSize() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(20, 0), new Dimension(0, 0));
		assertEquals(0, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testScaleGivenTopRightPointAnd100x100() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(20, 0), new Dimension(100, 100));
		assertEquals(100, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testScaleGivenBottomLeftPointAndNoSize() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(0, 20), new Dimension(0, 0));
		assertEquals(0, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testScaleGivenBottomLeftPointAnd100x100() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(0, 20), new Dimension(100, 100));
		assertEquals(0, point.x);
		assertEquals(100, point.y);
	}

	@Test
	public void testScaleGivenBottomRightPointAndNoSize() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(20, 20), new Dimension(0, 0));
		assertEquals(0, point.x);
		assertEquals(0, point.y);
	}

	@Test
	public void testScaleGivenBottomRightPointAnd100x100() {
		CoordinateConverter converter = new CoordinateConverter();
		Point point = converter.scale(new Point(20, 20), new Dimension(100, 100));
		assertEquals(100, point.x);
		assertEquals(100, point.y);
	}
}
