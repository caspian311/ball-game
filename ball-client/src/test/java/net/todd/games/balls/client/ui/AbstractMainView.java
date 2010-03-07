package net.todd.games.balls.client.ui;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

public class AbstractMainView implements IMainView {
	public void addClosingListener(IListener listener) {
	}

	public void addStartButtonListener(IListener listener) {
	}

	public void addStopButtonListener(IListener listener) {
	}

	public void setBallPositions(Ball[] ballData) {
		throw new IllegalStateException();
	}

	public void showView() {
		throw new IllegalStateException();
	}

	public void addUpKeyListener(IListener listener) {
	}

	public void addDownKeyListener(IListener listener) {
	}

	public void addLeftKeyListener(IListener listener) {
	}

	public void addRightKeyListener(IListener listener) {
	}

	public void addColorChangeListener(IListener listener) {
	}

	public String getSelectedColor() {
		throw new IllegalStateException();
	}
}
