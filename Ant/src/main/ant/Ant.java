package main.ant;

import java.awt.Color;
import java.awt.Graphics;

import main.graphics.Render;
import main.graphics.cells.Cells;
import main.patterns.Pattern;

public class Ant {

	private int x;
	private int y;
	private Pattern p;
	private boolean absolute;

	public Ant(Render r) {
		this(r.getCenterX(), r.getCenterY());
	}

	public Ant(int x, int y) {
		this(new Pattern(), x, y);
	}

	public Ant(Pattern p, int x, int y) {
		this(p, x, y, false);
	}
	
	public Ant(Pattern p, int x, int y, boolean ab) {
		this.setP(p);
		this.setAbsolute(ab);
		this.x = x;
		this.y = y;
	}

	public Color next(Color c) {
		switch (p.getDirection(c)) {
			case right:
				x++;
			break;
			
			case left:
				x--;
				break;
				
			case up:
				y++;
				break;
				
			case down:
				y--;
				break;
		}
		
		return p.getNextColor(c);
	}
	
	public void renderNext(Graphics g) {
		Next(Cells.getCell(getX(), getY()).get);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Pattern getP() {
		return p;
	}

	public void setP(Pattern p) {
		this.p = p;
	}

	public boolean isAbsolute() {
		return absolute;
	}

	public void setAbsolute(boolean absolute) {
		this.absolute = absolute;
	}

}
