package net.todd.games.balls.client.ui.j3d;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.todd.common.uitools.IListener;
import net.todd.games.balls.client.ui.IMainView;
import net.todd.games.balls.common.Ball;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class MainViewIn3D implements IMainView {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final JFrame frame;

    public MainViewIn3D() {
	log.info("3D view is not yet supported... but just imagine how cool it would be");

	frame = new JFrame();
	frame.setSize(new Dimension(300, 300));
	frame.setLocation(100, 100);

	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	Canvas3D canvas3D = new Canvas3D(config);
	canvas3D.setFocusable(true);
	canvas3D.requestFocus();

	SimpleUniverse su = new SimpleUniverse(canvas3D);
	su.getViewingPlatform().setNominalViewingTransform();
	su.getViewer().getView().setMinimumFrameCycleTime(5);

	su.addBranchGraph(createScene());

	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new BorderLayout());
	mainPanel.add(canvas3D, BorderLayout.CENTER);

	Container c = frame.getContentPane();
	c.setLayout(new BorderLayout());
	c.add(mainPanel, BorderLayout.CENTER);
    }

    private BranchGroup createScene() {
	BranchGroup bg = new BranchGroup();

	// TODO: something goes here...

	return bg;
    }

    public void addClosingListener(final IListener listener) {
	frame.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		listener.fireEvent();
	    }
	});
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
	frame.setVisible(true);
    }

}
