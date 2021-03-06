package net.todd.games.balls.client.ui.j3d;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import net.todd.games.balls.client.ui.IMainView;
import net.todd.games.balls.client.ui.MainView;
import net.todd.games.balls.common.Ball;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class MainViewIn3D extends MainView implements IMainView {
	private static final Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	private static final Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	private static final Color3f specular = new Color3f(0.9f, 0.9f, 0.9f);

	private Bounds bounds;
	private final List<String> allBalls;
	private final Map<String, TransformGroup> ballGraphs;
	private BranchGroup allBallsBG;

	public MainViewIn3D() {
		super();

		allBalls = new ArrayList<String>();
		ballGraphs = new HashMap<String, TransformGroup>();
	}

	@Override
	public JPanel createMainPanel() {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		canvas3D.setFocusable(true);
		canvas3D.requestFocus();

		SimpleUniverse su = new SimpleUniverse(canvas3D);
		su.getViewingPlatform().setNominalViewingTransform();
		su.getViewer().getView().setMinimumFrameCycleTime(5);

		bounds = new BoundingSphere(new Point3d(0, 0, 0), 100);

		su.addBranchGraph(createScene());

		createCamera(su);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(canvas3D, BorderLayout.CENTER);

		return mainPanel;
	}

	private BranchGroup createScene() {
		BranchGroup bg = new BranchGroup();

		lightScene(bg);
		createGameGrid(bg);
		createBallGroup(bg);
		bg.compile();

		return bg;
	}

	private void createBallGroup(BranchGroup bg) {
		allBallsBG = new BranchGroup();
		allBallsBG.setCapability(Group.ALLOW_CHILDREN_EXTEND);

		bg.addChild(allBallsBG);
	}

	private void createGameGrid(BranchGroup bg) {
		Shape3D grid = new Shape3D();

		Point3f[] points = new Point3f[84];
		int index = 0;
		for (int i = -10; i <= 10; i++) {
			float zPos = i;
			points[index * 2] = new Point3f(-10.0f, 0.0f, zPos);
			points[index * 2 + 1] = new Point3f(10.0f, 0.0f, zPos);
			index++;
		}

		for (int i = -10; i <= 10; i++) {
			float xPos = i;
			points[index * 2] = new Point3f(xPos, 0.0f, -10.0f);
			points[index * 2 + 1] = new Point3f(xPos, 0.0f, 10.0f);
			index++;
		}

		LineArray gridGeometry = new LineArray(points.length, GeometryArray.COORDINATES
		        | GeometryArray.COLOR_3);
		gridGeometry.setCoordinates(0, points);

		for (int i = 0; i < points.length; i++) {
			gridGeometry.setColor(i, new Color3f(1.0f, 1.0f, 1.0f));
		}

		Appearance gridAppearance = new Appearance();
		LineAttributes lineAttributes = new LineAttributes();
		lineAttributes.setLineWidth(1.0f);
		gridAppearance.setLineAttributes(lineAttributes);

		grid.setGeometry(gridGeometry);
		grid.setAppearance(gridAppearance);

		bg.addChild(grid);
	}

	private void createCamera(SimpleUniverse su) {
		ViewingPlatform vp = su.getViewingPlatform();
		TransformGroup tg = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		tg.getTransform(t3d);
		t3d.lookAt(new Point3d(0.0, 10.0, -25.0), new Point3d(0.0, 0.0, 0.0),
		        new Vector3d(0, 1, 0));
		t3d.invert();
		tg.setTransform(t3d);
	}

	private void addBall(Ball ball) {
		Appearance ballAppearance = new Appearance();

		Color3f ballColor = ball.getColor().getColor3f();

		Material blueMat = new Material(black, ballColor, black, specular, 128.0f);
		ballAppearance.setMaterial(blueMat);

		Sphere sphere = new Sphere(0.5f, 1, 50, ballAppearance);
		sphere.setCapability(Sphere.ENABLE_APPEARANCE_MODIFY);

		BranchGroup bBG = new BranchGroup();
		Transform3D t3d = new Transform3D();
		t3d.set(new Vector3f(ball.getPositionX(), ball.getPositionY(), ball
		        .getPositionZ()));
		TransformGroup tg = new TransformGroup(t3d);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(sphere);
		bBG.addChild(tg);

		allBallsBG.addChild(bBG);

		allBalls.add(ball.getId());
		ballGraphs.put(ball.getId(), tg);
	}

	private void lightScene(BranchGroup bg) {
		AmbientLight ambient = new AmbientLight(white);
		ambient.setInfluencingBounds(bounds);
		bg.addChild(ambient);

		Vector3f direction1 = new Vector3f(-1.0f, -1.0f, -1.0f);
		DirectionalLight direct1 = new DirectionalLight(white, direction1);
		direct1.setInfluencingBounds(bounds);
		bg.addChild(direct1);
	}

	public void setBallPositions(Ball[] ballData) {
		if (ballData != null) {
			for (Ball ball : ballData) {
				if (!allBalls.contains(ball.getId())) {
					addBall(ball);
				} else {
					updateBall(ball);
				}
			}

			List<String> slatedForDeletion = new ArrayList<String>();
			for (String ballId : allBalls) {
				for (Ball ball : ballData) {
					if (!allBalls.contains(ball.getId())) {
						slatedForDeletion.add(ballId);
					}
				}
			}

			for (String ballId : slatedForDeletion) {
				allBalls.remove(ballId);
				ballGraphs.remove(ballId);
			}
		}
	}

	private void updateBall(Ball ball) {
		TransformGroup tg = ballGraphs.get(ball.getId());
		Transform3D t3d = new Transform3D();
		tg.getTransform(t3d);

		t3d.set(new Vector3f(ball.getPositionX(), ball.getPositionY(), ball
		        .getPositionZ()));
		tg.setTransform(t3d);
	}
}
