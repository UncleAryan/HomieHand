package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.LoadSave;

public class BigPlayer extends GameObject {
	private BufferedImage[][] animations;
	private int animationTick, animationIndex, animationSpeed;
	private final int MAX_SPEED;
	
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = walking right
	 * 3 = walking left
	 */
	private int action;
	private int gravity;
	private Rectangle bounds;
	
	public BigPlayer(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		MAX_SPEED = 1;
		animationSpeed = 25;
		scale = 4;
		action = 0; // starts off facing right idle
		gravity = 1;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(x, y, width * scale, height * scale);
		
		falling = true;
		
		loadAnimations();
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		
		if(falling) {
			ySpeed += gravity;
			
			if(ySpeed > MAX_SPEED) {
				ySpeed = MAX_SPEED;
			}
		}
		updateBounds();
		collisionDetection(gameObjects);
		tickAnimation();
	}
	
	public void updateBounds() {
		bounds.x = x;
		bounds.y = y;
	}
	
	public void collisionDetection(LinkedList<GameObject> gameObjects) {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).getID().equals("Grass") && getBounds().intersects(gameObjects.get(i).getBounds())) {
				ySpeed = 0;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[action][animationIndex], x, y, width * scale, height * scale, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width * scale, height * scale);
	}
	
	public Rectangle getBounds() {
		return bounds;
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
	
	public void setAction(int action) {
		this.action = action;
	}
	
	public void setGravity(int num) {
		gravity = num;
	}
	
}
