package assets;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.AnimationLoader;
import framework.CollisionHandler;
import framework.GameObject;
import framework.ImageLoader;

public class BigPlayer extends GameObject {
	// r = 255, g = 106, b = 0
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = walking right
	 * 3 = walking left
	 */
	private int action;
	private float gravity;
	private AnimationLoader animationLoader;
	private Hammer hammer;
	
	public BigPlayer(float x, float y, float width, float height, float scale, String ID) {
		super(x, y, width, height, scale, ID);
		animationLoader = new AnimationLoader(25);
		action = 0; // starts off facing right idle
		gravity = 1;
		animationLoader.loadAnimations(4, 9, originalWidth, originalHeight, ImageLoader.BIGPLAYER_SPRITESHEET);
		hammer = new Hammer(x, y, width, height, 2, "Hammer", this);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		hammer.tick(gameObjects);
		
		if(hammer.getThrowHammer() == true) {
			hammer.setHammerWithBigPlayer(false);
			
		} else {
			hammer.setHammerWithBigPlayer(true);
		}
		
		animationLoader.tickAnimation();
		CollisionHandler.tick(gameObjects, this);
	}
	
	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[action][animationLoader.getAnimationIndex()], (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
		
		hammer.render(g);
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
