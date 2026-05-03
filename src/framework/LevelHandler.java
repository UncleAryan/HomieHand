package framework;

import assets.Block;
import core.Panel;

import java.awt.image.BufferedImage;

public class LevelHandler {
	private Panel panel;

	public LevelHandler(Panel panel) {
		this.panel = panel;
		loadLevel(ImageLoader.getSpriteSheet(ImageLoader.LEVEL_ONE));
	}

	private void loadLevel(BufferedImage level) {
		for (int row = 0; row < level.getHeight(); row++) {
			for (int col = 0; col < level.getWidth(); col++) {
				// getRGB takes (x, y) — x is the column, y is the row
				int pixel = level.getRGB(col, row);
				int red   = (pixel >> 16) & 0xff;
				int green = (pixel >> 8)  & 0xff;
				int blue  = (pixel)       & 0xff;

				BlockType blockType = BlockType.fromRGB(red, green, blue);
				if (blockType != null) {
					float x = (col * Constants.DEFAULT_GAMEOBJECT_WIDTH)  - Constants.DEFAULT_GAMEOBJECT_WIDTH;
					float y = (row * Constants.DEFAULT_GAMEOBJECT_HEIGHT) - Constants.DEFAULT_GAMEOBJECT_HEIGHT;
					panel.getGameObjectHandler().addGameObject(
						new Block(x, y, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 1, blockType)
					);
				}
			}
		}
	}
}
