package net.todd.games.balls.client.ui;

import java.awt.Color;

import net.todd.common.uitools.IListener;
import net.todd.common.uitools.ListenerManager;
import net.todd.games.balls.client.INetworkClient;
import net.todd.games.balls.common.Ball;

public class MainModel implements IMainModel {
	private final ISystemKiller systemKiller;
	private final ListenerManager listenerManager;
	private Ball[] balls;
	private Ball myBall;
	private final INetworkClient networkClient;

	public MainModel(ISystemKiller systemKiller,
			final INetworkClient networkClient) {
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
		myBall.setColorRed(255);
		myBall.setColorGreen(255);
		myBall.setColorBlue(255);
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
		myBall.setPositionY(myBall.getPositionY() - 1);
		networkClient.update(myBall);
	}

	public void moveBallDown() {
		myBall.setPositionY(myBall.getPositionY() + 1);
		networkClient.update(myBall);
	}

	public void moveBallLeft() {
		myBall.setPositionX(myBall.getPositionX() - 1);
		networkClient.update(myBall);
	}

	public void moveBallRight() {
		myBall.setPositionX(myBall.getPositionX() + 1);
		networkClient.update(myBall);
	}

	public void updateColor(String updatedColor) {
		if ("White".equals(updatedColor)) {
			myBall.setColorRed(Color.white.getRed());
			myBall.setColorGreen(Color.white.getGreen());
			myBall.setColorBlue(Color.white.getBlue());
		} else if ("Red".equals(updatedColor)) {
			myBall.setColorRed(Color.red.getRed());
			myBall.setColorGreen(Color.red.getGreen());
			myBall.setColorBlue(Color.red.getBlue());
		} else if ("Orange".equals(updatedColor)) {
			myBall.setColorRed(Color.orange.getRed());
			myBall.setColorGreen(Color.orange.getGreen());
			myBall.setColorBlue(Color.orange.getBlue());
		} else if ("Yellow".equals(updatedColor)) {
			myBall.setColorRed(Color.yellow.getRed());
			myBall.setColorGreen(Color.yellow.getGreen());
			myBall.setColorBlue(Color.yellow.getBlue());
		} else if ("Green".equals(updatedColor)) {
			myBall.setColorRed(Color.green.getRed());
			myBall.setColorGreen(Color.green.getGreen());
			myBall.setColorBlue(Color.green.getBlue());
		} else if ("Blue".equals(updatedColor)) {
			myBall.setColorRed(Color.blue.getRed());
			myBall.setColorGreen(Color.blue.getGreen());
			myBall.setColorBlue(Color.blue.getBlue());
		} else if ("Purple".equals(updatedColor)) {
			myBall.setColorRed(125);
			myBall.setColorGreen(0);
			myBall.setColorBlue(125);
		} else if ("Black".equals(updatedColor)) {
			myBall.setColorRed(Color.black.getRed());
			myBall.setColorGreen(Color.black.getGreen());
			myBall.setColorBlue(Color.black.getBlue());
		}
	}
}
