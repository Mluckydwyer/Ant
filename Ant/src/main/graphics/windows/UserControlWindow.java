package main.graphics.windows;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import main.AntArt;
import main.graphics.Render;

public class UserControlWindow extends Thread {

	protected JFrame frame;
	private Color backgroundColor = Color.WHITE;

	public UserControlWindow() {
		
		frame = new JFrame(AntArt.getTitle());
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(0, 0);
		frame.setBackground(backgroundColor);
		frame.setSize((Toolkit.getDefaultToolkit().getScreenSize().width - 200) / 4, (Toolkit.getDefaultToolkit().getScreenSize().height - 100) / 4);
		frame.setAlwaysOnTop(true);
	}
	
	@Override
	public void run() {
		
		//frame.add(buildMenuBar());
		frame.setVisible(true);
		
		while (AntArt.running) {
			//frame.add(render());
			frame.getContentPane().add(render());
			frame.repaint();
		}
		
	}

	private JMenuBar buildMenuBar() {
		return new JMenuBar();
	}
	
	private JPanel render() {
		JPanel jp = new JPanel();
		
		jp.setLayout(new CardLayout());
		
		JCheckBox constantAnts = new JCheckBox("Constant Ant Placement");
		constantAnts.setToolTipText("When on, this spams the screen with Ants, Can Cause Lag");
		constantAnts.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) Render.setConstant(true);
				else Render.setConstant(false);
			}
		});
		jp.add(constantAnts);
		
		return jp;
	}
}
