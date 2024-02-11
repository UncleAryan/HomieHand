package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.Constants;
import framework.GameObject;
import framework.ImageLoader;

public class Dirt extends GameObject {
	// r = 64, g = 64, g = 64
	private BufferedImage dirt;
	
	public Dirt(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		dirt = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage(Constants.DEFAULT_GAMEOBJECT_WIDTH * 1, 0, width, height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(dirt, x, y, scaledWidth, scaledHeight, null);
	}
}
