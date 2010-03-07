package net.todd.games.balls.client.ui;


import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

public interface IMainView {
	/**
	 * display the view
	 * 
	 * @param listener
	 */
	void showView();

	/**
	 * add a listener that will be notified when the view is closing
	 * 
	 * @param listener
	 */
	void addClosingListener(IListener listener);

	/**
	 * give the view the ball position information
	 * 
	 * @param ballData
	 */
	void setBallPositions(Ball[] ballData);
	
	void addUpKeyListener(IListener listener);
	
	void addDownKeyListener(IListener listener);

	void addLeftKeyListener(IListener listener);

	void addRightKeyListener(IListener listener);
	
	void addColorChangeListener(IListener listener);
	
	String getSelectedColor();
}
