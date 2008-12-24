package net.todd.games.balls.client.ui;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

public class AbstractMainModel implements IMainModel {
	public void addModelListener(IListener listener) {
	}

	public void doClose() {
		throw new IllegalStateException();
	}

	public void endRunning() {
		throw new IllegalStateException();
	}

	public Ball[] getBallData() {
		throw new IllegalStateException();
	}

	public String getBallPositionText() {
		throw new IllegalStateException();
	}

	public void startRunning() {
		throw new IllegalStateException();
	}

	public void moveBallUp() {
		throw new IllegalStateException();
	}

	public void moveBallDown() {
		throw new IllegalStateException();
	}

	public void moveBallLeft() {
		throw new IllegalStateException();
	}

	public void moveBallRight() {
		throw new IllegalStateException();
	}

	public void updateColor(String updatedColor) {
		// TODO Auto-generated method stub

	}
}
