package main.ant;

import java.awt.Color;
import java.awt.Point;

import main.graphics.Render;
import main.graphics.cells.Cell;
import main.patterns.Pattern;
import main.patterns.Presets;

public class Ant {

	private int x;
	private int y;
	private Pattern pattern;
	private Direction direction;
	private Color antColor;
	private static Pattern defaultPattern = Presets.getBasic();
	private boolean absolute;
	private boolean vertical;

	public Ant(Render r) {
		this(r.getCenterX(), r.getCenterY());
	}

	public Ant(int x, int y) {
		this(defaultPattern, x, y);
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
				if (direction.equals(Direction.UP) || direction.equals(Direction.RIGHT))
					moveValue = 1;
				else
					moveValue = -1;

				if (isVertical())
					setX(getX() + moveValue);
				else
					setY(getY() + moveValue);
				break;

			case LEFT :
				if (direction.equals(Direction.DOWN) || direction.equals(Direction.LEFT))
					moveValue = 1;
				else
					moveValue = -1;

				if (isVertical())
					setX(getX() + moveValue);
				else
					setY(getY() + moveValue);
				break;

			case UP :
				if (direction.equals(Direction.DOWN) || direction.equals(Direction.RIGHT))
					moveValue = 1;
				else
					moveValue = -1;

				if (isVertical())
					setY(getY() + moveValue);
				else
					setX(getX() + moveValue);
				break;

			case DOWN :
				if (direction.equals(Direction.UP) || direction.equals(Direction.LEFT))
					moveValue = 1;
				else
					moveValue = -1;

				if (isVertical())
					setY(getY() + moveValue);
				else
					setX(getX() + moveValue);
				break;
		}

		if (isAbsolute())
			direction = Direction.UP;
		else {
			if (dir == Direction.RIGHT)
				setDirection(nextRight(direction));
			else if (dir == Direction.LEFT)
				setDirection(nextRight(direction, 3));
			else if (dir == Direction.DOWN)
				setDirection(nextRight(direction, 2));
		}

		return pattern.getNextColor(color);
	}

	public void renderNext(Render render) {

		if(getX() >= render.cells.getCells().length || getX() < 0 || getY() >= render.cells.getCells()[1].length || getY() < 0)
			render.zoom();
		
		Point prevousPosition = new Point(getX(), getY());
		Color nextColor = next(render.cells.getCell(getX(), getY()).getColor());
		render.cells.setCell(new Cell(nextColor), (int) prevousPosition.getX(), (int) prevousPosition.getY());
	}

	public void terminate() {

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
