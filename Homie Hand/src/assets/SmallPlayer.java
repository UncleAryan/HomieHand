package assets;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.AnimationLoader;
import framework.CollisionHandler;
import framework.GameObject;
import framework.ImageLoader;


public class SmallPlayer extends GameObject {
	private AnimationLoader animationLoader;
	
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = jumping right
	 * 3 = jumping left
	 */
	private int action;
	private float gravity;
	private boolean jumping;
	private float MAX_JUMP;
	private float jumpSpeed;
	
	public SmallPlayer(float x, float y, float width, float height, float scale, String ID) {
		super(x, y, width, height, scale, ID);
		animationLoader = new AnimationLoader(30);
		action = 1; 
		gravity = 1;
		jumping = false;
		onGround = false;
		MAX_JUMP = y - scaledHeight * 4;
		jumpSpeed = 10;
		animationLoader.loadAnimations(4, 19, originalWidth, originalHeight, ImageLoader.SMALLPLAYER_SPRITESHEET);
	}

	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[action][animationLoader.getAnimationIndex()], (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		
		faceBigPlayer(gameObjects);
		
		if(jumping) {
			performJump();
		} 
		
		animationLoader.tickAnimation();
		CollisionHandler.tick(gameObjects, this);
		updateBounds();
		
		updateMaxJump();
	}
	
	public void faceBigPlayer(LinkedList<GameObject> gameObjects) {
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
	}
	
	public void performJump() {
		if(action == 1) {
			action = 3;
		} else {
			action = 2;
		}
		
		if(animationLoader.getAnimationIndex() == 11) {
			animationLoader.setAnimationSpeed(15);
			
			for(int i = 0; i <= jumpSpeed; i++) {
				ySpeed = -i;	
				onGround = false;
				if(y + ySpeed <= MAX_JUMP) {
					jumping = false;
					action = 1;
				}
			}
			
			animationLoader.setAnimationSpeed(30);
		}
	}
	
	public void updateMaxJump() {
		if(onGround) {
			MAX_JUMP = y - scaledHeight * 4;
		}
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	
	public AnimationLoader getAnimationLoader() {
		return animationLoader;
	}
}
