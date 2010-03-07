package net.todd.games.balls.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.todd.games.balls.common.Ball;
import net.todd.games.balls.common.ServerResponse;

import org.apache.mina.core.session.IoSession;

public class SendUpdatesThread extends Thread {
	private final List<IoSession> clientSessions;
	private final HashMap<Long, Ball> ballData;
	private boolean running;

	public SendUpdatesThread() {
		clientSessions = new ArrayList<IoSession>();
		ballData = new HashMap<Long, Ball>();
	}

	public void addClient(IoSession session) {
		clientSessions.add(session);
	}

	public void ballUpdate(long id, Ball ball) {
		ballData.put(id, ball);
		// System.out.println("ball received: " + ball);
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			List<IoSession> clonedClientSessions = new ArrayList<IoSession>();

			synchronized (clientSessions) {
				for (IoSession client : clientSessions) {
					clonedClientSessions.add(client);
				}
			}

			for (IoSession client : clonedClientSessions) {
				ServerResponse response = new ServerResponse();
				Ball[] balls = new Ball[ballData.size()];
				ballData.values().toArray(balls);
				response.setBalls(balls);
				client.write(response);
			}

			try {
				sleep(100);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
