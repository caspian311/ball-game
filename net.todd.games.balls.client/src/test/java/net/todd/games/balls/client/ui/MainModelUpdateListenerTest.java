package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

import org.junit.Before;
import org.junit.Test;

public class MainModelUpdateListenerTest {
	private MockNetworkClient networkClient;
	private boolean modelEventFired;
	private Ball[] ballsReceived;

	@Before
	public void setUp() {
		networkClient = new MockNetworkClient();
		
		modelEventFired = false;
		
		ballsReceived = null;
	}
	
	@Test
	public void testModelListenersAreNotifiedWhenNetworkEventFires() {
		MainModel model = new MainModel(null, networkClient);
		model.addModelListener(new IListener() {
			public void fireEvent() {
				modelEventFired = true;
			}
		});
		
		assertNotNull(networkClient.listener);
		
		assertFalse(modelEventFired);
		networkClient.listener.fireEvent();
		assertTrue(modelEventFired);
	}
	
	@Test
	public void testModelNotifiesListenersAfterItGrabsBallsFromNetwork() {
		final MainModel model = new MainModel(null, networkClient);
		model.addModelListener(new IListener() {
			public void fireEvent() {
				ballsReceived = model.getBallData();
			}
		});

		networkClient.ballsOnServer = new Ball[2];
		networkClient.ballsOnServer[0] = new Ball();
		networkClient.ballsOnServer[1] = new Ball();
		
		assertNull(ballsReceived);
		networkClient.listener.fireEvent();
		assertNotNull(ballsReceived);
		assertEquals(2, ballsReceived.length);
	}
	
	private static class MockNetworkClient extends AbstractNetworkClient {
		private IListener listener;
		private Ball[] ballsOnServer;

		@Override
		public void addListener(IListener listener) {
			this.listener = listener;
		}
		
		@Override
		public void update(Ball ball) {
		}
		
		@Override
		public Ball[] getBalls() {
			return ballsOnServer;
		}
	}
}
