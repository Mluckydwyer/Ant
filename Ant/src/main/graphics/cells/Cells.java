package main.graphics.cells;

import java.awt.Color;

public class Cells {

	private Cell[][] cells;
	public static Cell defaultCell;
	
	public Cells(int x, int y) {
		cells = new Cell[x][y];
		defaultCell = new Cell(Color.GRAY);
		setAll(defaultCell);
	}
	
	public void expandCells(int i) {
		Cell[][] old = cells;
		cells = new Cell[old.length + i * 2][old[0].length + i * 2];
		
		for (int x = 0; x < cells.length; x++)
			for (int y = 0; y < cells[x].length; y++)
				cells[x][y] = defaultCell;
		
		for (int x = i; x < old.length; x++)
			for (int y = i; y < old[x].length; y++)
				cells[x][y] = old[x - i][y - i];
	}
	
	private void setAll(Cell cell) {
		for (int x = 0; x < cells.length; x++)
			for (int y = 0; y < cells[x].length; y++)
				cells[x][y] = cell;
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public void setCell(Cell cell, int x, int y) {
		cells[x][y] = cell;
	}
	
	public Cell[][] getCells() {
		return cells;
	}

}
