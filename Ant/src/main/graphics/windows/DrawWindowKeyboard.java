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
			if (AntArt.isDebug())
				System.out.println("C Key Pressed, Clearing Ants");
			
			r.clearAnts();
		}
		else if (e.getKeyChar() == 'q' || e.equals(KeyEvent.VK_ESCAPE)) {
			if (AntArt.isDebug())
				System.out.println("Q Key Pressed, Terminating Program");
			
			r.terminate();
		}
		else if (e.getKeyChar() == 'z') {
			if (AntArt.isDebug())
				System.out.println("Z Key Pressed, Zooming Out");
			
			r.zoom(null);
		}
		else if (e.getKeyChar() == 'e') {
			if (AntArt.isDebug())
				System.out.println("E Key Pressed, Clearing Screen");
			
			r.cells.setAll(Cells.defaultCell);
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
