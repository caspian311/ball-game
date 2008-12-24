package net.todd.games.balls.server;

import net.todd.games.balls.common.Ball;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BallUpdateHandler extends IoHandlerAdapter {
	private static final Logger log = LoggerFactory
			.getLogger(BallUpdateHandler.class);
	private final SendUpdatesThread updateThread;
	
	public BallUpdateHandler(SendUpdatesThread updateThread) {
		this.updateThread = updateThread;
		updateThread.start();
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		log.error("Server: error occurred", cause);
		session.close();
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.info("Server: session opened");
		
		updateThread.addClient(session);
	}

	@Override
	public void messageReceived(IoSession session, Object obj) throws Exception {
		log.info("Server: message received");

		if (obj instanceof Ball) {
			Ball ball = (Ball) obj;
			
			updateThread.ballUpdate(session.getId(), ball);
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		log.info("Server: closing idle session");
		session.close();
	}
}
