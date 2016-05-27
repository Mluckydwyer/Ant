package main.patterns;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.ant.Direction;
import main.ant.Step;
import main.graphics.Render;
import main.graphics.cells.Cells;

public class Presets {

	private static List<Pattern> presets;

	private static List<Color> colors;

	private static List<Step> basic;
	private static Pattern BASIC;

	private static List<Step> holiday;
	private static Pattern HOLIDAY;

	private static List<Step> symmetry;
	private static Pattern SYMMETRY;

	private static List<Step> square;
	private static Pattern SQUARE;

	private static List<Step> highway;
	private static Pattern HIGHWAY;

	private static List<Step> triangle;
	private static Pattern TRIANGLE;

	private static final Color green = Color.GREEN;
	private static final Color red = Color.RED;
	private static final Color orange = Color.ORANGE;
	private static final Color blue = Color.BLUE;
	private static final Color magenta = Color.MAGENTA;
	private static final Color yellow = Color.YELLOW;
	private static final Color cyan = Color.CYAN;
	private static final Color pink = Color.PINK;
	private static final Color gray = Color.GRAY;
	private static final Color purple = new Color(100, 0, 175);
	private static final Color brown = new Color(150, 100, 15);

	public static Pattern getBasic(boolean random) {
		basic = new ArrayList<Step>();
		if (!random) {
			basic.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
			basic.add(new Step(green, Direction.LEFT));
		}
		else {
			basic.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
			basic.add(new Step(Render.randomColor(), Direction.LEFT));
		}

		BASIC = new Pattern(basic);

		return BASIC;
	}

	public static Pattern getHoliday(boolean random) {
		holiday = new ArrayList<Step>();

		if (!random) {
			holiday.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
			holiday.add(new Step(green, Direction.LEFT));
			holiday.add(new Step(red, Direction.RIGHT));
		}
		else {
			holiday.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
			holiday.add(new Step(Render.randomColor(), Direction.LEFT));
			holiday.add(new Step(Render.randomColor(), Direction.RIGHT));
		}

		HOLIDAY = new Pattern(holiday);

		return HOLIDAY;
	}

	public static Pattern getSymmetry(boolean random) {
		symmetry = new ArrayList<Step>();

		if (!random) {
			symmetry.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
			symmetry.add(new Step(green, Direction.LEFT));
			symmetry.add(new Step(red, Direction.RIGHT));
			symmetry.add(new Step(orange, Direction.RIGHT));
		}
		else {
			symmetry.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
			symmetry.add(new Step(Render.randomColor(), Direction.LEFT));
			symmetry.add(new Step(Render.randomColor(), Direction.RIGHT));
			symmetry.add(new Step(Render.randomColor(), Direction.RIGHT));
		}

		SYMMETRY = new Pattern(symmetry);

		return SYMMETRY;
	}

	public static Pattern getSquare(boolean random) {
		square = new ArrayList<Step>();

		if (!random) {
			square.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
			square.add(new Step(green, Direction.RIGHT));
			square.add(new Step(red, Direction.RIGHT));
			square.add(new Step(orange, Direction.RIGHT));
			square.add(new Step(blue, Direction.RIGHT));
			square.add(new Step(magenta, Direction.RIGHT));
			square.add(new Step(yellow, Direction.LEFT));
			square.add(new Step(cyan, Direction.LEFT));
			square.add(new Step(pink, Direction.RIGHT));
		}
		else {
			square.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
			square.add(new Step(Render.randomColor(), Direction.RIGHT));
			square.add(new Step(Render.randomColor(), Direction.RIGHT));
			square.add(new Step(Render.randomColor(), Direction.RIGHT));
			square.add(new Step(Render.randomColor(), Direction.RIGHT));
			square.add(new Step(Render.randomColor(), Direction.RIGHT));
			square.add(new Step(Render.randomColor(), Direction.LEFT));
			square.add(new Step(Render.randomColor(), Direction.LEFT));
			square.add(new Step(Render.randomColor(), Direction.RIGHT));
		}

		SQUARE = new Pattern(square);

		return SQUARE;
	}

	public static Pattern getHighway(boolean random) {
		highway = new ArrayList<Step>();

		if (!random) {
			highway.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
			highway.add(new Step(green, Direction.LEFT));
			highway.add(new Step(red, Direction.RIGHT));
			highway.add(new Step(orange, Direction.RIGHT));
			highway.add(new Step(blue, Direction.RIGHT));
			highway.add(new Step(magenta, Direction.LEFT));
			highway.add(new Step(yellow, Direction.RIGHT));
			highway.add(new Step(cyan, Direction.LEFT));
			highway.add(new Step(pink, Direction.RIGHT));
			highway.add(new Step(gray, Direction.LEFT));
			highway.add(new Step(purple, Direction.LEFT));
			highway.add(new Step(brown, Direction.RIGHT));
		}
		else {
			highway.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
			highway.add(new Step(Render.randomColor(), Direction.LEFT));
			highway.add(new Step(Render.randomColor(), Direction.RIGHT));
			highway.add(new Step(Render.randomColor(), Direction.RIGHT));
			highway.add(new Step(Render.randomColor(), Direction.RIGHT));
			highway.add(new Step(Render.randomColor(), Direction.LEFT));
			highway.add(new Step(Render.randomColor(), Direction.RIGHT));
			highway.add(new Step(Render.randomColor(), Direction.LEFT));
			highway.add(new Step(Render.randomColor(), Direction.RIGHT));
			highway.add(new Step(Render.randomColor(), Direction.LEFT));
			highway.add(new Step(Render.randomColor(), Direction.LEFT));
			highway.add(new Step(Render.randomColor(), Direction.RIGHT));
		}

		HIGHWAY = new Pattern(highway);

		return HIGHWAY;
	}

	public static Pattern getTriangle(boolean random) {
		// RRLLLRLLLRRR
		triangle = new ArrayList<Step>();

		if (!random) {
			triangle.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
			triangle.add(new Step(green, Direction.RIGHT));
			triangle.add(new Step(red, Direction.LEFT));
			triangle.add(new Step(orange, Direction.LEFT));
			triangle.add(new Step(blue, Direction.LEFT));
			triangle.add(new Step(magenta, Direction.RIGHT));
			triangle.add(new Step(yellow, Direction.LEFT));
			triangle.add(new Step(cyan, Direction.LEFT));
			triangle.add(new Step(pink, Direction.LEFT));
			triangle.add(new Step(gray, Direction.RIGHT));
			triangle.add(new Step(purple, Direction.RIGHT));
			triangle.add(new Step(brown, Direction.RIGHT));
		}
		else {
			triangle.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
			triangle.add(new Step(Render.randomColor(), Direction.RIGHT));
			triangle.add(new Step(Render.randomColor(), Direction.LEFT));
			triangle.add(new Step(Render.randomColor(), Direction.LEFT));
			triangle.add(new Step(Render.randomColor(), Direction.LEFT));
			triangle.add(new Step(Render.randomColor(), Direction.RIGHT));
			triangle.add(new Step(Render.randomColor(), Direction.LEFT));
			triangle.add(new Step(Render.randomColor(), Direction.LEFT));
			triangle.add(new Step(Render.randomColor(), Direction.LEFT));
			triangle.add(new Step(Render.randomColor(), Direction.RIGHT));
			triangle.add(new Step(Render.randomColor(), Direction.RIGHT));
			triangle.add(new Step(Render.randomColor(), Direction.RIGHT));
		}

		TRIANGLE = new Pattern(triangle);

		return TRIANGLE;
	}

	public static List<Pattern> getPresets(boolean random) {
		presets = new ArrayList<Pattern>();

		presets.add(getBasic(random));
		presets.add(getHoliday(random));
		presets.add(getSymmetry(random));
		presets.add(getSquare(random));
		presets.add(getHighway(random));
		presets.add(getTriangle(random));

		return presets;
	}

	public static Pattern getRandomPreset(boolean random) {
		return getPresets(random).get((int) (Math.random() * (getPresets(random).size() - 1)));
	}

	public static List<Color> getColors() {
		colors = new ArrayList<Color>();

		colors.add(green);
		colors.add(red);
		colors.add(orange);
		colors.add(blue);
		colors.add(magenta);
		colors.add(yellow);
		colors.add(cyan);
		colors.add(pink);
		colors.add(gray);
		colors.add(purple);
		colors.add(brown);

		return colors;
	}

	public static Color getRandomColor(Color c) {
		Color color;

		do {
			color = getRandomColor();
		}
		while (color.equals(c));
		
		return color;
	}

	public static Color getRandomColor(List<Step> steps) {
		Color color;
		boolean good = true;

		do {
			good = true;
			color = getRandomColor();
			
			for (Step s:steps)
				if (s.getColor().equals(color)) good = false;
		}
		while (!good);
		
		return color;
	}
	
	public static Color getRandomColor() {
		return getColors().get((int) (Math.random() * (getColors().size() - 1)));
	}

}
