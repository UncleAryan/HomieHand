package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.AnimationLoader;
import framework.CollisionHandler;
import framework.GameObject;
import framework.LoadSave;

public class BigPlayer extends GameObject {
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = walking right
	 * 3 = walking left
	 */
	private int action;
	private int gravity;
	private Rectangle bounds;
	private AnimationLoader animationLoader;
	private CollisionHandler collisionHandler;
	
	public BigPlayer(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		animationLoader = new AnimationLoader(25);
		scale = 4;
		action = 0; // starts off facing right idle
		gravity = 1;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(x, y, width * scale, height * scale);
		collisionHandler = new CollisionHandler();
		animationLoader.loadAnimations(4, 9, width, height, LoadSave.BIGPLAYER_SPRITESHEET);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		collisionHandler.checkCollision(gameObjects, this);
		animationLoader.tickAnimation();
	}
	
	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[action][animationLoader.getAnimationIndex()], x, y, width * scale, height * scale, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width * scale, height * scale);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setAction(int action) {
		this.action = action;
	}
}
