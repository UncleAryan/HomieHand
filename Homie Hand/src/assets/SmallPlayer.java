package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.AnimationLoader;
import framework.CollisionHandler;
import framework.GameObject;
import framework.LoadSave;

/*
 * Add jumping
 */
public class SmallPlayer extends GameObject {
	private AnimationLoader animationLoader;
	private CollisionHandler collisionHandler;
	private Rectangle bounds;
	
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = walking right
	 * 3 = walking left
	 */
	private int action;
	private int scale;
	private int gravity;
	
	public SmallPlayer(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		this.width = width;
		this.height = height;
		scale = 4;
		animationLoader = new AnimationLoader(30);
		bounds = new Rectangle(x, y, width * scale, height * scale);
		action = 1; // starts off jumping right
		collisionHandler = new CollisionHandler();
		gravity = 1;
		
		animationLoader.loadAnimations(4, 19, width, height, LoadSave.SMALLPLAYER_SPRITESHEET);
	}

	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[action][animationLoader.getAnimationIndex()], x, y, width * scale, height * scale, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width * scale, height * scale);
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		collisionHandler.checkCollision(gameObjects, this);
		animationLoader.tickAnimation();
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
