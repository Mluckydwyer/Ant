package main;

import java.util.Scanner;

import main.graphics.windows.DrawWindow;
import main.graphics.windows.LoadingWindow;
import main.graphics.windows.UserControlWindow;

public class AntArt {
	// ---------- Variables & Objects & Setting ----------

	// Objects
	private static LoadingWindow load;
	private static DrawWindow draw;
	private static UserControlWindow user;
	private static Thread mainWindow;
	private static Thread controlWindow;

	// Variables
	public static boolean running;

	// Settings / General Info
	private static final String title = "Ant";
	private static final String version = "v1.1 Beta";
	private static int FPSCap = -1; // If -1 Then There Is Not Cap Implemented
	private static boolean fullScreen = false;
	private static boolean loadingWindow = true;
	private static int loadWindowLength = 3;

	// Testing Info
	private static boolean debug = true; // debug or user present
	private static boolean auto = false; // auto or user controlled
	private static boolean autoScattered = false;
	private static boolean drawInfo = true;

	// Random / Fun / Secret Settings
	private static boolean fun = true;
	private static int loadWindowFunLength = 8;

	// ---------- Main Method For Whole Program ----------
	public static void main(String[] args) {
		if (!debug) {
			Scanner s = new Scanner(System.in);
			String temp;
			boolean again = true;

			do {
				again = true;

				System.out.println("Would you like this program to open in full screen? (Y/N)");
				temp = s.next();
				System.out.println("");

				if (temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("T")) {
					again = false;
					fullScreen = true;
				}
				else if (temp.equalsIgnoreCase("N") || temp.equalsIgnoreCase("F")) {
					again = false;
					fullScreen = false;
				}
			}
			while (again);

			do {
				again = true;

				System.out.println("Would you like this program to run in automatic/display mode? (Y/N)");
				temp = s.next();
				System.out.println("");

				if (temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("T") || temp.equalsIgnoreCase("A") || temp.equalsIgnoreCase("D")) {
					again = false;
					auto = true;
				}
				else if (temp.equalsIgnoreCase("N") || temp.equalsIgnoreCase("F") || temp.equalsIgnoreCase("NO")) {
					again = false;
					auto = false;
				}
			}
			while (again);
			s.close();
		}
		// Debug mode settings
		else {
			fullScreen = false;
			autoScattered = true;
			auto = true;
			loadingWindow = false;
		}
		
		// Present mode
		if (!debug) {
			if (auto)
				loadingWindow = false;
			drawInfo = true;
		}

		// Starts Loading Window
		if (loadingWindow) {

			// Real
			if (!fun) {
				try {
					load = new LoadingWindow();

					load.advance("Creating Window");
					load.advance("Creating Menus");
					load.advance("Displaying Window");

					load.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
			// Fun
			else {

				try {
					load = new LoadingWindow();

					load.advance("Finding Mounds");
					load.advance("Feeding The Queen");
					load.advance("Expanding Colonie");
					load.advance("Collecting Leaves");
					load.advance("Recalibrating Anteni");
					load.advance("Alerting The Hive");
					load.advance("Hatching The Larva");
					load.advance("Fighting The Humans");

					load.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

		running = true;
		draw = new DrawWindow();
		user = new UserControlWindow();
		mainWindow = new Thread(draw, "Draw Window");
		controlWindow = new Thread(user, "User Control Window");

		mainWindow.setDaemon(true);
		mainWindow.start();
		if (!auto)
			controlWindow.start();

		try {
			Thread.currentThread().join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ---------- Getters For Program Main Settings ----------

	// Returns Program Title
	public static String getTitle() {
		return title;
	}

	// Returns Version Number
	public static String getVersion() {
		return version;
	}

	// Returns The Set FPS Limit
	public static int getFPSCap() {
		return FPSCap;
	}

	// Returns The State Of Fun Setting
	public static boolean isFun() {
		return fun;
	}

	// Returns The State Of Whether FullScreen Is Enabled
	public static boolean isFullScreen() {
		return fullScreen;
	}

	// Returns The state Of Whether Or Not To Draw The Debug Info Overlay
	// Directly Over The Window
	public static boolean isDrawInfo() {
		return drawInfo;
	}

	// Returns The State Of Whether Debug Mode Is Enabled
	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug(boolean debug) {
		AntArt.debug = debug;
	}
	
	public static int getLoadWindowFunLength() {
		return loadWindowFunLength;
	}

	public static int getLoadWindowLength() {
		return loadWindowLength;
	}

	public static boolean isAuto() {
		return auto;
	}

	public static void setAuto(boolean auto) {
		AntArt.auto = auto;
	}
	
	public static boolean isAutoScattered() {
		return autoScattered;
	}
	
	public static void setIsAutoScattered(boolean b) {
		autoScattered = b;
	}

}
