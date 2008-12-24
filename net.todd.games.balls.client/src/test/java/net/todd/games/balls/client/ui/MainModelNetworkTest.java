package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
		assertEquals(255, networkClient.ballReceived.getColorBlue());
		assertEquals(255, networkClient.ballReceived.getColorRed());
		assertEquals(255, networkClient.ballReceived.getColorGreen());
		assertEquals(0, networkClient.ballReceived.getPositionX());
		assertEquals(0, networkClient.ballReceived.getPositionY());
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
		assertEquals(1, balls[0].getPositionX());
		assertEquals(2, balls[0].getPositionY());
		assertEquals(3, balls[1].getPositionX());
		assertEquals(4, balls[1].getPositionY());
	}
}
