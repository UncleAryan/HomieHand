package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.LoadSave;

public class Dirt extends GameObject {
	private BufferedImage dirt;
	private int width, height;
	public static int WIDTH;
	public static int HEIGHT;
	
	public Dirt(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		scale = 1;
		this.width = 32;
		this.height = 32;
		dirt = LoadSave.getSpriteSheet(LoadSave.BLOCK_SPRITESHEET).getSubimage(32, 0, this.width, this.height);
		WIDTH = width * scale;
		HEIGHT = height * scale;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(dirt, x, y, null);
	}
}
