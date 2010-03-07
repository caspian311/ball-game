package net.todd.games.balls.common;

import java.io.Serializable;

public class ServerResponse implements Serializable {
	private static final long serialVersionUID = -4873823792030776917L;

	private Ball[] balls;

	public Ball[] getBalls() {
		return balls;
	}

	public void setBalls(Ball[] balls) {
		this.balls = balls;
	}
}
