package main.graphics.windows;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.AntArt;
import main.graphics.Render;

public class DrawWindowMouse extends DrawWindow implements MouseListener, MouseMotionListener {

	// private boolean focus = false;
	public int lastClickX = 0;
	public int lastClickY = 0;
	public int mouseX = 0;
	public int mouseY = 0;

	private Render r;

	public DrawWindowMouse(Render r) {
		super();

		this.r = r;
		if (AntArt.isFun())
			frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/cursors/Leaf.png"), new Point(frame.getX(), frame.getY()), "img"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && !AntArt.isAuto()) {

				lastClickX = e.getX();
				lastClickY = e.getY();

				if (AntArt.isDebug())
					System.out.println("\nMouse Left Clicked At  X: " + lastClickX + "  Y: " + lastClickY);

				r.genNewAnt(lastClickX, lastClickY);

				if (AntArt.isDebug())
					System.out.println("Generating Ant At  X: " + lastClickX + "  Y: " + lastClickY);
			}
			/*else if (e.getButton() == MouseEvent.BUTTON2) {
				if (AntArt.isDebug())
					System.out.println("\nMouse Middle Clicked At  X: " + lastClickX + "  Y: " + lastClickY);

				r.terminate();

				if (AntArt.isDebug())
					System.out.println("\nClosing Program At  X: " + lastClickX + "  Y: " + lastClickY);
			}*/
			else if (e.getButton() == MouseEvent.BUTTON3) {
				if (AntArt.isDebug())
					System.out.println("\nMouse Right Clicked At  X: " + lastClickX + "  Y: " + lastClickY);
				
				r.setLastPattern(r.cells.getCell(e.getX(), e.getY()).getLastPattern());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Do Noting on Press For Now
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Do Noting on Press For Now
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// ???????????????????????????????????
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// ???????????????????????????????????
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("test");

		if (e.getButton() == MouseEvent.BUTTON1) {

			lastClickX = e.getX();
			lastClickY = e.getY();

			if (AntArt.isDebug())
				System.out.println("\nMouse Left Dragged At  X: " + lastClickX + "  Y: " + lastClickY);

			r.genNewAnt(lastClickX, lastClickY);

			if (AntArt.isDebug())
				System.out.println("Generating Ant At  X: " + lastClickX + "  Y: " + lastClickY);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

}
