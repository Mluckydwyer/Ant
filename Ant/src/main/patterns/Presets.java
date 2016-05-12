package main.patterns;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.ant.Direction;
import main.ant.Step;
import main.graphics.cells.Cells;

public class Presets {

	private static List<Step> basic;
	private static Pattern BASIC;

	private static List<Step> holiday;
	private static Pattern HOLIDAY;

	public Presets() {
		// Basic
		basic = new ArrayList<Step>();
		basic.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
		basic.add(new Step(Color.GREEN, Direction.LEFT));

		BASIC = new Pattern(basic);

		// Holiday
		holiday = new ArrayList<Step>();
		holiday.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
		holiday.add(new Step(Color.GREEN, Direction.LEFT));
		holiday.add(new Step(Color.RED, Direction.RIGHT));

		HOLIDAY = new Pattern(holiday);

		// Other Presets
	}

	public static Pattern getBASIC() {
		// Basic
		basic = new ArrayList<Step>();
		basic.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
		basic.add(new Step(Color.GREEN, Direction.LEFT));

		BASIC = new Pattern(basic);

		return BASIC;
	}

	public static Pattern getHOLIDAY() {
		return HOLIDAY;
	}

}
