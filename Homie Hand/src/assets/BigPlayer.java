package assets;

import java.awt.Graphics;
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
	private AnimationLoader animationLoader;
	private CollisionHandler collisionHandler;
	private Hammer hammer;
	
	public BigPlayer(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		animationLoader = new AnimationLoader(25);
		action = 0; // starts off facing right idle
		gravity = 1;
		collisionHandler = new CollisionHandler();
		animationLoader.loadAnimations(4, 9, originalWidth, originalHeight, LoadSave.BIGPLAYER_SPRITESHEET);
		hammer = new Hammer(x, y, width, height, 2, "Hammer", this);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		hammer.tick(gameObjects);
		
		collisionHandler.checkCollision(gameObjects, this);
		animationLoader.tickAnimation();
	}
	
	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[action][animationLoader.getAnimationIndex()], x, y, scaledWidth, scaledHeight, null);
		
		hammer.render(g);
		
		showBoundsOutline(g);
	}
	
	public void setAction(int action) {
		this.action = action;
	}
	
	public AnimationLoader getAnimationLoader() {
		return animationLoader;
	}
	
	public Hammer getHammer() {
		return hammer;
	}
	
	public int getAction() {
		return action;
	}
}
