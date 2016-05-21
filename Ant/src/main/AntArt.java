package main;

import main.graphics.windows.DrawWindow;
import main.graphics.windows.LoadingWindow;

public class AntArt {
    // ---------- Variables & Objects & Setting ----------
    
    // Objects
    private static LoadingWindow load;
    private static DrawWindow draw;
    private static Thread mainWindow;
    
    // Variables
    public static boolean running;
    
    // Settings / General Info
    private static final String title = "Ant";
    private static final String version = "v0.2 Alpha";
    private static int FPSCap = -1; // If -1 Then There Is Not Cap Implemented
    private static boolean fullScreen = false;
    private static boolean loadingWindow = false;
    private static int loadWindowLength = 3;
    
    // Testing Info
    private static boolean debug = true;
    private static boolean drawInfo = true;
    
    // Random / Fun / Secret Settings
    private static boolean fun = true;
    private static int loadWindowFunLength = 8;
    
    // ---------- Main Method For Whole Program ----------
    public static void main(String[] args) {
        running = true;
        draw = new DrawWindow();
        mainWindow = new Thread(draw, "Draw Window");
        
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

        mainWindow.setDaemon(true);
        mainWindow.start();
        
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
    
    // Returns The state Of Whether Or Not To Draw The Debug Info Overlay Directly Over The Window
    public static boolean isDrawInfo() {
        return drawInfo;
    }
    
    // Returns The State Of Whether Debug Mode Is Enabled
    public static boolean isDebug() {
        return debug;
    }

	public static int getLoadWindowFunLength() {
		return loadWindowFunLength;
	}

	public static int getLoadWindowLength() {
		return loadWindowLength;
	}
    
}
