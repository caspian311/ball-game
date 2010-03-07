package net.todd.games.balls.client.ui;

import javax.swing.JComboBox;

public class ViewPickerView extends PickerView implements IViewPickerView {
	public ViewType getSelectedViewType() {
		return (ViewType) getSelection();
	}

	@Override
	String getFrameTitle() {
		return "Pick a view...";
	}

	@Override
	String getLabelText() {
		return "View:";
	}

	@Override
	void populateDropDownMenu(JComboBox dropDownMenu) {
		for (ViewType viewType : ViewType.values()) {
			dropDownMenu.addItem(viewType);
		}
	}
}
