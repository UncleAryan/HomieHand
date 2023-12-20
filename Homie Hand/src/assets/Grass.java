package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.LoadSave;

public class Grass extends GameObject {
	private BufferedImage grass;
	private int width, height;
	public static int WIDTH, HEIGHT;
	
	public Grass(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		this.width = 32;
		this.height = 32;
		grass = LoadSave.getSpriteSheet(LoadSave.BLOCK_SPRITESHEET).getSubimage(0, 0, this.width, this.height);
		scale = 1;
		WIDTH = width * scale;
		HEIGHT = height * scale;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(grass, x, y, width * scale, height * scale, null);
	}
}
