package main.graphics.cells;

import java.awt.Color;

import main.patterns.Pattern;

public class Cell {

	private Color	color;
	private Pattern	lastPattern;

	public Cell(Color color) {
		this(color, null);
	}

	public Cell(Color color, Pattern pattern) {
		setColor(color);
		setLastPattern(pattern);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Pattern getLastPattern() {
		return lastPattern;
	}

	public void setLastPattern(Pattern lastPattern) {
		this.lastPattern = lastPattern;
	}

}
