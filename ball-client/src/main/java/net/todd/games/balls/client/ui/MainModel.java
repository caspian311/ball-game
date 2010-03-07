package net.todd.games.balls.client.ui;

import java.util.UUID;

import net.todd.common.uitools.IListener;
import net.todd.common.uitools.ListenerManager;
import net.todd.games.balls.client.INetworkClient;
import net.todd.games.balls.common.Ball;
import net.todd.games.balls.common.BallColor;

public class MainModel implements IMainModel {
	private final ISystemKiller systemKiller;
	private final ListenerManager listenerManager;
	private Ball[] balls;
	private Ball myBall;
	private final INetworkClient networkClient;

	public MainModel(ISystemKiller systemKiller, final INetworkClient networkClient) {
		this.systemKiller = systemKiller;
		this.networkClient = networkClient;

		if (networkClient != null) {
			createDefaultBall();
			networkClient.update(myBall);
			networkClient.addListener(new IListener() {
				public void fireEvent() {
					balls = networkClient.getBalls();
					listenerManager.notifyListeners();
				}
			});
		}

		listenerManager = new ListenerManager();
	}

	private void createDefaultBall() {
		myBall = new Ball();
		myBall.setId(UUID.randomUUID().toString());
		myBall.setColor(BallColor.White);
	}

	public void doClose() {
		if (systemKiller != null) {
			systemKiller.killSystem();
		}

		if (networkClient != null) {
			networkClient.closeConnection();
		}
	}

	public void addModelListener(IListener listener) {
		listenerManager.addListener(listener);
	}

	public Ball[] getBallData() {
		return balls;
	}

	public void moveBallUp() {
		myBall.setPositionZ(myBall.getPositionZ() + 1);
		networkClient.update(myBall);
	}

	public void moveBallDown() {
		myBall.setPositionZ(myBall.getPositionZ() - 1);
		networkClient.update(myBall);
	}

	public void moveBallLeft() {
		myBall.setPositionX(myBall.getPositionX() + 1);
		networkClient.update(myBall);
	}

	public void moveBallRight() {
		myBall.setPositionX(myBall.getPositionX() - 1);
		networkClient.update(myBall);
	}

	public void updateColor(String updatedColor) {
		myBall.setColor(BallColor.byName(updatedColor));
		networkClient.update(myBall);
	}
}
