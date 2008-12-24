package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import net.todd.common.uitools.IListener;

import org.junit.Before;
import org.junit.Test;

public class MainPresenterStartupAndShutdownTest {
	private MockMainView mockView;
	private MockMainModel mockModel;

	@Before
	public void setUp() {
		mockView = new MockMainView();
		mockModel = new MockMainModel();
	}

	@Test
	public void testWhenPresenterStartsUpMainViewIsShown() {
		assertFalse(mockView.isShown);
		new MainPresenter(mockView, mockModel);
		assertTrue(mockView.isShown);
	}
	
	@Test
	public void testWhenMainViewClosedModelIsToldToClose() {
		assertNull(mockView.closingListener);
		new MainPresenter(mockView, mockModel);
		assertNotNull(mockView.closingListener);

		assertFalse(mockModel.isClosed);
		mockView.closingListener.fireEvent();
		assertTrue(mockModel.isClosed);
	}
	
	private static class MockMainView extends AbstractMainView {
		private boolean isShown;
		private IListener closingListener;
		
		@Override
		public void showView() {
			isShown = true;
		}
		
		@Override
		public void addClosingListener(IListener listener) {
			closingListener = listener;
		}
		
		@Override
		public void addStartButtonListener(IListener listener) {
		}
		
		@Override
		public void addStopButtonListener(IListener listener) {
		}
	}
	
	private static class MockMainModel extends AbstractMainModel {
		private boolean isClosed;

		@Override
		public void doClose() {
			isClosed = true;
		}

		@Override
		public void addModelListener(IListener listener) {
		}
	}
}
