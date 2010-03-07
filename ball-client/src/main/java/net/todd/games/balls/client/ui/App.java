package net.todd.games.balls.client.ui;

import javax.swing.UIManager;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.client.BallClient;
import net.todd.games.balls.client.INetworkClient;
import net.todd.games.balls.client.NoNetworkClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);
	private static final String LOOK_AND_FEEL_CLASSNAME = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	public App() {
		final IViewPickerView view = new ViewPickerView();
		view.addChangeListener(new IListener() {
			public void fireEvent() {
				ViewType viewType = view.getSelectedViewType();
				createMainView(viewType);
			}
		});
		view.addCancelListener(new IListener() {
			public void fireEvent() {
				new SystemKiller(null).killSystem();
			}
		});
	}

	private void createMainView(ViewType viewType) {
		IMainView mainView = ViewFactory.getView(viewType);
		INetworkClient networkClient = setupNetwork();
		ISystemKiller systemKiller = new SystemKiller(networkClient);
		if (!networkClient.isConnected()) {
			log.error("No network available - switching to non-network mode");
			// systemKiller.killSystem();
			networkClient = new NoNetworkClient();
		}
		IMainModel mainModel = new MainModel(systemKiller, networkClient);
		new MainPresenter(mainView, mainModel);
	}

	private BallClient setupNetwork() {
		BallClient ballClient = new BallClient();
		boolean keepTrying = true;
		int count = 0;
		while (keepTrying && !ballClient.isConnected()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				keepTrying = false;
			}
			count++;
			if (count > 10) {
				keepTrying = false;
			}
		}
		return ballClient;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(LOOK_AND_FEEL_CLASSNAME);
		} catch (Exception e) {
			log.error("Can't use the nimbus look and feel");
		}

		new App();
	}
}
