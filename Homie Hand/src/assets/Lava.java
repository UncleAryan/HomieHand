package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ImageLoader;

public class Lava extends GameObject {
	// r = 51, g = 0, b = 0
	private BufferedImage lava;
	
	public Lava(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		lava = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage(width * 3, 0, width, height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(lava, x, y, scaledWidth, scaledHeight, null);
	}
}