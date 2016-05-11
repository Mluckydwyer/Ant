package main.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import main.AntArt;
import main.graphics.ant.Ant;
import windows.DrawWindow;

public class Render {

	public Color pixels[][];
	private int width;
	private int height;
	private DrawWindow dw;

	// Tree Stuff
	private Queue<Ant> ants = new LinkedList<Ant>();

	public Render(DrawWindow dw, int width, int height) {
		this.dw = dw;
		this.width = width;
		this.height = height;
		pixels = new Color[this.width][this.height];
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

	}

	private void setAll(Color color) {
		for (int x = 0; x < pixels.length; x++) {
			for (int y = 0; y < pixels[x].length; y++) {
				pixels[x][y] = color;
			}
		}
	}

	public void renderOverlay(Graphics g, long FPS) {
		// Info
		if (AntArt.isDrawInfo()) {
			int tlc = 15; // Top Left Corner
			g.setColor(Color.MAGENTA);
			g.drawString("Version:  " + AntArt.getVersion(), tlc, (int) (tlc * 3.5));
			g.drawString("FPS:  " + FPS, tlc, (int) (tlc * 4.5));
			g.drawString("Mouse X: " + DrawWindow.dwm.lastClickX, tlc, (int) (tlc * 5.5));
			g.drawString("Mouse Y: " + DrawWindow.dwm.lastClickY, tlc, (int) (tlc * 6.5));
		}
	}

	public void renderAnts(Graphics g) {
		for (Ant a : ants)
			a.renderNext(g);
	}

	public void genNewAntInCenter(Render r) {
		ants.add(new Ant(r));
	}

	public void genNewAnt(int x, int y) {
		ants.add(new Ant(x, y));
	}

	public void clearAnts() {
		ants.clear();
	}

	public void clear() {
		for (Color[] colorArray : pixels)
			for (Color c : colorArray)
				c = dw.getBackgroundColor();
	}

	public void terminate() {
		AntArt.running = false;
	}

	public int getCenterX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCenterY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
