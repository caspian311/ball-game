package net.todd.games.balls.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.InetSocketAddress;
import java.util.Map;

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

public class BallClientTest {
    private boolean clientConnected;
    private boolean clientDisconnected;
    private SocketAcceptor acceptor;

    @Before
    public void setUp() throws Exception {
	acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors());

	acceptor.getFilterChain().addFirst("logger", new LoggingFilter());
	acceptor.getFilterChain().addLast("codec",
	        new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

	acceptor.setHandler(new IoHandlerAdapter() {
	    @Override
	    public void sessionOpened(IoSession session) throws Exception {
		clientConnected = true;
	    }

	    @Override
	    public void sessionClosed(IoSession session) throws Exception {
		clientDisconnected = true;
	    }
	});

	acceptor.getSessionConfig().setReadBufferSize(2048);
	acceptor.setCloseOnDeactivation(true);

	acceptor.bind(new InetSocketAddress(9898));
	clientConnected = false;
	clientDisconnected = false;
    }

    @Test
    public void testClientConnectsToServer() throws Exception {
	assertFalse(clientConnected);
	new BallClient();
	Thread.sleep(100);
	assertTrue(clientConnected);
    }

    @Test
    public void testClientIsConnected() throws Exception {
	assertFalse(clientConnected);
	BallClient client = new BallClient();
	assertFalse(client.isConnected());
	while (!clientConnected)
	    ;
	Thread.sleep(10);
	assertTrue(client.isConnected());
    }

    @Test
    public void testCloseConnection() throws Exception {
	BallClient client = new BallClient();
	Thread.sleep(100);
	assertFalse(clientDisconnected);
	client.closeConnection();
	Thread.sleep(100);
	assertTrue(clientDisconnected);
    }

    @Test
    public void testCloseConnectionWhenNoConnection() throws Exception {
	tearDown();

	BallClient client = new BallClient();
	client.closeConnection();
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
