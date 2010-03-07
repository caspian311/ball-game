package net.todd.games.balls.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import net.todd.games.balls.common.Ball;

public class MainView2D extends MainView implements IMainView {
    private BouncyBallComponent bouncyBallComponent;

    @Override
    public JPanel createMainPanel() {
	JPanel mainPanel = new JPanel();
	mainPanel.setBackground(Color.black);

	bouncyBallComponent = new BouncyBallComponent();
	bouncyBallComponent.setBackground(Color.white);
	bouncyBallComponent.setPreferredSize(new Dimension(300, 300));
	mainPanel.setLayout(new BorderLayout());
	mainPanel.add(bouncyBallComponent, BorderLayout.CENTER);

	return mainPanel;
    }

    public void setBallPositions(Ball[] ballData) {
	bouncyBallComponent.setBallPositions(ballData);
	bouncyBallComponent.repaint();
    }
}
