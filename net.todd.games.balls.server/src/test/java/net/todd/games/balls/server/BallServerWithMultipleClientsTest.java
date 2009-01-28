package net.todd.games.balls.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.InetSocketAddress;

import net.todd.games.balls.common.Ball;
import net.todd.games.balls.common.BallColor;
import net.todd.games.balls.common.ServerResponse;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

public class BallServerWithMultipleClientsTest {
	private BallServer server;

	@Test
	@Ignore
	public void testMultipleClientsConnectToServer() throws Exception {
		server = new BallServer(1);

		Ball ball = new Ball();
		ball.setColor(BallColor.Red);
		ball.setPositionX(100);
		ball.setPositionY(100);

		TestHandler handler1 = connectClientToServer(ball);
		TestHandler handler2 = connectClientToServer(null);

		Thread.sleep(100);

		assertNotNull(handler1.serverBalls);
		assertEquals(1, handler1.serverBalls.length);
		assertNotNull(handler2.serverBalls);
		assertEquals(1, handler2.serverBalls.length);
	}

	@After
	public void tearDown() {
		if (server != null) {
			server.killServer();
		}
	}

	private TestHandler connectClientToServer(Ball clientBall) {
		SocketConnector connector = new NioSocketConnector();

		connector.getFilterChain().addLast("codec",
		        new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.getFilterChain().addLast("logger", new LoggingFilter());

		TestHandler handler = new TestHandler(clientBall);
		connector.setHandler(handler);

		connector.connect(new InetSocketAddress("localhost", 9898));
		return handler;
	}

	private static class TestHandler extends IoHandlerAdapter {
		private Ball[] serverBalls;
		private final Ball clientBall;

		public TestHandler(Ball ball) {
			this.clientBall = ball;
		}

		@Override
		public void sessionOpened(IoSession session) throws Exception {
			System.out.println("Client: session was opened");

			if (clientBall != null) {
				session.write(clientBall);
			}
		}

		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			ServerResponse response = (ServerResponse) message;
			serverBalls = response.getBalls();
		}
	}
}
