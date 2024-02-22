package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ImageLoader;

public class Lava extends GameObject {
	// r = 51, g = 0, b = 0
	private BufferedImage lava;
	
	public Lava(float x, float y, float width, float height, float scale, String ID) {
		super(x, y, width, height, scale, ID);
		lava = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage((int)width * 3, 0, (int)width, (int)height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(lava, (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
	}
}