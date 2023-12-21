package framework;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
	public static final String BIGPLAYER_SPRITESHEET = "big_player_animations.png";
	public static final String BLOCK_SPRITESHEET = "block_spritesheet.png";
	public static final String LEVEL_ONE = "level1.png";
	
	public static BufferedImage getSpriteSheet(String file) {
		BufferedImage image = null;
		InputStream inputStream = LoadSave.class.getResourceAsStream("/" + file);
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
