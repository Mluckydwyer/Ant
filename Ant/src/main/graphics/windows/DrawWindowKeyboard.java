package main.graphics.windows;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.AntArt;
import main.graphics.Render;
import main.graphics.cells.Cells;

public class DrawWindowKeyboard extends DrawWindow implements KeyListener {

	Render r;

	public DrawWindowKeyboard(Render r) {
		super();

		this.r = r;
	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getKeyChar() == 'c') {
			if (AntArt.isDebug()) System.out.println("C Key Pressed, Clearing Ants");

			r.clearAnts();
		}
		else if (e.getKeyChar() == 'q' || e.equals(KeyEvent.VK_ESCAPE)) {
			if (AntArt.isDebug()) System.out.println("Q Key Pressed, Terminating Program");

			r.terminate();
		}
		else if (e.getKeyChar() == 'z') {
			if (AntArt.isDebug()) System.out.println("Z Key Pressed, Zooming Out");

			r.zoom(null);
		}
		else if (e.getKeyChar() == 'e') {
			if (AntArt.isDebug()) System.out.println("E Key Pressed, Clearing Screen");

			r.cells.setAll(Cells.defaultCell);
		}
		else if (e.getKeyChar() == 'd') {
			if (AntArt.isDebug()) System.out.println("D Key Pressed, Toggling Debug");

			AntArt.setDebug(!AntArt.isDebug());
		}
		else if (e.getKeyChar() == 'a') {
			if (AntArt.isDebug()) System.out.println("A Key Pressed, Toggling Auto");

			AntArt.setAuto(!AntArt.isAuto());
			r.clearAnts();
			r.cells.setAll(Cells.defaultCell);
			r.setAutoClearCount(0);
			// r.setLastPattern(r.getCurrentPattern());
		}
		/*
		 * else if (e.getKeyChar() == 's') {
		 * if (AntArt.isDebug()) System.out.println(
		 * "S Key Pressed, Toggling Seizure Mode");
		 * 
		 * r.setSeizure(!r.isSeizure());
		 * }
		 */
		else if (e.getKeyChar() == 'm') {
			if (AntArt.isDebug()) System.out.println("M Key Pressed, Toggling Constant Mouse Trail");

			r.setConstant(!r.isConstant());
		}
		else if (e.getKeyChar() == 'h') {
			if (AntArt.isDebug()) System.out.println("H Key Pressed, Toggling HUD");

			AntArt.setDrawInfo(!AntArt.isDrawInfo());
		}
		else if (e.getKeyChar() == 'p' && !AntArt.isAuto()) {
			if (AntArt.isDebug()) System.out.println("P Key Pressed, Cycling Patterns");

			r.cyclePattern(true);
		}
		else if (e.getKeyChar() == 'r') {
			if (AntArt.isDebug()) System.out.println("R Key Pressed, Toggling Random Colors");

			r.setRandomColors(!r.isRandomColors());
			r.cyclePattern(false);
		}
		else if (e.getKeyChar() == 't') {
			if (AntArt.isDebug()) System.out.println("T Key Pressed, Toggling Trail Mode");

			r.setConstant(!r.isConstant());
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Not Used
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not Used
	}

}
