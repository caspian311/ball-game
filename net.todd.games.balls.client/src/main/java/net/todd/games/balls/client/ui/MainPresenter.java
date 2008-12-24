package net.todd.games.balls.client.ui;

import net.todd.common.uitools.IListener;


public class MainPresenter {
	public MainPresenter(final IMainView mainView, final IMainModel mainModel) {
		mainView.showView();

		mainView.addClosingListener(new IListener() {
			public void fireEvent() {
				mainModel.doClose();
			}
		});

		mainModel.addModelListener(new IListener() {
			public void fireEvent() {
				mainView.setBallPositions(mainModel.getBallData());
			}
		});
		
		mainView.addUpKeyListener(new IListener() {
			public void fireEvent() {
				mainModel.moveBallUp();
			}
		});
		
		mainView.addDownKeyListener(new IListener() {
			public void fireEvent() {
				mainModel.moveBallDown();
			}
		});
		
		mainView.addLeftKeyListener(new IListener() {
			public void fireEvent() {
				mainModel.moveBallLeft();
			}
		});

		mainView.addRightKeyListener(new IListener() {
			public void fireEvent() {
				mainModel.moveBallRight();
			}
		});
	
		mainView.addColorChangeListener(new IListener() {
			public void fireEvent() {
				mainModel.updateColor(mainView.getSelectedColor());
			}
		});
	}
}
