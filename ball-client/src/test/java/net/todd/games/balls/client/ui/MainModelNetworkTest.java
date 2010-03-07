package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

import org.junit.Before;
import org.junit.Test;

public class MainModelNetworkTest {
	private static class MockNetworkClient extends AbstractNetworkClient {
		private Ball ballReceived;
		private Ball[] allBallsOnNetwork;
		private IListener networkListener;

		@Override
		public void update(Ball ball) {
			ballReceived = ball;
		}

		@Override
		public Ball[] getBalls() {
			return allBallsOnNetwork;
		}

		@Override
		public void addListener(IListener listener) {
			networkListener = listener;
		}
	}

	private MockNetworkClient networkClient;

	@Before
	public void setUp() {
		networkClient = new MockNetworkClient();
	}

	@Test
	public void testWhenModelStartsUpTellsNetworkAboutNewBall() {
		assertNull(networkClient.ballReceived);
		new MainModel(null, networkClient);
		assertNotNull(networkClient.ballReceived);
		assertEquals(Color.white, networkClient.ballReceived.getColor()
				.getColor());
		assertEquals(0.0f, networkClient.ballReceived.getPositionX());
		assertEquals(0.0f, networkClient.ballReceived.getPositionY());
		assertEquals(0.0f, networkClient.ballReceived.getPositionZ());
	}

	@Test
	public void testAllBallsGetUniqueIdToStartWith() {
		new MainModel(null, networkClient);
		List<String> previousIds = new ArrayList<String>();
		previousIds.add(networkClient.ballReceived.getId());
		networkClient.ballReceived = null;
		for (int i = 0; i < 1000; i++) {
			new MainModel(null, networkClient);
			if (previousIds.contains(networkClient.ballReceived.getId())) {
				fail("Id not unique");
			}
			previousIds.add(networkClient.ballReceived.getId());
			networkClient.ballReceived = null;
		}
	}

	@Test
	public void testModelGetsBallsFromNetworkWhenNotified() {
		networkClient.allBallsOnNetwork = new Ball[2];
		networkClient.allBallsOnNetwork[0] = new Ball();
		networkClient.allBallsOnNetwork[0].setPositionX(1);
		networkClient.allBallsOnNetwork[0].setPositionY(2);
		networkClient.allBallsOnNetwork[1] = new Ball();
		networkClient.allBallsOnNetwork[1].setPositionX(3);
		networkClient.allBallsOnNetwork[1].setPositionY(4);

		MainModel model = new MainModel(null, networkClient);
		assertNull(model.getBallData());
		networkClient.networkListener.fireEvent();
		assertNotNull(model.getBallData());

		Ball[] balls = model.getBallData();
		assertEquals(2, balls.length);
		assertEquals(1f, balls[0].getPositionX());
		assertEquals(2f, balls[0].getPositionY());
		assertEquals(3f, balls[1].getPositionX());
		assertEquals(4f, balls[1].getPositionY());
	}
}
