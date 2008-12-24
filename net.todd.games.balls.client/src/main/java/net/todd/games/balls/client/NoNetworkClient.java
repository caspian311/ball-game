package net.todd.games.balls.client;

import net.todd.common.uitools.IListener;
import net.todd.common.uitools.ListenerManager;
import net.todd.games.balls.common.Ball;

public class NoNetworkClient implements INetworkClient {
	private final Ball[] balls;
	private final ListenerManager listenerManager;

	public NoNetworkClient() {
		balls = new Ball[1];
		listenerManager = new ListenerManager();
	}
	
	public void addListener(IListener listener) {
		listenerManager.addListener(listener);
	}

	public void closeConnection() {
		// nothing done
	}

	public Ball[] getBalls() {
		return balls;
	}

	public boolean isConnected() {
		return true;
	}

	public void update(Ball ball) {
		balls[0] = ball;
		listenerManager.notifyListeners();
	}

}
