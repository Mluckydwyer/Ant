package main.graphics.windows;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.AntArt;
import main.graphics.Render;

public class UserControlWindow extends Thread {

	// UNSED CODE TODO
	
	protected JFrame frame;
	private Color backgroundColor = Color.WHITE;

	public UserControlWindow() {
		frame = new JFrame(AntArt.getTitle());
		frame.setVisible(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(backgroundColor);
		frame.setSize((DrawWindow.width - 200) / 4, (DrawWindow.height - 100) / 4);
		frame.setAlwaysOnTop(true);
		if (AntArt.isFullScreen()) frame.setLocation(DrawWindow.width - frame.getWidth(), 0);
		else ;
	}
	
	@Override
	public void run() {
		frame.getContentPane().add(render());
		frame.setVisible(true);
		
		while (AntArt.running) {
			frame.repaint();
		}
		
	}
	
	private JPanel render() {
		JPanel jp = new JPanel();
		
		jp.setLayout(new CardLayout());
		
		JCheckBox constantAnts = new JCheckBox("Constant Ant Placement");
		constantAnts.setToolTipText("When on, this spams the screen with Ants, Can Cause Lag");
		constantAnts.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//if (e.getStateChange() == 1) Render.setIsConstant(true);
				//else Render.setIsConstant(false);
			}
		});
		jp.add(constantAnts);
		
		return jp;
	}
}
