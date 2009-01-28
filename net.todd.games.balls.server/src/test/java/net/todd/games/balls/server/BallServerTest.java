package net.todd.games.balls.server;

import static org.junit.Assert.assertEquals;

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

public class BallServerTest {
	private BallServer server;

	@Test
	@Ignore
	public void testAClientCanConnectToServer() {
		server = new BallServer();

		SocketConnector connector = new NioSocketConnector();

		connector.getFilterChain().addLast("codec",
		        new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.getFilterChain().addLast("logger", new LoggingFilter());

		TestHandler handler = new TestHandler();
		connector.setHandler(handler);

		connector.connect(new InetSocketAddress("localhost", 9898));

		while (handler.serverBalls == null)
			;

		assertEquals("Color: Red, Position: {100, 100}", handler.serverBalls[0]
		        .toString());
	}

	@After
	public void tearDown() {
		if (server != null) {
			server.killServer();
		}
	}

	private static class TestHandler extends IoHandlerAdapter {
		private Ball[] serverBalls;

		@Override
		public void sessionOpened(IoSession session) throws Exception {
			System.out.println("Client: session was opened");

			Ball ball = new Ball();
			ball.setColor(BallColor.Red);
			ball.setPositionX(100);
			ball.setPositionY(100);
			session.write(ball);
		}

		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			ServerResponse response = (ServerResponse) message;
			serverBalls = response.getBalls();
		}
	}
}
