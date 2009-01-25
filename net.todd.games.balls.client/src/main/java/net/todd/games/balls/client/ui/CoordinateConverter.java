package net.todd.games.balls.client.ui;

import java.awt.Point;

public class CoordinateConverter {

	public Point toXY(float positionX, float positionY, float positionZ) {
		int x = (int) positionX;
		int z = (int) positionZ;

		x += 10;
		z -= 10;

		return new Point(x, -z);
	}
}
