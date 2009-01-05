package net.todd.games.balls.client.ui.j3d;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.client.ui.IMainView;
import net.todd.games.balls.common.Ball;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainViewIn3D implements IMainView {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public MainViewIn3D() {
	log.info("3D view is not yet supported... but just imagine how cool it would be");
    }

    public void addClosingListener(IListener listener) {
    }

    public void addColorChangeListener(IListener listener) {
    }

    public void addDownKeyListener(IListener listener) {
    }

    public void addLeftKeyListener(IListener listener) {
    }

    public void addRightKeyListener(IListener listener) {
    }

    public void addUpKeyListener(IListener listener) {
    }

    public String getSelectedColor() {
	return null;
    }

    public void setBallPositions(Ball[] ballData) {
    }

    public void showView() {
    }

}
