package net.todd.games.balls.client;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

public interface INetworkClient {
	void update(Ball ball);
	
	Ball[] getBalls();
	
	boolean isConnected();
	
	void closeConnection();
	
	void addListener(IListener listener);
}
