package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.LoadSave;

public class BigPlayer extends GameObject {
	private BufferedImage[][] animations;
	private int animationTick, animationIndex, animationSpeed;
	private int width, height;
	public static int WIDTH;
	public static int HEIGHT;
	
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = walking right
	 * 3 = walking left
	 */
	private int action;
	private int speed;
	private int gravity;
	
	public BigPlayer(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		this.width = 32;
		this.height = 32;
		animationSpeed = 25;
		scale = 4;
		WIDTH = width * scale;
		HEIGHT = height * scale;
		action = 0; // starts off facing right idle
		speed = 0;
		gravity = 1;
		loadAnimations();
	}
	
	public void tick() {
		x += speed;
		y += gravity;
		tickAnimation();						
	}
	
	public void render(Graphics g){
		g.drawImage(animations[action][animationIndex], x, y, width*scale, height*scale, null);
	}
	
	private void tickAnimation() {
		animationTick++;
		if(animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= 9) {
				animationIndex = 0;
			}
		}
	}
	
	private void loadAnimations() {
		BufferedImage image = LoadSave.getSpriteSheet(LoadSave.BIGPLAYER_SPRITESHEET);
		
		animations = new BufferedImage[4][9];
		for(int row = 0; row < animations.length; row++) {
			for(int col = 0; col < animations[row].length; col++) {
				animations[row][col] = image.getSubimage(col * width, row * height, 32, 32);
			}
		}
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setAction(int action) {
		this.action = action;
	}
	
	public void setGravity(int num) {
		gravity = num;
	}
	
}
