package net.todd.games.balls.client.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.todd.common.uitools.IListener;
import net.todd.common.uitools.ListenerManager;

public class ColorPickerView {
	private final JFrame frame;
	private final ListenerManager listenerManager;
	private JComboBox colorMenus;

	public ColorPickerView() {
		listenerManager = new ListenerManager();

		frame = new JFrame("Pick a color...");
		Container c = new Container();
		c.setLayout(new BorderLayout());

		c.add(createMainPanel(), BorderLayout.CENTER);
		c.add(createControlPanel(), BorderLayout.SOUTH);

		frame.getContentPane().add(c);

		frame.pack();
		frame.setVisible(true);
	}

	private JPanel createControlPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closePopup();			
				listenerManager.notifyListeners();
			}
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closePopup();
			}
		});

		panel.add(okButton);
		panel.add(cancelButton);

		return panel;
	}
	
	private void closePopup() {
		frame.setVisible(false);
	}

	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(new Label("Color:"), BorderLayout.WEST);

		colorMenus = new JComboBox();
		colorMenus.addItem("White");
		colorMenus.addItem("Red");
		colorMenus.addItem("Orange");
		colorMenus.addItem("Yellow");
		colorMenus.addItem("Green");
		colorMenus.addItem("Blue");
		colorMenus.addItem("Purple");
		colorMenus.addItem("Black");

		mainPanel.add(colorMenus, BorderLayout.CENTER);

		return mainPanel;
	}

	public void addChangeListener(IListener listener) {
		listenerManager.addListener(listener);
	}
	
	public String getSelectedColor() {
		return (String) colorMenus.getSelectedItem();
	}
}
