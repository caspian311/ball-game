package net.todd.games.balls.client.ui;

import javax.swing.JComboBox;

public class ColorPickerView extends PickerView {
    public String getSelectedColor() {
	return (String) getSelection();
    }

    @Override
    String getFrameTitle() {
	return "Pick a color...";
    }

    @Override
    String getLabelText() {
	return "Color:";
    }

    @Override
    void populateDropDownMenu(JComboBox dropDownMenu) {
	dropDownMenu.addItem("White");
	dropDownMenu.addItem("Red");
	dropDownMenu.addItem("Orange");
	dropDownMenu.addItem("Yellow");
	dropDownMenu.addItem("Green");
	dropDownMenu.addItem("Blue");
	dropDownMenu.addItem("Purple");
	dropDownMenu.addItem("Black");
    }
}
