package net.todd.games.balls.common;

import java.io.Serializable;

public class Ball implements Serializable {
	private static final long serialVersionUID = -7475994381301386151L;

	private int colorRed;
	private int colorGreen;
	private int colorBlue;
	private float positionX;
	private float positionY;
	private float positionZ;
	private String id;

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
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
		return "Ball[" + id + "]: Color: {" + colorRed + ", " + colorGreen + ", "
		        + colorBlue + "}, Position: {" + positionX + ", " + positionY + "}";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPositionZ(float positionZ) {
		this.positionZ = positionZ;
	}

	public float getPositionZ() {
		return positionZ;
	}

	@Override
	public Ball clone() {
		Ball clone = new Ball();

		clone.id = this.id;
		clone.colorRed = this.colorRed;
		clone.colorGreen = this.colorGreen;
		clone.colorBlue = this.colorBlue;
		clone.positionX = this.positionX;
		clone.positionY = this.positionY;
		clone.positionZ = this.positionZ;

		return clone;
	}
}
