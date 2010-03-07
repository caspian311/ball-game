package net.todd.games.balls.common;

import java.awt.Color;

import javax.vecmath.Color3f;

public enum BallColor {
	Red(Color.red), Orange(Color.orange), Yellow(Color.yellow), Green(Color.green), Blue(
	        Color.blue), Purple(new Color(125, 0, 125)), White(Color.white), Black(
	        Color.black);

	private Color color;

	private BallColor(Color color) {
		this.color = color;
	}

	public Color3f getColor3f() {
		return new Color3f(color);
	}

	public Color getColor() {
		return color;
	}

	public static BallColor byName(String colorName) {
		BallColor target = null;
		for (BallColor color : values()) {
			if (colorName.equals(color.name())) {
				target = color;
				break;
			}
		}
		return target;
	}
}
