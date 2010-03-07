package net.todd.games.balls.client.ui;

import net.todd.games.balls.client.ui.j3d.MainViewIn3D;

public class ViewFactory {

	public static IMainView getView(ViewType viewType) {
		IMainView view = null;

		if (viewType == ViewType.SIMPLE_VIEW) {
			view = new MainView2D();
		} else if (viewType == ViewType.COOL_VIEW) {
			view = new MainViewIn3D();
		}

		return view;
	}

}
