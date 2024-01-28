package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ImageLoader;

public class Ice extends GameObject {
	// r = 0, g = 0, b = 51
	private BufferedImage ice;
	
	public Ice(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		ice = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage(width * 4, 0, width, height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(ice, x, y, scaledWidth, scaledHeight, null);
	}
}
