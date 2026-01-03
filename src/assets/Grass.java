package assets;

import framework.EntityType;
import framework.GameObject;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Grass extends GameObject {
	// r = 0, g = 102, b = 0
	private BufferedImage grass;
	
	public Grass(float x, float y, float width, float height, float scale, EntityType entityType) {
		super(x, y, width, height, scale, entityType);
		grass = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET).getSubimage(0, 0, (int)width, (int)height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(grass, (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
	}
}
