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
	
	private static List<Step> symmetry;
	private static Pattern SYMMETRY;
	
	private static List<Step> square;
	private static Pattern SQUARE;
	
	private static List<Step> highway;
	private static Pattern HIGHWAY;
	
	private static List<Step> triangle;
	private static Pattern TRIANGLE;

	public static Pattern getBasic() {
		basic = new ArrayList<Step>();
		
		basic.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
		basic.add(new Step(Color.GREEN, Direction.LEFT));

		BASIC = new Pattern(basic);

		return BASIC;
	}

	public static Pattern getHoliday() {
		holiday = new ArrayList<Step>();
		
		holiday.add(new Step(Cells.defaultCell.getColor(), Direction.RIGHT));
		holiday.add(new Step(Color.GREEN, Direction.LEFT));
		holiday.add(new Step(Color.RED, Direction.RIGHT));

		HOLIDAY = new Pattern(holiday);

		return HOLIDAY;
	}

	public static Pattern getSymmetry() {
		symmetry = new ArrayList<Step>();
		
		symmetry.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
		symmetry.add(new Step(Color.CYAN, Direction.LEFT));
		symmetry.add(new Step(Color.MAGENTA, Direction.RIGHT));
		symmetry.add(new Step(Color.GREEN, Direction.RIGHT));

		SYMMETRY = new Pattern(symmetry);

		return SYMMETRY;
	}
	
	public static Pattern getSquare() {
		square = new ArrayList<Step>();
		
		square.add(new Step(Cells.defaultCell.getColor(), Direction.LEFT));
		square.add(new Step(Color.CYAN, Direction.RIGHT));
		square.add(new Step(Color.MAGENTA, Direction.RIGHT));
		square.add(new Step(Color.GREEN, Direction.RIGHT));
		square.add(new Step(Color.ORANGE, Direction.RIGHT));
		square.add(new Step(Color.PINK, Direction.RIGHT));
		square.add(new Step(Color.RED, Direction.LEFT));
		square.add(new Step(Color.WHITE, Direction.LEFT));
		square.add(new Step(Color.YELLOW, Direction.RIGHT));

		SQUARE = new Pattern(square);

		return SQUARE;
	}
	
	public static Pattern getHighway() {
		// LLRRRLRLRLLR
		return HIGHWAY;
	}
	
	public static Pattern getTriangle() {
		// RRLLLRLLLRRR
		return TRIANGLE;
	}
	
}
