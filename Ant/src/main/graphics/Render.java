package main.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.AntArt;
import main.ant.Ant;
import main.ant.Direction;
import main.ant.Step;
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
	private int GPF = 100000;
	private boolean isResize = false; // Major
										// Lag
	private BigInteger generationCount;

	private static boolean isConstant = true;
	private static boolean isSeizure = true;
	private static boolean isRandomPattern = false;
	private static boolean isRandomColors = false;
	private static boolean isRandomPresetColors = false;
	private static boolean isLimited = true;
	private Color lastSeizureColor;
	private static int limit = 25;
	private static int minSteps = 5;
	private static int maxSteps = 12;
	private int autoClearCount = 0;
	private int autoClear = 500;
	private int patternCycle = 0;

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

		// this.lastPattern = Presets.getPresets((isRandomColors() &&
		// !isRandomPresetColors ? true : false)).get(patternCycle);
		// else this.lastPattern = randomPattern(maxSteps);

		setSettings();
	}

	private void setSettings() {
		if (AntArt.isAuto()) {
			isConstant = false;
			isSeizure = false;
			isRandomPattern = true;
			setRandomColors(true);
			isRandomPresetColors = false;
			isLimited = false;
			;

			GPF = 100000;
			autoClear = 150;

			if (AntArt.isAutoScattered()) {
				isLimited = true;
				limit = 10;
				GPF = 25000;
				autoClear = 200;
			}
		}
	}

	private void auto() {
		Random rand = new Random();
		autoClearCount++;

		if (autoClearCount < autoClear) {
			if (AntArt.isAutoScattered())
				genNewAnt(randomPattern(maxSteps), rand.nextInt(width), rand.nextInt(height));
			else if (!AntArt.isAutoScattered() && autoClearCount == 1)
				genNewAntInCenter(this);
		} else if (autoClear <= autoClearCount) {
			clearAnts();
			cells.setAll(Cells.defaultCell);
			autoClearCount = 0;
			AntArt.setIsAutoScattered(!AntArt.isAutoScattered());
			setSettings();

			return;
		}
	}

	public void render() {
		if (AntArt.isAuto())
			auto();

		doExtras();
		setAllPixels(dw.getBackgroundColor());
		renderRawPixles();

		drawFrame();
	}

	private Color[][] seizure() {
		Color[][] pix = new Color[pixels.length][pixels[0].length];

		for (int x = 0; x < pix.length; x++) {
			for (int y = 0; y < pix[x].length; y++) {
				pix[x][y] = pixels[x][y];
			}
		}

		Color random = randomColor();
		lastSeizureColor = random;

		for (int x = 0; x < pix.length; x++) {
			for (int y = 0; y < pix[x].length; y++) {
				if (pix[x][y].equals(cells.getDefaultCellColor()) || pix[x][y].equals(lastSeizureColor))
					pix[x][y] = random;
			}
		}

		return pix;
	}

	private void doExtras() {
		if (isConstant && dw.isFocus())
			genNewAntAtMouse();
		if (isSeizure)
			cells.setDefaultCellColor(randomColor());
	}

	public void skip() {
		autoClearCount = autoClear;
	}

	public static Color randomColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	public static Color randomPresetColor(Color c) {
		return Presets.getRandomColor(c);
	}

	public static Color randomPresetColor(List<Step> steps) {
		return Presets.getRandomColor(steps);
	}

	private Direction randomDirection() {
		Random rand = new Random();

		if (rand.nextBoolean()) {
			if (rand.nextBoolean())
				return Direction.LEFT;
			return Direction.UP;
		} else if (rand.nextBoolean())
			return Direction.RIGHT;
		return Direction.DOWN;
	}

	private Direction randomDirectionLR() {
		Random rand = new Random();

		if (rand.nextBoolean())
			return Direction.LEFT;
		return Direction.RIGHT;
	}

	private Pattern randomPattern(int maxSteps) {
		List<Step> steps = new ArrayList<Step>();
		int stepNum = (int) (maxSteps * Math.random());
		if (stepNum < minSteps)
			stepNum = minSteps;

		steps.add(new Step(cells.getDefaultCellColor(), randomDirectionLR()));

		if (isRandomPresetColors) {
			for (int i = 0; i < stepNum; i++)
				steps.add(new Step(randomPresetColor(steps.get(steps.size() - 1).getColor()), randomDirectionLR()));
			// steps.add(new Step(randomPresetColor(steps.get(steps.size() -
			// 1).getColor()), randomDirectionLR()));
		} else {
			for (int i = 0; i < stepNum; i++)
				steps.add(new Step(randomColor(), randomDirectionLR()));
		}

		return new Pattern("Randomly Generated Pattern", steps);
	}

	private Pattern randomPresetPattern() {
		return Presets.getRandomPreset(isRandomColors());
	}

	public void cyclePattern(boolean forward) {
		if (forward)
			patternCycle++;

		if (patternCycle == Presets.getPresets(false).size() + 1)
			patternCycle = 0;
		;

		if (patternCycle == Presets.getPresets(false).size()) {
			lastPattern = randomPattern(maxSteps);
		} else {
			lastPattern = Presets.getPresets(isRandomColors()).get(patternCycle);
		}
	}

	public Pattern getCurrentPattern() {
		if (patternCycle == Presets.getPresets(false).size())
			return randomPattern(maxSteps);
		else
			return Presets.getPresets(isRandomColors()).get(patternCycle);
	}

	public boolean containsAnt(Ant a) {
		return ants.contains(a);
	}

	private void drawFrame() {
		if (isSeizure) {
			dw.setPixels(dw.to1DArray(seizure()));
		} else
			dw.setPixels(dw.to1DArray(pixels));
	}

	private void renderRawPixles() {
		calculateAnts(this.ants.toArray());
		renderCells();
		// renderAnts();
	}

	public void setAllPixels(Color color) {
		for (int x = 0; x < pixels.length; x++)
			for (int y = 0; y < pixels[x].length; y++)
				pixels[x][y] = color;
	}

	public void renderOverlay(Graphics g, long FPS) {
		// Info
		if (AntArt.isDrawInfo()) {
			int tlc = 15; // Top Left Corner
			g.setColor(new Color(Cells.defaultCell.getColor().getRed(), Cells.defaultCell.getColor().getGreen(),
					Cells.defaultCell.getColor().getBlue(), 150));
			g.fillRect(tlc - 10, tlc, tlc * 14, (int) (tlc * 20.5));

			g.setColor(Color.GREEN);
			g.drawString("----- Info -----", tlc, (int) (tlc * 2.5));
			if (AntArt.isDebug())
				g.drawString("Debug:  " + AntArt.isDebug(), tlc, (int) (tlc * 4.5));
			g.drawString("Version:  " + AntArt.getVersion(), tlc, (int) (tlc * 5.5));
			g.drawString("Last Mouse Click X: " + DrawWindow.dwm.lastClickX, tlc, (int) (tlc * 6.5));
			g.drawString("Last Mouse Clic Y: " + DrawWindow.dwm.lastClickY, tlc, (int) (tlc * 7.5));
			g.drawString("Mouse X: " + DrawWindow.dwm.mouseX, tlc, (int) (tlc * 8.5));
			g.drawString("Mouse Y: " + DrawWindow.dwm.mouseY, tlc, (int) (tlc * 9.5));

			g.drawString("FPS:  " + FPS, tlc, (int) (tlc * 10.5));
			g.drawString("Generations/Frame:  " + GPF, tlc, (int) (tlc * 11.5));

			g.drawString("Ant Count: " + ants.size(), tlc, (int) (tlc * 12.5));
			g.drawString("Ganeration: " + generationCount, tlc, (int) (tlc * 13.5));
			if (AntArt.isAuto()) {
				g.drawString("Auto Count: " + autoClearCount, tlc, (int) (tlc * 14.5));
				g.drawString("Auto Clear: " + autoClear, tlc, (int) (tlc * 15.5));
			}

			g.drawString("Constant Ants: " + isConstant, tlc, (int) (tlc * 16.5));

			if (!AntArt.isAuto())
				g.drawString("Pattern: " + lastPattern, tlc, (int) (tlc * 17.5));

			g.drawString("Auto: " + AntArt.isAuto(), tlc, (int) (tlc * 18.5));

			if (AntArt.isAuto())
				g.drawString("Auto Scattered: " + AntArt.isAutoScattered(), tlc, (int) (tlc * 19.5));

			g.drawString("Random Colors: " + isRandomColors(), tlc, (int) (tlc * 20.5));
			// g.drawString("Seizure Mode: " + isSeizure(), tlc, (int) (tlc *
			// 21.5));

			g.setColor(new Color(Cells.defaultCell.getColor().getRed(), Cells.defaultCell.getColor().getGreen(),
					Cells.defaultCell.getColor().getBlue(), 200));
			g.fillRect(tlc - 10, (int) (height - (tlc * 20.5) - 10), tlc * 14, (int) (height - (tlc * 45.5)));

			// Bottom Left Corner
			g.setColor(Color.magenta);
			g.drawString("----- Controls -----", tlc, (int) (height - (tlc * 20.5)));
			g.drawString("Q  -  Quit", tlc, (int) (height - (tlc * 18.5)));
			g.drawString("E  -  Erase Screen", tlc, (int) (height - (tlc * 17.5)));
			g.drawString("C  -  Clear All Ants From Screen", tlc, (int) (height - (tlc * 16.5)));
			g.drawString("H  -  Toggle HUD", tlc, (int) (height - (tlc * 15.5)));
			g.drawString("D  -  Toggle Debug", tlc, (int) (height - (tlc * 14.5)));
			g.drawString("T  -  Toggles Trail Mode", tlc, (int) (height - (tlc * 13.5)));
			g.drawString("R  -  Toggles Random Colors", tlc, (int) (height - (tlc * 12.5)));
			g.drawString("P  -  Cycle Manual Pattern", tlc, (int) (height - (tlc * 11.5)));
			g.drawString("Left Click  -  Generates Ant At Mouse", tlc, (int) (height - (tlc * 10.5)));
			g.drawString("Right Click  -  Select Pattern", tlc, (int) (height - (tlc * 9.5)));

			g.setColor(new Color(Cells.defaultCell.getColor().getRed(), Cells.defaultCell.getColor().getGreen(),
					Cells.defaultCell.getColor().getBlue(), 200));
			g.fillRect(tlc - 10, (int) (height - (tlc * 6.5) - 10), tlc * 11, (int) (height - (tlc * 50.5)));

			g.setColor(Color.red);
			g.drawString("----- Features -----", tlc, (int) (height - (tlc * 7.5)));
			g.drawString("A  -  Toggle Automatic Mode", tlc, (int) (height - (tlc * 5.5)));
			g.drawString("M  -  Toggles Mouse Trail", tlc, (int) (height - (tlc * 4.5)));
			g.drawString("Z  -  Zoom Out If Enabled", tlc, (int) (height - (tlc * 3.5)));
			g.drawString("Q  -  Quit", tlc, (int) (height - (tlc * 2.5)));
			if (AntArt.isAuto())
				g.drawString("S  -  Skip", tlc, (int) (height - (tlc * 1.5)));

			int trc = tlc + 400;
			// Top Right

			g.setColor(new Color(Cells.defaultCell.getColor().getRed(), Cells.defaultCell.getColor().getGreen(),
					Cells.defaultCell.getColor().getBlue(), 200));
			if (lastPattern != null && !AntArt.isDisplayBypass()) {
				g.fillRect(width - trc - 10, tlc, trc, tlc * (6 + lastPattern.getSteps().size()));
			}
			else
				g.fillRect(width - trc - 10, tlc, (int) (width - trc * 4.375), tlc * 2);

			g.setColor(Color.cyan);
			if (AntArt.isDrawInfo() && AntArt.isDisplayBypass()) {
				g.drawString("Ant Art - By: Matt Dwyer", width - trc, (int) (tlc * 2.5));
			} 
			else {
				g.drawString("----- Pattern -----", width - trc, (int) (tlc * 2.5));

				if (lastPattern != null) {
					g.drawString("Pattern: " + lastPattern, width - trc, (int) (tlc * 4.5));
					g.drawString("Steps:", width - trc, (int) (tlc * 6.5));

					for (int i = 1; i <= lastPattern.getSteps().size(); i++)
						g.drawString(i + ") " + lastPattern.getSteps().get(i - 1), width - trc,
								(int) (tlc * (6.5 + i)));

				}
			}

		} else {
			g.setColor(Color.green);
			g.drawString("Press H for Instructions", 15, (int) (15 * 2.5));

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

		if (AntArt.isAuto() && !AntArt.isAutoScattered())
			autoClearCount = autoClear;
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
		try {
			genNewAnt(dw.getMouse().x, dw.getMouse().y);
		} catch (Exception e) {
			if (AntArt.isDebug())
				e.printStackTrace();
		}
	}

	public void genNewAntInCenter(Render r) {
		genNewAnt(randomPattern(maxSteps), r.getCenterX(), r.getCenterY());
	}

	public void genNewAnt(int x, int y) {
		genNewAnt(lastPattern, x, y);
	}

	public void genNewAnt(Pattern p, int x, int y) {
		int calculatedX = ((pixels.length / DrawWindow.width) * x);
		int calculatedY = ((pixels[0].length / DrawWindow.height) * y);

		if (p == null) {
			lastPattern = getCurrentPattern();
			p = getCurrentPattern();
		}

		if (!isLimited || (isLimited && ants.size() <= limit)) {
			ants.add(new Ant(p, calculatedX, calculatedY));
		} else if (AntArt.isAuto()) {
			ants.remove(0);
			ants.add(new Ant(p, calculatedX, calculatedY));
		}
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

	public boolean isConstant() {
		return isConstant;
	}

	public void setConstant(boolean isConstant) {
		Render.isConstant = isConstant;
	}

	public void setAutoClearCount(int i) {
		autoClearCount = i;
	}

	public boolean isRandomColors() {
		return isRandomColors;
	}

	public void setRandomColors(boolean isRandomColors) {
		Render.isRandomColors = isRandomColors;
	}

	public boolean isSeizure() {
		return isSeizure;
	}

	public void setSeizure(boolean isSeizure) {
		Render.isSeizure = isSeizure;
	}

	public void setLastPattern(Pattern pattern) {
		lastPattern = pattern;
	}

}
