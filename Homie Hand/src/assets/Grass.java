package assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.LoadSave;

public class Grass extends GameObject {
	private BufferedImage grass;
	private int width, height;
	private Rectangle bounds;
	
	public Grass(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		scale = 1;
		this.width = width * scale;
		this.height = height * scale;
		grass = LoadSave.getSpriteSheet(LoadSave.BLOCK_SPRITESHEET).getSubimage(0, 0, width, height);
		bounds = new Rectangle(x, y, width * scale, height * scale);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(grass, x, y, width, height, null);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
