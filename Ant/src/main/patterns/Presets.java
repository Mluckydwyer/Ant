package main.patterns;

import java.awt.Color;
import java.util.LinkedList;

import main.ant.Direction;
import main.ant.Step;

public class Presets {
	
	private static LinkedList<Step> basic = new LinkedList<Step>();
	public static final Pattern BASIC = new Pattern();
	
	public Presets() {
		// Basic
		basic.add(new Step(Color.GREEN, Direction.right));
		basic.add(new Step(Color.RED, Direction.left));
		
		//Other Presets
	}
	
}
