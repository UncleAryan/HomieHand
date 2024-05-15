package assets;

import framework.Constants;
import framework.GameObject;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Dirt extends GameObject {
	// r = 64, g = 64, g = 64
	private BufferedImage dirt;
	
	public Dirt(float x, float y, float width, float height, float scale, String ID) {
		super(x, y, width, height, scale, ID);
		dirt = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage(Constants.DEFAULT_GAMEOBJECT_WIDTH * 1, 0, (int)width, (int)height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(dirt, (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
	}
}
