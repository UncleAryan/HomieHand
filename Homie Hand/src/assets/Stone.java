package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ImageLoader;

public class Stone extends GameObject {
	// r = 0, g = 51, b = 0
	private BufferedImage stone;
	
	public Stone(float x, float y, float width, float height, float scale, String ID) {
		super(x, y, width, height, scale, ID);
		stone = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage((int)width * 2, 0, (int)width, (int)height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(stone, (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
	}
}