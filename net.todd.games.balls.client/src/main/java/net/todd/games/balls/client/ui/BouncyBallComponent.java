package net.todd.games.balls.client.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import net.todd.games.balls.common.Ball;

public class BouncyBallComponent extends Component {
	private static final long serialVersionUID = 3146348058907303750L;
	private static final int BALL_SIZE = 20;
	private Ball[] ballData;
	private final CoordinateConverter coordinateConverter;

	public BouncyBallComponent() {
		coordinateConverter = new CoordinateConverter();
	}

	@Override
	public void paint(Graphics g) {
		if (ballData != null) {
			for (Ball ball : ballData) {
				g.setColor(new Color(ball.getColorRed(), ball.getColorGreen(), ball
				        .getColorBlue()));
				Point point = coordinateConverter.toXY(ball.getPositionX(), ball
				        .getPositionY(), ball.getPositionZ());

				g.fillOval(point.x, point.y, BALL_SIZE, BALL_SIZE);
			}
		}
	}

	public void setBallPositions(Ball[] ballData) {
		this.ballData = ballData;
	}
}
