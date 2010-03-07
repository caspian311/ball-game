package net.todd.games.balls.common;

import java.io.Serializable;

public class Ball implements Serializable {
	private static final long serialVersionUID = -7475994381301386151L;

	private float positionX;
	private float positionY;
	private float positionZ;
	private String id;
	private BallColor color;

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

	@Override
	public String toString() {
		return "Ball[" + id + "]: Color: " + getColor().name() + ", Position: {"
		        + positionX + ", " + positionY + "}";
	}

	public BallColor getColor() {
		return color;
	}

	public void setColor(BallColor ballColor) {
		color = ballColor;
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
		clone.color = this.color;
		clone.positionX = this.positionX;
		clone.positionY = this.positionY;
		clone.positionZ = this.positionZ;

		return clone;
	}
}
