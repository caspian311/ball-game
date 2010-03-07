package net.todd.games.balls.client.ui;

import java.awt.BorderLayout;
import java.awt.Component;
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

public abstract class PickerView {
	private final ListenerManager listenerManager;
	private final ListenerManager cancelListenerManager;
	private final JFrame frame;
	private JComboBox dropDownMenu;

	public PickerView() {
		listenerManager = new ListenerManager();
		cancelListenerManager = new ListenerManager();

		frame = new JFrame(getFrameTitle());
		Container c = new Container();
		c.setLayout(new BorderLayout());

		c.add(createMainPanel(), BorderLayout.CENTER);
		c.add(createControlPanel(), BorderLayout.SOUTH);

		frame.getContentPane().add(c);

		frame.pack();
		frame.setVisible(true);
	}

	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(new Label(getLabelText()), BorderLayout.WEST);

		dropDownMenu = new JComboBox();
		populateDropDownMenu(dropDownMenu);

		mainPanel.add(dropDownMenu, BorderLayout.CENTER);

		return mainPanel;
	}

	abstract String getLabelText();

	abstract void populateDropDownMenu(JComboBox dropDownMenu);

	abstract String getFrameTitle();

	private Component createControlPanel() {
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
				cancelListenerManager.notifyListeners();
			}
		});

		panel.add(okButton);
		panel.add(cancelButton);

		return panel;
	}

	private void closePopup() {
		frame.setVisible(false);
	}

	public void addCancelListener(IListener listener) {
		cancelListenerManager.addListener(listener);
	}

	public void addChangeListener(IListener listener) {
		listenerManager.addListener(listener);
	}

	Object getSelection() {
		return dropDownMenu.getSelectedItem();
	}
}
