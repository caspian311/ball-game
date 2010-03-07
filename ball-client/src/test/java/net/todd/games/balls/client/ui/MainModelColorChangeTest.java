package net.todd.games.balls.client.ui;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import net.todd.games.balls.common.Ball;

import org.junit.Before;
import org.junit.Test;

public class MainModelColorChangeTest {
	private MockNetworkClient network;

	@Before
	public void setUp() {
		network = new MockNetworkClient();
	}

	@Test
	public void testDefaultColorIsWhite() {
		new MainModel(null, network);

		assertEquals(Color.white, network.ballReceived.getColor().getColor());
	}

	@Test
	public void testWhiteColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("White");

		assertEquals(Color.white, network.ballReceived.getColor().getColor());
	}

	@Test
	public void testRedColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Red");

		assertEquals(Color.red, network.ballReceived.getColor().getColor());
	}

	@Test
	public void testOrangeColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Orange");

		assertEquals(Color.orange, network.ballReceived.getColor().getColor());
	}

	@Test
	public void testYellowColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Yellow");

		assertEquals(Color.yellow, network.ballReceived.getColor().getColor());
	}

	@Test
	public void testGreenColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Green");

		assertEquals(Color.green, network.ballReceived.getColor().getColor());
	}

	@Test
	public void testBlueColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Red");
		model.updateColor("Blue");

		assertEquals(Color.blue, network.ballReceived.getColor().getColor());
	}

	@Test
	public void testPurpleColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Purple");

		assertEquals(125, network.ballReceived.getColor().getColor().getRed());
		assertEquals(0, network.ballReceived.getColor().getColor().getGreen());
		assertEquals(125, network.ballReceived.getColor().getColor().getBlue());
	}

	@Test
	public void testBlackColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Black");

		assertEquals(Color.black, network.ballReceived.getColor().getColor());
	}

	private static class MockNetworkClient extends AbstractNetworkClient {
		private Ball ballReceived;

		@Override
		public void update(Ball ball) {
			ballReceived = ball.clone();
		}
	}
}
