package net.todd.games.balls.common;

import java.io.Serializable;

public class Ball implements Serializable {
	private static final long serialVersionUID = -7475994381301386151L;

	private int colorRed;
	private int colorGreen;
	private int colorBlue;
	private int positionX;
	private int positionY;

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getColorRed() {
		return colorRed;
	}

	public void setColorRed(int colorRed) {
		this.colorRed = colorRed;
	}

	public int getColorGreen() {
		return colorGreen;
	}

	public void setColorGreen(int colorGreen) {
		this.colorGreen = colorGreen;
	}

	public int getColorBlue() {
		return colorBlue;
	}

	public void setColorBlue(int colorBlue) {
		this.colorBlue = colorBlue;
	}
	
	@Override
	public String toString() {
		return "Color: {" + colorRed + ", " + colorGreen + ", " + colorBlue
				+ "}, Position: {" + positionX + ", " + positionY + "}";
	}
}
