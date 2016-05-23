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

	public static Pattern getBasic(boolean random) {
		basic = new ArrayList<Step>();
		if (!random) {
			basic.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
			basic.add(new Step(Color.GREEN, Direction.LEFT));
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
			holiday.add(new Step(Color.GREEN, Direction.LEFT));
			holiday.add(new Step(Color.RED, Direction.RIGHT));
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
			symmetry.add(new Step(Color.CYAN, Direction.LEFT));
			symmetry.add(new Step(Color.MAGENTA, Direction.RIGHT));
			symmetry.add(new Step(Color.GREEN, Direction.RIGHT));
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
			square.add(new Step(Color.CYAN, Direction.RIGHT));
			square.add(new Step(Color.MAGENTA, Direction.RIGHT));
			square.add(new Step(Color.GREEN, Direction.RIGHT));
			square.add(new Step(Color.ORANGE, Direction.RIGHT));
			square.add(new Step(Color.PINK, Direction.RIGHT));
			square.add(new Step(Color.RED, Direction.LEFT));
			square.add(new Step(Color.WHITE, Direction.LEFT));
			square.add(new Step(Color.YELLOW, Direction.RIGHT));
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
		// LLRRRLRLRLLR
		return HIGHWAY;
	}

	public static Pattern getTriangle(boolean random) {
		// RRLLLRLLLRRR
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

	public static Pattern getRandom(boolean random) {
		return getPresets(random).get((int) (Math.random() * (getPresets(random).size() - 1)));
	}
	
}
