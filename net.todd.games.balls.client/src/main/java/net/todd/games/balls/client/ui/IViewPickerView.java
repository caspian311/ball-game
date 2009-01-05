package net.todd.games.balls.client.ui;

import net.todd.common.uitools.IListener;

public interface IViewPickerView {
    void addChangeListener(IListener listener);

    ViewType getSelectedViewType();
}
