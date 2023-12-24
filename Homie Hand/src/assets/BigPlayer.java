package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	
	public BigPlayer(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		animationLoader = new AnimationLoader(25);
		action = 0; // starts off facing right idle
		gravity = 1;
		bounds = new Rectangle(x, y, scaledWidth, scaledHeight);
		collisionHandler = new CollisionHandler();
		animationLoader.loadAnimations(4, 9, originalWidth, originalHeight, LoadSave.BIGPLAYER_SPRITESHEET);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		collisionHandler.checkCollision(gameObjects, this);
		animationLoader.tickAnimation();
	}
	
	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[action][animationLoader.getAnimationIndex()], x, y, scaledWidth, scaledHeight, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, scaledWidth, scaledHeight);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setAction(int action) {
		this.action = action;
	}
}
