package framework;

import java.awt.image.BufferedImage;

import assets.Grass;
import core.Panel;

public class LevelHandler {
	private Panel panel;
	
	public LevelHandler(Panel panel) {
		this.panel = panel;
		loadLevel(LoadSave.getSpriteSheet(LoadSave.LEVEL_ONE));
	}
	
	private void loadLevel(BufferedImage level) {
		for(int row = 0; row < level.getHeight(); row++) {
			for(int col = 0; col < level.getWidth(); col++) {
				int pixel = level.getRGB(row, col);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
			
				if(red == 255 && green == 255 && blue == 255) {
					panel.getGameObjectHandler().addGameObject(new Grass(row * Constants.DEFAULT_GAMEOBJECT_WIDTH, col * Constants.DEFAULT_GAMEOBJECT_HEIGHT, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, "Grass")); 
				}
			}
		}
	}
}
