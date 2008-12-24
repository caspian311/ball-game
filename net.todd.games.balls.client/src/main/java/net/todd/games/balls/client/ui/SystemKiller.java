package net.todd.games.balls.client.ui;

import net.todd.games.balls.client.INetworkClient;


public class SystemKiller implements ISystemKiller {
	private final INetworkClient networkClient;

	public SystemKiller(INetworkClient networkClient) {
		this.networkClient = networkClient;
	}

	public void killSystem() {
		if (networkClient != null) {
			networkClient.closeConnection();
		}
		doKill();
	}

	void doKill() {
		System.exit(0);
	}
}
