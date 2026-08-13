package framework;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ImageLoader {
	public static final String BIGPLAYER_SPRITESHEET = "big_player_animations.png";
	public static final String SMALLPLAYER_SPRITESHEET = "small_player_animation.png";
	public static final String BLOCK_SPRITESHEET = "block_spritesheet.png";
	public static final String LEVEL_ONE = "level1.png";
	public static final String HAMMER = "hammer.png";
	public static final String CLOUDY_BACKGROUND = "cloudy_background.png";
	public static final String POINTER = "pointer.png";
	public static final String BUTTONS_SPRITESHEET = "menu_buttons.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String ARROW_BUTTONS_SPRITESHEET = "arrow_buttons.png";
	public static final String FPS_OPTIONS_SPRITESHEET = "fps_textfield.png";
	public static final String SETTINGS_MENU_BACKGROUND = "settings_menu_background.png";
	public static final String BACK_BUTTON_SPRITESHEET = "back_buttons.png";
	public static final String ICE_BLOCK = "ice.png";
	public static final String LAVA_BLOCK = "lava.png";
	public static final String STONE_BLOCK = "stone.png";
	public static final String BUTTON = "button.png";

	private static final HashMap<String, BufferedImage> CACHE = new HashMap<>();
	
	public static BufferedImage getSpriteSheet(String file) {
		return CACHE.computeIfAbsent(file, f -> loadFromDisk(f));
	}

	private static BufferedImage loadFromDisk(String file) {
		try (InputStream inputStream = ImageLoader.class.getResourceAsStream("/" + file)) {
			if (inputStream == null) {
				throw new IllegalStateException("Missing Resource: " + file);
			}
			return ImageIO.read(inputStream);
		} catch (IOException e) {
			throw new IllegalStateException("Cannot read resource" + file, e);
		}
	}

}
