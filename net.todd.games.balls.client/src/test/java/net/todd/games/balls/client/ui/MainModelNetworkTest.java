package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;

import org.junit.Before;
import org.junit.Ignore;
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
	@Ignore
	public void testWhenModelStartsUpTellsNetworkAboutNewBall() {
		assertNull(networkClient.ballReceived);
		new MainModel(null, networkClient);
		assertNotNull(networkClient.ballReceived);
		assertEquals(255, networkClient.ballReceived.getColorBlue());
		assertEquals(255, networkClient.ballReceived.getColorRed());
		assertEquals(255, networkClient.ballReceived.getColorGreen());
		assertEquals(0.0f, networkClient.ballReceived.getPositionX());
		assertEquals(0.0f, networkClient.ballReceived.getPositionY());
		assertEquals(0.0f, networkClient.ballReceived.getPositionZ());
	}

	@Test
	@Ignore
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
	@Ignore
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
		assertEquals(1, balls[0].getPositionX());
		assertEquals(2, balls[0].getPositionY());
		assertEquals(3, balls[1].getPositionX());
		assertEquals(4, balls[1].getPositionY());
	}
}
