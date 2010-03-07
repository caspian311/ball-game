package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.todd.common.uitools.IListener;

import org.junit.Before;
import org.junit.Test;

public class MainPresenterPositionListenerTest {
	private MockMainView view;
	private MockMainModel model;

	@Before
	public void setUp() {
		view = new MockMainView();
		model = new MockMainModel();
	}

	@Test
	public void testPresenterListensForUpKey() {
		new MainPresenter(view, model);
		
		assertFalse(model.moveBallUp);
		view.upKeyListenerListener.fireEvent();
		assertTrue(model.moveBallUp);
	}
	
	@Test
	public void testPresenterListensForDownKey() {
		new MainPresenter(view, model);

		assertFalse(model.moveBallDown);
		view.downKeyListenerListener.fireEvent();
		assertTrue(model.moveBallDown);
	}
	
	@Test
	public void testPresenterListensForLeftKey() {
		new MainPresenter(view, model);

		assertFalse(model.moveBallLeft);
		view.leftKeyListenerListener.fireEvent();
		assertTrue(model.moveBallLeft);
	}

	@Test
	public void testPresenterListensForRightKey() {
		new MainPresenter(view, model);

		assertFalse(model.moveBallRight);
		view.rightKeyListenerListener.fireEvent();
		assertTrue(model.moveBallRight);
	}
	
	private static class MockMainModel extends AbstractMainModel {
		private boolean moveBallLeft;
		private boolean moveBallDown;
		private boolean moveBallUp;
		private boolean moveBallRight;

		@Override
		public void moveBallUp() {
			moveBallUp = true;
		}
		
		@Override
		public void moveBallDown() {
			moveBallDown = true;
		}
		
		public void moveBallLeft() {
			moveBallLeft = true;
		}

		public void moveBallRight() {
			moveBallRight = true;
		}
	}

	private static class MockMainView extends AbstractMainView {
		private IListener upKeyListenerListener;
		private IListener downKeyListenerListener;
		private IListener rightKeyListenerListener;
		private IListener leftKeyListenerListener;

		@Override
		public void addUpKeyListener(IListener listener) {
			this.upKeyListenerListener = listener;
		}
		
		@Override
		public void addDownKeyListener(IListener listener) {
			this.downKeyListenerListener = listener;
		}

		@Override
		public void addLeftKeyListener(IListener listener) {
			this.leftKeyListenerListener = listener;
		}

		@Override
		public void addRightKeyListener(IListener listener) {
			this.rightKeyListenerListener = listener;
		}

		@Override
		public void showView() {
		}
	}
}
