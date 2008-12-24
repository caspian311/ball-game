package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import net.todd.common.uitools.IListener;

import org.junit.Before;
import org.junit.Test;

public class MainPresenterColorChangeListenerTest {
	private MockMainView view;
	private MockMainModel model;

	@Before
	public void setUp() {
		view = new MockMainView();
		model = new MockMainModel();
	}

	@Test
	public void testColorChangeInViewGetsPushedToModel() {
		new MainPresenter(view, model);
		
		view.selectedColor = "Red";
		view.colorChangeListener.fireEvent();
		assertEquals("Red", model.updatedColor);
	}

	private static class MockMainView extends AbstractMainView {
		private String selectedColor;
		private IListener colorChangeListener;
		
		@Override
		public String getSelectedColor() {
			return selectedColor;
		}
		
		@Override
		public void addColorChangeListener(IListener listener) {
			colorChangeListener = listener;
		}
		
		@Override
		public void showView() {
		}
	}
	
	private static class MockMainModel extends AbstractMainModel {
		private String updatedColor;

		@Override
		public void updateColor(String updatedColor) {
			this.updatedColor = updatedColor;
		}
	}
}
