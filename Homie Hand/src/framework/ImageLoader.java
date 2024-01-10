package framework;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

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
	
	public static BufferedImage getSpriteSheet(String file) {
		BufferedImage image = null;
		InputStream inputStream = ImageLoader.class.getResourceAsStream("/" + file);
		try {
			image = ImageIO.read(inputStream);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return image;
	}
}
