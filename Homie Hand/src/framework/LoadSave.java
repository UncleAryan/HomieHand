package framework;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import core.Panel;

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
	
	public static int[][] getLevel(){
		int[][] level = new int[Panel.BLOCKS_IN_ROW][Panel.BLOCKS_IN_COL];
		BufferedImage levelImage = getSpriteSheet(LEVEL_ONE);
		
		for(int row = 0; row < levelImage.getHeight(); row++) {
			for(int col = 0; col < levelImage.getWidth(); col++) {
				Color color = new Color(levelImage.getRGB(col, row));
				int value = color.getRed();
				if(value >= 64) {
					value = 0;
				}
				level[row][col] = value;
			}
		}
		
		return level;
	}
}
