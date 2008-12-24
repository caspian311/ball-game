package net.todd.games.balls.client.ui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import net.todd.games.balls.common.Ball;

public class BouncyBallComponent extends Component {
	private static final long serialVersionUID = 3146348058907303750L;
	private static final int BALL_SIZE = 20;
	private Ball[] ballData;

	@Override
	public void paint(Graphics g) {
		if (ballData != null) {
			for (Ball ball : ballData) {
				g.setColor(new Color(ball.getColorRed(), ball.getColorGreen(),
						ball.getColorBlue()));
				g.fillOval(ball.getPositionX(), ball.getPositionY(), BALL_SIZE,
						BALL_SIZE);
			}
		}
	}

	public void setBallPositions(Ball[] ballData) {
		this.ballData = ballData;
	}
}
