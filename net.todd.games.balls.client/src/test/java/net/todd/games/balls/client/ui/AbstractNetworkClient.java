package net.todd.games.balls.client.ui;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.client.INetworkClient;
import net.todd.games.balls.common.Ball;

public class AbstractNetworkClient implements INetworkClient {
	public Ball[] getBalls() {
		throw new IllegalStateException();
	}

	public boolean isConnected() {
		throw new IllegalStateException();
	}

	public void update(Ball ball) {
		throw new IllegalStateException();
	}

	public void closeConnection() {
		throw new IllegalStateException();
	}

	public void addListener(IListener listener) {
	}
}
