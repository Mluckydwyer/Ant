package main.ant;

import java.awt.Color;

public class Step {

	private Color		c;
	private Direction	d;

	public Step(Color c, Direction d) {
		setColor(c);
		setDirection(d);
	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public Direction getDirection() {
		return d;
	}

	public void setDirection(Direction d) {
		this.d = d;
	}

	public String toString() {
		return "Direction: " + getDirection() + " - Color: [R=" + getColor().getRed() + ", G=" + getColor().getGreen() + ", B=" + getColor().getBlue() + ", A=" + getColor().getAlpha() + "] int="
				+ getColor().getRGB();
	}

}
