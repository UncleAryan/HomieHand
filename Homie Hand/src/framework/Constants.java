package framework;

import java.awt.Toolkit;

public class Constants {
	// this is assuming screen dimensions are 1280 by 720
	public static int DEFAULT_GAMEOBJECT_WIDTH = 32;
	public static int DEFAULT_GAMEOBJECT_HEIGHT = 32;
	
	public static int BIGPLAYER_XSPEED = 1;
	
	public final static float WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public final static float HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public final static int DEFAULT_BUTTON_WIDTH = 32;
	public final static int DEFAULT_BUTTON_HEIGHT = 16;
	public final static int DEFAULT_POINTER_WIDTH = 32;
	public final static int DEFAULT_POINTER_HEIGHT = 14;
	public final static int DEFAULT_ARROW_BUTTON_WIDTH = 16;
	public final static int DEFAULT_ARROW_BUTTON_HEIGHT = 16;
	public final static int DEFAULT_FPS_TEXTFIELD_WIDTH = 64;
	public final static int DEFAULT_FPS_TEXTFIELD_HEIGHT = 32;
	public final static int DEFAULT_BACK_BUTTON_WIDTH = 32;
	public final static int DEFAULT_BACK_BUTTON_HEIGHT = 32;
}
