package main.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import main.AntArt;
import main.ant.Ant;
import main.ant.Direction;
import main.ant.Step;
import main.graphics.cells.Cell;
import main.graphics.cells.Cells;
import main.graphics.windows.DrawWindow;
import main.patterns.Pattern;
import main.patterns.Presets;

public class Render {

	public Color pixels[][];
	private int width;
	private int height;
	private int extraX;
	private int extraY;
	private DrawWindow dw;
	public Cells cells;
	private Pattern lastPattern;
	private int GPF = 10000;
	private boolean isResize = false;
	private BigInteger generationCount;
	
	private static boolean isConstant = false;
	private static boolean isSeizure = false;
	private static boolean isRandomPattern = false;
	private static boolean isRandomPreset = true;
	private static boolean isRandomColors = false;
	private static boolean isLimited = false;
	private static int limit = -1;

	// Tree Stuff
	private List<Ant> ants = new ArrayList<Ant>();

	public Render(DrawWindow dw, int width, int height) {
		this.dw = dw;
		this.width = width;
		this.height = height;
		this.extraX = 0;
		this.extraY = 0;
		pixels = new Color[this.width][this.height];
		cells = new Cells(this.width, this.height);
		generationCount = new BigInteger("0");

		this.lastPattern = Presets.getBasic(false);
	}

	public void render() {
		doExtras();
		setAll(dw.getBackgroundColor());
		renderRawPixles();

		drawFrame();
	}

	private void doExtras() {
		if (isConstant && dw.isFocus()) genNewAntAtMouse();
		if (isSeizure) cells.setDefaultCellColor(randomColor());
	}

	public static Color randomColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}
	
	private Direction randomDirection() {
		return null; //TODO
	}
	
	private Direction randomDirectionLR() {
		return null; //TODO
	}
	
	private Pattern randomPattern(int maxSteps) {
		List<Step> steps = new ArrayList<Step>();
		int stepNum = (int) (maxSteps * Math.random());
		
		steps.add(new Step(cells.getDefaultCellColor(), randomDirectionLR()));
		
		for (int i = 0; i < stepNum; i++)
			steps.add(new Step(randomColor(), randomDirection()));
		
		return new Pattern(steps);
	}

	

	private Pattern randomPresetPattern() {
		return Presets.getRandom(isRandomColors);
	}
	
	private void drawFrame() {
		dw.setPixels(dw.to1DArray(pixels));
	}

	private void renderRawPixles() {
		calculateAnts(this.ants.toArray());
		renderCells();
		// renderAnts();
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
			g.drawString("Mouse X: " + DrawWindow.dwm.mouseX, tlc, (int) (tlc * 6.5));
			g.drawString("Mouse Y: " + DrawWindow.dwm.mouseY, tlc, (int) (tlc * 7.5));
			
			g.drawString("FPS:  " + FPS, tlc, (int) (tlc * 9.5));
			g.drawString("Generations/Frame:  " + GPF, tlc, (int) (tlc * 10.5));

			g.drawString("Ant Count: " + ants.size(), tlc, (int) (tlc * 11.5));
			g.drawString("Ganeration: " + generationCount, tlc, (int) (tlc * 12.5));

			g.drawString("Constant Ants: " + isConstant, tlc, (int) (tlc * 13.5));
			
		}
	}

	private void calculateAnts(Object[] ants) {
		for (int i = 0; i < GPF; i++)
			for (int j = 0; j < ants.length; j++) {
				((Ant) ants[j]).renderNext(this);
				generationCount = generationCount.add(new BigInteger("1"));
			}
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

	public void zoom(Ant a) {
		if (isResize)
			dw.zoomOut();
		else if (a.equals(null))
			return;
		else
			removeAnt(a);
	}

	public void removeAnt(Ant a) {
		ants.remove(a);
	}
	
	
	public void expandCells(int zoom) {
		Color[][] oldPix = pixels;
		double ratio = oldPix.length / oldPix[0].length;
		extraX += zoom * ratio;
		extraY += zoom;

		ajustAnts(zoom, ratio);
		pixels = new Color[(int) (oldPix.length + (zoom * ratio * 2))][oldPix[0].length + zoom * 2];

		for (int x = 0; x < pixels.length; x++)
			for (int y = 0; y < pixels[x].length; y++)
				pixels[x][y] = Cells.defaultCell.getColor();

		for (int x = zoom; x < oldPix.length; x++)
			for (int y = zoom; y < oldPix[x].length; y++)
				pixels[x][y] = oldPix[x - zoom][y - zoom];

		cells.expandCells(zoom);
	}

	private void ajustAnts(int zoom, double ratio) {
		for (Ant a : ants) {
			a.setX((int) (a.getX() + (zoom * ratio)));
			a.setY(a.getY() + zoom);
		}
	}
	
	private void genNewAntAtMouse() {
		genNewAnt(dw.getMouse().x, dw.getMouse().y);
	}

	public void genNewAntInCenter(Render r) {
		genNewAnt(r.getCenterX(), r.getCenterY());
	}

	public void genNewAnt(int x, int y) {
		if (isRandomPreset) lastPattern = randomPresetPattern();
		genNewAnt(lastPattern, x, y);
	}

	public void genNewAnt(Pattern p, int x, int y) {
		int calculatedX = ((pixels.length / DrawWindow.width) * x);
		int calculatedY = ((pixels[0].length / DrawWindow.height) * y);

		if (!isLimited || (isLimited && ants.size() <= limit)) ants.add(new Ant(p, calculatedX, calculatedY));
	}

	public void clearAnt(Ant a) {
		ants.remove(a);
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

	public static boolean isConstant() {
		return isConstant;
	}

	public static void setConstant(boolean isConstant) {
		Render.isConstant = isConstant;
	}
}
