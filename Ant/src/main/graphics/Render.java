package main.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.AntArt;
import main.ant.Ant;
import main.graphics.cells.Cells;
import main.graphics.windows.DrawWindow;
import main.patterns.Pattern;
import main.patterns.Presets;

public class Render {

	public Color pixels[][];
	private int width;
	private int height;
	private DrawWindow dw;
	private Cells cells;
	private Pattern lastPattern;
	private int GPS = 10000;

	// Tree Stuff
	private List<Ant> ants = new ArrayList<Ant>();

	public Render(DrawWindow dw, int width, int height) {
		this.dw = dw;
		this.width = width;
		this.height = height;
		pixels = new Color[this.width][this.height];
		cells = new Cells(this.width, this.height);

		this.lastPattern = Presets.getSquare();
	}

	public void render(Graphics g) {
		setAll(dw.getBackgroundColor());
		renderRawPixles();

		drawFrame();
	}

	private void drawFrame() {
		dw.setPixels(dw.to1DArray(pixels));
	}

	private void renderRawPixles() {
		for (int i = 0; i < GPS; i++)
			calculateAnts(this.ants.toArray());

		renderCells();
		renderAnts();
	}

	private void setAll(Color color) {
		for (int x = 0; x < pixels.length; x++)
			for (int y = 0; y < pixels[x].length; y++)
				pixels[x][y] = color;
	}

	public void renderOverlay(Graphics g, long FPS) {
		// Info
		if (AntArt.isDrawInfo()) {
			int tlc = 15; // Top Left Corner
			g.setColor(Color.GREEN);
			g.drawString("Version:  " + AntArt.getVersion(), tlc, (int) (tlc * 3.5));
			g.drawString("Last Mouse Click X: " + DrawWindow.dwm.lastClickX, tlc, (int) (tlc * 4.5));
			g.drawString("Last Mouse Clic Y: " + DrawWindow.dwm.lastClickY, tlc, (int) (tlc * 5.5));

			g.drawString("FPS:  " + FPS, tlc, (int) (tlc * 7.5));
			g.drawString("Generations/Second:  " + GPS, tlc, (int) (tlc * 8.5));

			g.drawString("Ant Count: " + ants.size(), tlc, (int) (tlc * 10.5));

		}
	}

	private void calculateAnts(Object[] ants) {
		for (Object a : ants)
			((Ant) a).renderNext(cells);
	}

	private void renderAnts() {
		for (Ant a : ants)
			pixels[a.getX()][a.getY()] = a.getAntColor();
	}

	public void renderCells() {
		for (int x = 0; x < pixels.length; x++)
			for (int y = 0; y < pixels[x].length; y++)
				pixels[x][y] = cells.getCell(x, y).getColor();
	}

	public void genNewAntInCenter(Render r) {
		ants.add(new Ant(r));
	}

	public void genNewAnt(int x, int y) {
		genNewAnt(lastPattern, x, y);
	}

	public void genNewAnt(Pattern p, int x, int y) {
		ants.add(new Ant(p, x, y));
	}

	public void clearAnts() {
		ants.clear();
	}

	public void clear() {
		for (int x = 0; x < pixels.length; x++)
			for (int y = 0; y < pixels[x].length; y++)
				pixels[x][y] = dw.getBackgroundColor();
	}

	public void terminate() {
		AntArt.running = false;
	}

	public int getCenterX() {
		return (int) pixels.length / 2;
	}

	public int getCenterY() {
		return (int) pixels[0].length / 2;
	}
}
