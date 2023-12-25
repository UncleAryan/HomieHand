package assets;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.AnimationLoader;
import framework.CollisionHandler;
import framework.GameObject;
import framework.LoadSave;


public class SmallPlayer extends GameObject {
	private AnimationLoader animationLoader;
	private CollisionHandler collisionHandler;
	
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = jumping right
	 * 3 = jumping left
	 */
	private int action;
	private int gravity;
	private boolean jumping;
	private int MAX_JUMP;
	public static boolean onGround;
	private int jumpSpeed;
	
	public SmallPlayer(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		animationLoader = new AnimationLoader(30);
		action = 1; 
		collisionHandler = new CollisionHandler();
		gravity = 1;
		jumping = false;
		onGround = false;
		MAX_JUMP = y - height * 5;
		jumpSpeed = 6;
		animationLoader.loadAnimations(4, 19, originalWidth, originalHeight, LoadSave.SMALLPLAYER_SPRITESHEET);
	}

	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[action][animationLoader.getAnimationIndex()], x, y, scaledWidth, scaledHeight, null);
		showBoundsOutline(g);
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).getID().equals("BigPlayer")) {
				if(gameObjects.get(i).getX() < x) {
					action = 1;
				}
				if(gameObjects.get(i).getX() > x) {
					action = 0;
				}
			} 
		}
		
		if(jumping) {
			if(action == 1) {
				action = 3;
			} else {
				action = 2;
			}
			
			if(animationLoader.getAnimationIndex() == 11) {
				animationLoader.setAnimationSpeed(15);
				
				for(int i = 1; i <= jumpSpeed; i++) {
					ySpeed = -i;	
					if(y + ySpeed <= MAX_JUMP) {
						jumping = false;
						action = 1;
					}
				}
				
				animationLoader.setAnimationSpeed(30);
			}
		} 
		
		collisionHandler.checkCollision(gameObjects, this);
		animationLoader.tickAnimation();
		
		if(onGround) {
			MAX_JUMP = y - scaledHeight * 2;
		}
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public boolean isJumping() {
		return jumping;
	}
}
