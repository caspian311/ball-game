package net.todd.games.balls.client.ui;


import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

public interface IMainModel {
	/**
	 * Closing the system down
	 */
	void doClose();

	/**
	 * add a listener to the model for when the balls are updated
	 * 
	 * @param listener
	 */
	void addModelListener(IListener listener);
	
	/**
	 * retreive ball data
	 * 
	 * @return
	 */
	Ball[] getBallData();
	
	void moveBallUp();

	void moveBallDown();
	
	void moveBallRight();

	void moveBallLeft();
	
	void updateColor(String updatedColor);
}
