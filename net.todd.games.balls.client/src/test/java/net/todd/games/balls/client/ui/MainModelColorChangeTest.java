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

		assertEquals(Color.white.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.white.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.white.getBlue(), network.ballReceived.getColorBlue());
	}

	@Test
	public void testWhiteColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("White");

		assertEquals(Color.white.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.white.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.white.getBlue(), network.ballReceived.getColorBlue());
	}

	@Test
	public void testRedColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Red");

		assertEquals(Color.red.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.red.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.red.getBlue(), network.ballReceived.getColorBlue());
	}

	@Test
	public void testOrangeColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Orange");

		assertEquals(Color.orange.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.orange.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.orange.getBlue(), network.ballReceived.getColorBlue());
	}

	@Test
	public void testYellowColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Yellow");

		assertEquals(Color.yellow.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.yellow.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.yellow.getBlue(), network.ballReceived.getColorBlue());
	}

	@Test
	public void testGreenColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Green");

		assertEquals(Color.green.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.green.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.green.getBlue(), network.ballReceived.getColorBlue());
	}

	@Test
	public void testBlueColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Red");
		model.updateColor("Blue");

		assertEquals(Color.blue.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.blue.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.blue.getBlue(), network.ballReceived.getColorBlue());
	}

	@Test
	public void testPurpleColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Purple");

		assertEquals(125, network.ballReceived.getColorRed());
		assertEquals(0, network.ballReceived.getColorGreen());
		assertEquals(125, network.ballReceived.getColorBlue());
	}

	@Test
	public void testBlackColorChangeGetsBallSentToNetwork() {
		MainModel model = new MainModel(null, network);

		model.updateColor("Blue");
		model.updateColor("Black");

		assertEquals(Color.black.getRed(), network.ballReceived.getColorRed());
		assertEquals(Color.black.getGreen(), network.ballReceived.getColorGreen());
		assertEquals(Color.black.getBlue(), network.ballReceived.getColorBlue());
	}

	private static class MockNetworkClient extends AbstractNetworkClient {
		private Ball ballReceived;

		@Override
		public void update(Ball ball) {
			ballReceived = ball.clone();
		}
	}
}
