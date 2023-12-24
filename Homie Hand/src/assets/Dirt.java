package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.LoadSave;

public class Dirt extends GameObject {
	private BufferedImage dirt;
	private Rectangle bounds;
	
	public Dirt(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		dirt = LoadSave.getSpriteSheet(LoadSave.BLOCK_SPRITESHEET).getSubimage(32, 0, width, height);
		bounds = new Rectangle(x, y, scaledWidth, scaledHeight);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(dirt, x, y, scaledWidth, scaledHeight, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, scaledWidth, scaledHeight);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
