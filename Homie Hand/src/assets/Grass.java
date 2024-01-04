package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.LoadSave;

public class Grass extends GameObject {
	private BufferedImage grass;
	
	public Grass(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		grass = LoadSave.getSpriteSheet(LoadSave.BLOCK_SPRITESHEET).getSubimage(0, 0, width, height);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(grass, x, y, scaledWidth, scaledHeight, null);
	}
}
