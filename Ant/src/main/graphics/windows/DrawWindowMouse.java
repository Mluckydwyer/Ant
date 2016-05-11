package main.graphics.windows;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.AntArt;
import main.graphics.Render;

public class DrawWindowMouse extends DrawWindow implements MouseListener {

	// private boolean focus = false;
	public int lastClickX = 0;
	public int lastClickY = 0;
	private Render r;

	public DrawWindowMouse(Render r) {
		super();

		this.r = r;
		if (AntArt.isFun())
			frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/cursors/Leaf.png"), new Point(frame.getX(), frame.getY()), "img"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {

			lastClickX = e.getX();
			lastClickY = e.getY();
			if (AntArt.isDebug())
				System.out.println("\nMouse Left Clicked At  X: " + lastClickX + "  Y: " + lastClickY);

			r.genNewAnt(lastClickX, lastClickY);
			if (AntArt.isDebug())
				System.out.println("Generating Tree At  X: " + lastClickX + "  Y: " + lastClickY);
		}
		else if (e.getButton() == MouseEvent.BUTTON2) {
			if (AntArt.isDebug())
				System.out.println("\nMouse Middle Clicked At  X: " + lastClickX + "  Y: " + lastClickY);

			r.terminate();
			
			if (AntArt.isDebug())
				System.out.println("\nClosing Program At  X: " + lastClickX + "  Y: " + lastClickY);
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			if (AntArt.isDebug())
				System.out.println("\nMouse Right Clicked At  X: " + lastClickX + "  Y: " + lastClickY);
			// Does Nothing
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
}
