package net.todd.games.balls.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BallServer {
	private static final int PORT = 9898;
	private final SocketAcceptor acceptor;
	private final SendUpdatesThread updateThread;

	private static final Logger log = LoggerFactory.getLogger(BallServer.class);

	public BallServer(int idleTimeout) {
		acceptor = new NioSocketAcceptor(Runtime.getRuntime()
				.availableProcessors());

		acceptor.getFilterChain().addFirst("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

		updateThread = new SendUpdatesThread();
		acceptor.setHandler(new BallUpdateHandler(updateThread));

		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
				idleTimeout);

		acceptor.setCloseOnDeactivation(true);
		
		try {
			acceptor.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
		}

		log.info("Server: listening on port " + PORT);
	}

	public BallServer() {
		this(10);
	}

	public void killServer() {
		Map<Long, IoSession> managedSessions = acceptor.getManagedSessions();
		for (long id : managedSessions.keySet()) {
			IoSession session = managedSessions.get(id);
			session.close();
		}
		updateThread.interrupt();
		acceptor.dispose();
		acceptor.unbind();
	}

	public static void main(String[] args) {
		new BallServer();
	}
}
