package main.ant;

import java.awt.Color;
import java.awt.Point;

import main.graphics.Render;
import main.graphics.cells.Cells;
import main.patterns.Pattern;

public class Ant {

	private int x;
	private int y;
	private Pattern pattern;
	private Direction direction;
	private Color antColor;
	private boolean absolute;
	private boolean vertical;

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
		this.setPattern(p);
		this.absolute = ab;
		this.x = x;
		this.y = y;
		this.antColor = Color.PINK;
		this.direction = Direction.UP;
		this.vertical = true;
	}

	private Direction nextRight(Direction d) {
		switch (d) {
			case RIGHT :
				return Direction.DOWN;

			case LEFT :
				return Direction.UP;

			case UP :
				return Direction.RIGHT;

			case DOWN :
				return Direction.LEFT;
		}

		return null;
	}
	
	private Direction nextRight(Direction d, int i) {
		Direction tempDir = d;
		
		for (int j = 0; j < i; j++) {
			tempDir = nextRight(tempDir);
		}
		
		return tempDir;
	}

	private Color next(Color color) {
		int moveValue;
		Direction dir = pattern.getDirection(color);

		switch (dir) {
			case RIGHT :
				if (direction == Direction.UP || direction == Direction.LEFT)
					moveValue = 1;
				else
					moveValue = -1;

				if (isVertical())
					x += moveValue;
				else
					y += moveValue;

			case LEFT :
				if (direction == Direction.DOWN || direction == Direction.RIGHT)
					moveValue = 1;
				else
					moveValue = -1;

				if (isVertical())
					x += moveValue;
				else
					y += moveValue;

			case UP :
				if (direction == Direction.UP || direction == Direction.RIGHT) moveValue = 1;
				else moveValue = -1;
				
				if (isVertical()) y += moveValue;
				else x += moveValue;

			case DOWN :
				if (direction == Direction.DOWN || direction == Direction.LEFT) moveValue = 1;
				else moveValue = -1;
				
				if (isVertical()) y += moveValue;
				else x += moveValue;
		}

		if (isAbsolute()) direction = Direction.UP;
		else {
			if (dir == Direction.RIGHT) direction = nextRight(direction);
			else if (dir == Direction.LEFT) direction = nextRight(direction, 3);
			else if (dir == Direction.DOWN) direction = nextRight(direction, 2);
		}
		
		return pattern.getNextColor(color);
	}

	public void renderNext(Cells cells) {
		Point prevousPosition = new Point(getX(), getY());
		Color nextColor = next(cells.getCell(getX(), getY()).getColor());

		cells.getCell((int) prevousPosition.getX(), (int) prevousPosition.getY()).setColor(nextColor);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public Color getAntColor() {
		return antColor;
	}
	
	public void setPattern(Pattern p) {
		this.pattern = p;
	}

	public void setDirection(Direction d) {
		direction = d;

		if (direction == Direction.UP || direction == Direction.DOWN)
			vertical = true;
		else
			vertical = false;
	}

	public boolean isAbsolute() {
		return absolute;
	}
	
	private boolean isVertical() {
		return vertical;
	}

}
