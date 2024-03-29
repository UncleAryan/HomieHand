package framework;

import java.awt.image.BufferedImage;

import assets.Dirt;
import assets.Grass;
import assets.Ice;
import assets.Lava;
import assets.Stone;
import core.Panel;

public class LevelHandler {
	private Panel panel;
	
	public LevelHandler(Panel panel) {
		this.panel = panel;
		loadLevel(ImageLoader.getSpriteSheet(ImageLoader.LEVEL_ONE));
	}
	
	private void loadLevel(BufferedImage level) {
		for(int row = 0; row < level.getHeight(); row++) {
			for(int col = 0; col < level.getWidth(); col++) {
				int pixel = level.getRGB(row, col);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
			
				if(red == 0 && green == 102 && blue == 0) {
					panel.getGameObjectHandler().addGameObject(new Grass(row * Constants.DEFAULT_GAMEOBJECT_WIDTH, col * Constants.DEFAULT_GAMEOBJECT_HEIGHT, 
									                                     Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 1, "Block")); 
				}
				
				if(red == 64 && green == 64 && blue == 64) {
					panel.getGameObjectHandler().addGameObject(new Dirt(row * Constants.DEFAULT_GAMEOBJECT_WIDTH, col * Constants.DEFAULT_GAMEOBJECT_HEIGHT, 
									                                     Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 1, "Block")); 
				}
				
				if(red == 0 && green == 0 && blue == 51) {
					panel.getGameObjectHandler().addGameObject(new Ice(row * Constants.DEFAULT_GAMEOBJECT_WIDTH, col * Constants.DEFAULT_GAMEOBJECT_HEIGHT, 
                            											Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 1, "Block"));
				}
				
				if(red == 0 && green == 51 && blue == 0) {
					panel.getGameObjectHandler().addGameObject(new Stone(row * Constants.DEFAULT_GAMEOBJECT_WIDTH, col * Constants.DEFAULT_GAMEOBJECT_HEIGHT, 
                            											Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 1, "Block"));
				}
				
				if(red == 51 && green == 0 && blue == 0) {
					panel.getGameObjectHandler().addGameObject(new Lava(row * Constants.DEFAULT_GAMEOBJECT_WIDTH, col * Constants.DEFAULT_GAMEOBJECT_HEIGHT, 
                            											Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 1, "Block"));
				}
				
			}
		}
	}
}
