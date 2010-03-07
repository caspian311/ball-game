package net.todd.games.balls.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.InetSocketAddress;
import java.util.Map;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.common.Ball;
import net.todd.games.balls.common.BallColor;
import net.todd.games.balls.common.ServerResponse;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BallClientHasListenersTest {
	private SocketAcceptor acceptor;

	@Before
	public void setUp() throws Exception {
		acceptor = new NioSocketAcceptor(Runtime.getRuntime()
				.availableProcessors());

		acceptor.getFilterChain().addFirst("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

		acceptor.setHandler(new IoHandlerAdapter() {
		});

		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.setCloseOnDeactivation(true);

		acceptor.bind(new InetSocketAddress(9898));
	}

	private boolean listenerWasNotified;

	@Test
	@Ignore
	public void testClientNotifiesListenersWhenMessageComesIn()
			throws Exception {
		BallClient client = new BallClient();
		Thread.sleep(100);

		client.addListener(new IListener() {
			public void fireEvent() {
				listenerWasNotified = true;
			}
		});

		assertFalse(listenerWasNotified);

		long id = acceptor.getManagedSessions().keySet().iterator().next();
		IoSession session = acceptor.getManagedSessions().get(id);

		ServerResponse response = new ServerResponse();
		session.write(response);

		Thread.sleep(100);

		assertTrue(listenerWasNotified);
	}

	@Test
	@Ignore
	public void testGettingClientBallsGetsAllBallsGivenFromServer()
			throws Exception {
		BallClient client = new BallClient();
		Thread.sleep(100);

		assertFalse(listenerWasNotified);

		long id = acceptor.getManagedSessions().keySet().iterator().next();
		IoSession session = acceptor.getManagedSessions().get(id);

		ServerResponse response = new ServerResponse();
		Ball[] balls = new Ball[2];
		balls[0] = new Ball();
		balls[0].setColor(BallColor.Blue);
		balls[1] = new Ball();
		balls[1].setColor(BallColor.Green);
		response.setBalls(balls);
		session.write(response);

		Thread.sleep(100);

		assertEquals(2, client.getBalls().length);
		assertEquals(0, client.getBalls()[0].getColor().getColor().getRed());
		assertEquals(0, client.getBalls()[0].getColor().getColor().getGreen());
		assertEquals(255, client.getBalls()[0].getColor().getColor().getBlue());
		assertEquals(0, client.getBalls()[1].getColor().getColor().getRed());
		assertEquals(255, client.getBalls()[1].getColor().getColor().getGreen());
		assertEquals(0, client.getBalls()[1].getColor().getColor().getBlue());

		response = new ServerResponse();
		balls = new Ball[1];
		balls[0] = new Ball();
		balls[0].setColor(BallColor.Red);
		response.setBalls(balls);
		session.write(response);

		Thread.sleep(100);

		assertEquals(1, client.getBalls().length);
		assertEquals(255, client.getBalls()[0].getColor().getColor().getRed());
		assertEquals(0, client.getBalls()[0].getColor().getColor().getGreen());
		assertEquals(0, client.getBalls()[0].getColor().getColor().getBlue());
	}

	private Ball[] ballsFromClient = null;

	@Test
	public void testNotifyListenersIsCalledAfterBallsRetreivedFromServerMessage()
			throws Exception {
		final BallClient client = new BallClient();
		client.addListener(new IListener() {
			public void fireEvent() {
				ballsFromClient = client.getBalls();
			}
		});

		Thread.sleep(100);

		assertNull(ballsFromClient);
		long id = acceptor.getManagedSessions().keySet().iterator().next();
		IoSession session = acceptor.getManagedSessions().get(id);

		ServerResponse response = new ServerResponse();
		response.setBalls(new Ball[1]);
		session.write(response);
		Thread.sleep(100);

		assertNotNull(ballsFromClient);
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
