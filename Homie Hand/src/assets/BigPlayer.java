package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.AnimationTicker;
import framework.CollisionHandler;
import framework.GameObject;
import framework.LoadSave;

public class BigPlayer extends GameObject {
	private BufferedImage[][] animations;
	
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = walking right
	 * 3 = walking left
	 */
	private int action;
	private int gravity;
	private Rectangle bounds;
	private AnimationTicker animationTicker;
	private CollisionHandler collisionHandler;
	
	public BigPlayer(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		animationTicker = new AnimationTicker(25);
		scale = 4;
		action = 0; // starts off facing right idle
		gravity = 1;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(x, y, width * scale, height * scale);
		collisionHandler = new CollisionHandler();
		loadAnimations();
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		collisionHandler.checkCollision(gameObjects, this);
		animationTicker.tickAnimation();
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[action][animationTicker.getAnimationIndex()], x, y, width * scale, height * scale, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width * scale, height * scale);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	private void loadAnimations() {
		BufferedImage image = LoadSave.getSpriteSheet(LoadSave.BIGPLAYER_SPRITESHEET);
		
		animations = new BufferedImage[4][9];
		for(int row = 0; row < animations.length; row++) {
			for(int col = 0; col < animations[row].length; col++) {
				animations[row][col] = image.getSubimage(col * width, row * height, width, height);
			}
		}
	}
	
	public void setAction(int action) {
		this.action = action;
	}
}
