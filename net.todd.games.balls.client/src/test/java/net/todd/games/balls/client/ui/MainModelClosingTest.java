package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.todd.games.balls.common.Ball;

import org.junit.Before;
import org.junit.Test;

public class MainModelClosingTest {
	private MockSystemKiller systemKiller;
	private MockNetworkClient network;
	
	@Before
	public void setUp() {
		systemKiller = new MockSystemKiller();
		network = new MockNetworkClient();
	}
	
	@Test
	public void testModelKillsSystemWhenClosing() {
		MainModel mainModel = new MainModel(systemKiller, null);
		
		assertFalse(systemKiller.isSystemDead);
		mainModel.doClose();
		assertTrue(systemKiller.isSystemDead);
	}
	
	@Test
	public void testModelShutsDownNetworkWhenClosing() {
		MainModel mainModel = new MainModel(null, network);

		assertFalse(network.isClosed);
		mainModel.doClose();
		assertTrue(network.isClosed);
	}
	
	private static class MockSystemKiller implements ISystemKiller {
		private boolean isSystemDead;

		public void killSystem() {
			isSystemDead = true;
		}
	}
	
	private static class MockNetworkClient extends AbstractNetworkClient {
		private boolean isClosed;
		
		@Override
		public void closeConnection() {
			isClosed = true;
		}
		
		@Override
		public void update(Ball ball) {
		}
	}
}
