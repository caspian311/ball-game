package net.todd.games.balls.client;

import java.net.InetSocketAddress;

import net.todd.common.uitools.IListener;
import net.todd.common.uitools.ListenerManager;
import net.todd.games.balls.common.Ball;
import net.todd.games.balls.common.ServerResponse;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class BallClient implements INetworkClient {
	private static final int PORT = 9898;
	private final ListenerManager listenerManager;
	private Ball[] balls;
	protected IoSession session;
	private boolean isConnected;

	public BallClient() {
		listenerManager = new ListenerManager();
		SocketConnector connector = new NioSocketConnector();

		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.getFilterChain().addLast("logger", new LoggingFilter());

		connector.setHandler(new IoHandlerAdapter() {
			@Override
			public void sessionOpened(IoSession session) throws Exception {
				BallClient.this.session = session;
			}

			@Override
			public void messageReceived(IoSession session, Object message)
					throws Exception {
				ServerResponse response = (ServerResponse) message;
				balls = response.getBalls();
				listenerManager.notifyListeners();
			}
		});

		connector.addListener(new IoServiceListener() {
			public void serviceActivated(IoService service) throws Exception {
				isConnected = true;
			}

			public void serviceDeactivated(IoService service) throws Exception {
			}

			public void serviceIdle(IoService service, IdleStatus idleStatus)
					throws Exception {
			}

			public void sessionCreated(IoSession session) throws Exception {
			}

			public void sessionDestroyed(IoSession session) throws Exception {
			}
		});
		connector.connect(new InetSocketAddress("localhost", PORT));
	}

	public void addListener(IListener listener) {
		listenerManager.addListener(listener);
	}

	public Ball[] getBalls() {
		return balls;
	}

	public void update(Ball ball) {
		session.write(ball);
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void closeConnection() {
		if (session != null) {
			session.close();
		}
	}
}
