package net.todd.games.balls.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import net.todd.common.uitools.IListener;
import net.todd.common.uitools.ListenerManager;
import net.todd.games.balls.common.Ball;

public class MainView implements IMainView {
	private final JFrame mainFrame;
	private BouncyBallComponent bouncyBallComponent;
	private final ListenerManager colorChangedListenerManager;
	private ColorPickerView colorPickerView;

	public MainView() {
		colorChangedListenerManager = new ListenerManager();
		mainFrame = new JFrame();
		Container c = mainFrame.getContentPane();

		JPanel mainPanel = createMainPanel();
		mainFrame.setJMenuBar(createMenuBar());
		
		c.setLayout(new BorderLayout());
		c.add(mainPanel, BorderLayout.CENTER);

		mainFrame.pack();
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu colorMenu = new JMenu("My Ball");
		JMenuItem pickColorMenuItem = new JMenuItem("Pick color...");
		pickColorMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				colorPickerView = new ColorPickerView();
				colorPickerView.addChangeListener(new IListener() {
					public void fireEvent() {
						colorChangedListenerManager.notifyListeners();
					}
				});
			}
		});
		colorMenu.add(pickColorMenuItem);
		menuBar.add(colorMenu);

		return menuBar;
	}
	
	public void addColorChangeListener(IListener listener) {
		colorChangedListenerManager.addListener(listener);
	}

	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.black);
		mainPanel.setPreferredSize(new Dimension(300, 300));

		bouncyBallComponent = new BouncyBallComponent();
		bouncyBallComponent.setBackground(Color.white);
		bouncyBallComponent.setPreferredSize(new Dimension(300, 300));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(bouncyBallComponent, BorderLayout.CENTER);

		return mainPanel;
	}

	public void showView() {
		mainFrame.setVisible(true);
	}

	public void addClosingListener(final IListener listener) {
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.fireEvent();
			}
		});
	}

	public void setBallPositions(Ball[] ballData) {
		bouncyBallComponent.setBallPositions(ballData);
		bouncyBallComponent.repaint();
	}

	public void addUpKeyListener(final IListener listener) {
		mainFrame.requestFocus();

		mainFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (38 == e.getKeyCode()) {
					listener.fireEvent();
				}
			}
		});
	}
	
	public void addDownKeyListener(final IListener listener) {
		mainFrame.requestFocus();

		mainFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (40 == e.getKeyCode()) {
					listener.fireEvent();
				}
			}
		});
	}

	public void addLeftKeyListener(final IListener listener) {
		mainFrame.requestFocus();

		mainFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (37 == e.getKeyCode()) {
					listener.fireEvent();
				}
			}
		});
	}

	public void addRightKeyListener(final IListener listener) {
		mainFrame.requestFocus();

		mainFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (39 == e.getKeyCode()) {
					listener.fireEvent();
				}
			}
		});
	}

	public String getSelectedColor() {
		return colorPickerView != null ? colorPickerView.getSelectedColor()
				: null;
	}
}
