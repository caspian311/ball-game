package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import net.todd.games.balls.common.Ball;

import org.junit.Before;
import org.junit.Test;

public class MainModelPositionUpdateTest {
	private TestNetworkClient networkClient;

	@Before
	public void setUp() {
		networkClient = new TestNetworkClient();
	}
	
	@Test
	public void testMoveBallUp() {
		MainModel model = new MainModel(null, networkClient);
		assertNotNull(networkClient.ballSent);
		assertEquals(0, networkClient.ballSent.getPositionX());
		assertEquals(0, networkClient.ballSent.getPositionY());
		networkClient.ballSent = null;
		model.moveBallUp();
		assertNotNull(networkClient.ballSent);
		assertEquals(0, networkClient.ballSent.getPositionX());
		assertEquals(-1, networkClient.ballSent.getPositionY());
	}
	
	@Test
	public void testMoveBallDown() {
		MainModel model = new MainModel(null, networkClient);
		assertNotNull(networkClient.ballSent);
		assertEquals(0, networkClient.ballSent.getPositionX());
		assertEquals(0, networkClient.ballSent.getPositionY());
		networkClient.ballSent = null;
		model.moveBallDown();
		assertNotNull(networkClient.ballSent);
		assertEquals(0, networkClient.ballSent.getPositionX());
		assertEquals(1, networkClient.ballSent.getPositionY());
	}
	
	@Test
	public void testMoveBallLeft() {
		MainModel model = new MainModel(null, networkClient);
		assertNotNull(networkClient.ballSent);
		assertEquals(0, networkClient.ballSent.getPositionX());
		assertEquals(0, networkClient.ballSent.getPositionY());
		networkClient.ballSent = null;
		model.moveBallLeft();
		assertNotNull(networkClient.ballSent);
		assertEquals(-1, networkClient.ballSent.getPositionX());
		assertEquals(0, networkClient.ballSent.getPositionY());
	}
	
	@Test
	public void testMoveBallRight() {
		MainModel model = new MainModel(null, networkClient);
		assertNotNull(networkClient.ballSent);
		assertEquals(0, networkClient.ballSent.getPositionX());
		assertEquals(0, networkClient.ballSent.getPositionY());
		networkClient.ballSent = null;
		model.moveBallRight();
		assertNotNull(networkClient.ballSent);
		assertEquals(1, networkClient.ballSent.getPositionX());
		assertEquals(0, networkClient.ballSent.getPositionY());
	}

	private static class TestNetworkClient extends AbstractNetworkClient {
		private Ball ballSent;

		@Override
		public void update(Ball ball) {
			ballSent = ball;
		}
	}
}
