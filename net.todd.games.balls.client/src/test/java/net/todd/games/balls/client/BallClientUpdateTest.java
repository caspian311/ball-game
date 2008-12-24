package net.todd.games.balls.client;

import static org.junit.Assert.assertEquals;

import java.net.InetSocketAddress;
import java.util.Map;

import net.todd.games.balls.common.Ball;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BallClientUpdateTest {
	private SocketAcceptor acceptor;
	private int ballsOnServer;
	private Ball ballReceived;

	@Before
	public void setUp() throws Exception {
		ballsOnServer = 0;
		ballReceived = null;
		
		acceptor = new NioSocketAcceptor(Runtime.getRuntime()
				.availableProcessors());

		acceptor.getFilterChain().addFirst("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

		acceptor.setHandler(new IoHandlerAdapter() {

			@Override
			public void messageReceived(IoSession session, Object message)
					throws Exception {
				if (message instanceof Ball) {
					ballsOnServer++;
					ballReceived = (Ball) message;
				}
			}
		});

		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.setCloseOnDeactivation(true);

		acceptor.bind(new InetSocketAddress(9898));
	}
	
	@Test
	public void testAddingBallToClientAddsToServer() throws Exception {
		BallClient ballClient = new BallClient();
		Thread.sleep(100);
		ballClient.update(new Ball());
		Thread.sleep(100);
		assertEquals(1, ballsOnServer);
	}

	@Test
	public void testAdding2BallsToClientAddsToServer() throws Exception {
		BallClient ballClient = new BallClient();
		Thread.sleep(100);
		ballClient.update(new Ball());
		ballClient.update(new Ball());
		Thread.sleep(100);
		assertEquals(2, ballsOnServer);
	}
	
	@Test
	public void testNewBallsOnServerAreBlue() throws Exception {
		BallClient ballClient = new BallClient();
		Thread.sleep(100);
		Ball ball = new Ball();
		ball.setColorBlue(255);
		ballClient.update(ball);
		Thread.sleep(100);
		assertEquals(255, ballReceived.getColorBlue());
		assertEquals(0, ballReceived.getColorRed());
		assertEquals(0, ballReceived.getColorGreen());
	}
	
	@After
	public void tearDown() {
		Map<Long, IoSession> managedSessions = acceptor.getManagedSessions();
		for (long id : managedSessions.keySet()) {
			IoSession session = managedSessions.get(id);
			session.close();
		}
		acceptor.dispose();
		acceptor.unbind();
	}
}
