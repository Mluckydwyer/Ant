package main.graphics.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import main.AntArt;

public class LoadingWindow {

	private final int width = 500;
	private final int height = 300;
	private final int loadingBarSteps = 10;
	private final int sleepLength = 1000 / loadingBarSteps;

	private JFrame frame;
	private JProgressBar bar;
	private JLabel img;
	public static Image loadingImage;
	private JLabel text;

	public LoadingWindow() {
		frame = new JFrame();

		frame.setVisible(false);
		frame.setUndecorated(true);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setBackground(Color.GREEN);
		frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - (width / 2), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - (height / 2));
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);

		bar = new JProgressBar(0, 0, loadingBarSteps);

		bar.setStringPainted(true);
		if (!AntArt.isFun())
			advance("Preparing to Load Window");
		else
			advance("Being Amazing");

		frame.add(bar);

		try {
			loadingImage = ImageIO.read(new File("res/images/LoadScreenTreeCoolText.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		img = new JLabel(new ImageIcon(loadingImage.getScaledInstance(width, 280, Image.SCALE_SMOOTH)));

		frame.add(img);

		text = new JLabel("Tree\nBy: Matt Dwyer");
		text.setHorizontalAlignment(SwingConstants.LEFT);
		text.setForeground(Color.BLUE);

		layout();
		frame.setVisible(true);

		// To be able to see the first thing
		try {
			Thread.sleep(sleepLength);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void layout() {
		frame.getContentPane().add(bar, BorderLayout.PAGE_END);
		frame.getContentPane().add(img, BorderLayout.PAGE_START);
		frame.doLayout();
	}

	public void advance(String step) {
		bar.setString(step);
		bar.setValue(0);

		for (int i = 0; i < loadingBarSteps + 1; i++) {

			bar.setValue(i);
			
			try {
				Thread.sleep(sleepLength);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void close() {
		frame.setVisible(false);
		frame.dispose();
	}
}
