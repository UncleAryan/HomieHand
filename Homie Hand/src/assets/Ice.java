package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ImageLoader;

public class Ice extends GameObject {
	// r = 0, g = 0, b = 51
	private BufferedImage ice;
	
	public Ice(float x, float y, float width, float height, float scale, String ID) {
		super(x, y, width, height, scale, ID);
		ice = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage((int)width * 4, 0, (int)width, (int)height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(ice, (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
	}
}
