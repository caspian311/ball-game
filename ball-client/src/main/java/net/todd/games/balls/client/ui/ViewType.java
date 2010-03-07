package net.todd.games.balls.client.ui;

public enum ViewType {
    SIMPLE_VIEW("2D"), COOL_VIEW("3D");

    private String name;

    private ViewType(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return name;
    }
}
