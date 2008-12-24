package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SystemKillerTest {
	private boolean killHasBeenMade;
	private MockNetworkClient networkClient;

	@Before
	public void setUp() {
		killHasBeenMade = false;
		networkClient = new MockNetworkClient();
	}

	@Test
	public void testKillingSystemDoesADoKill() {
		SystemKiller killer = new SystemKiller(null) {
			@Override
			void doKill() {
				killHasBeenMade = true;
			}
		};

		assertFalse(killHasBeenMade);
		killer.killSystem();
		assertTrue(killHasBeenMade);
	}
	
	@Test
	public void testKillingSystemAttemptsToShutdownNetwork() {
		SystemKiller killer = new SystemKiller(networkClient) {
			@Override
			void doKill() {
				killHasBeenMade = true;
			}
		};
		
		assertFalse(networkClient.connectionHasBeenClosed);
		killer.killSystem();
		assertTrue(networkClient.connectionHasBeenClosed);
	}
	
	private static class MockNetworkClient extends AbstractNetworkClient {
		private boolean connectionHasBeenClosed;

		@Override
		public void closeConnection() {
			connectionHasBeenClosed = true;
		}
	}
}
