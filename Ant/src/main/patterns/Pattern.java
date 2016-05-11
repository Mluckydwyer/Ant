package main.patterns;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

import main.ant.Direction;
import main.ant.Step;


public class Pattern {

	private LinkedList<Step> steps;
	
	public Pattern() {
		this.steps = new LinkedList<Step>();
	}
	
	public Pattern(LinkedList<Step> steps) {
		this.steps = steps;
	}

	public Direction getDirection(Color c) {
		return steps.get(findColor(c)).getDirection();
	}
	
	public Color getNextColor(Color c) {
		return steps.get(findColor(c) + 1).getColor();
	}
	
	private int findColor(Color c) {
		Iterator<Step> it = steps.iterator();
		
		for (int i = 0; it.hasNext(); i++) {
			if (it.next().equals(c)) return i;
		}
		
		return -1;
	}
	
}
