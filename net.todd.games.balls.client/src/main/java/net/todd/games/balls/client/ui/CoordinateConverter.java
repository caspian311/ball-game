package net.todd.games.balls.client.ui;

import java.awt.Dimension;
import java.awt.Point;

public class CoordinateConverter {

	private static final int SCREEN_SIZE = 20;

	public Point toXY(float positionX, float positionY, float positionZ) {
		int x = (int) positionX;
		int z = (int) positionZ;

		x += SCREEN_SIZE / 2;
		z -= SCREEN_SIZE / 2;

		return new Point(x, -z);
	}

	public Point scale(Point point, Dimension size) {
		int x = point.x;
		int y = point.y;

		x = x * (size.width / SCREEN_SIZE);
		y = y * (size.height / SCREEN_SIZE);

		return new Point(x, y);
	}
}
