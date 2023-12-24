package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.LoadSave;

public class Grass extends GameObject {
	private BufferedImage grass;
	private Rectangle bounds;
	
	public Grass(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		grass = LoadSave.getSpriteSheet(LoadSave.BLOCK_SPRITESHEET).getSubimage(0, 0, width, height);
		bounds = new Rectangle(x, y, scaledWidth, scaledHeight);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(grass, x, y, scaledWidth, scaledHeight, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, scaledWidth, scaledHeight);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
