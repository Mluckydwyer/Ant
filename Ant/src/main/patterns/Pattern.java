package main.patterns;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.AntArt;
import main.ant.Direction;
import main.ant.Step;


public class Pattern {

	private String name;
	private List<Step> steps;
	
	public Pattern() {
		this.steps = new ArrayList<Step>();
	}
	
	public Pattern(String name, List<Step> steps) {
		this(steps);
		this.name = name;
	}
	
	public Pattern(List<Step> steps) {
		this.steps = steps;
	}

	public Direction getDirection(Color c) {
		try {
			return steps.get(findColor(c)).getDirection();
		}
		catch(Exception e) {
			if (AntArt.isDebug()) e.printStackTrace();
		}
		
		return null;
		}
	
	public Color getNextColor(Color c) {
		int i = findColor(c);
		
		if (i + 1 < steps.size()) return steps.get(i + 1).getColor();
		else return steps.get(0).getColor();
	}
	
	private int findColor(Color c) {
		Iterator<Step> it = steps.iterator();
		int i = 0;
		
		while(it.hasNext()) {
			if (it.next().getColor().equals(c)) return i;
			i++;
		}
		
		for(int j = 0; j < steps.size(); j++)
			if (steps.get(j).getColor().equals(c)) return j;
		
		return -1;
	}

	public String toString() {
		return (!name.isEmpty() ? name : "");
	}
	
}
