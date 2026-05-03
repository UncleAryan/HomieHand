package framework;

public class Constants {
	// Screen
	public final static float WIDTH = 1280;
	public final static float HEIGHT = 720;
	public final static float SCALE = 1;

	// Game objects
	public static int DEFAULT_GAMEOBJECT_WIDTH = 32;
	public static int DEFAULT_GAMEOBJECT_HEIGHT = 32;

	// Physics
	public static final float GRAVITY = 1f;

	// BigPlayer movement
	public static int BIGPLAYER_XSPEED = 1;

	// SmallPlayer jump
	public static final float JUMP_SPEED = 20f;
	public static final float JUMP_HEIGHT_MULTIPLIER = 2f;
	public static final int JUMP_LAUNCH_FRAME = 11;

	// Hammer
	public static final double HAMMER_SPIN_SPEED = 0.05;
	public static final float  HAMMER_FLOAT_SPEED = -1f;
	public static final float  HAMMER_THROW_SPEED = 3f;
	public static final float  HAMMER_OFFSET_X = 0.25f;
	public static final float  HAMMER_OFFSET_Y = 0.33f;
	public static final double HAMMER_IDLE_ANGLE = 45.0;

	// Knockback applied to SmallPlayer on hammer hit
	public static final float KNOCKBACK_X = 3f;
	public static final float KNOCKBACK_Y = -2f;

	// UI
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
