package assets;

import framework.*;

import java.awt.*;
import java.util.LinkedList;

public class BigPlayer extends GameObject {
	// r = 255, g = 255, b = 255

	private float gravity;
	private AnimationLoader animationLoader;
	private Hammer hammer;
	
	public BigPlayer(float x, float y, float width, float height, float scale, EntityType entityType) {
		super(x, y, width, height, scale, entityType);
		animationLoader = new AnimationLoader(25);
		entityState = EntityState.IDLE_RIGHT;
		gravity = 1;
		animationLoader.loadAnimations(4, 9, originalWidth, originalHeight, ImageLoader.BIGPLAYER_SPRITESHEET);
		hammer = new Hammer(x, y, width, height, 2, EntityType.HAMMER, this);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		hammer.tick(gameObjects);
		
		if(hammer.getThrowHammer()) {
			hammer.setHammerWithBigPlayer(false);
			
		} else {
			hammer.setHammerWithBigPlayer(true);
		}
		
		animationLoader.tickAnimation();

		CollisionHandler.tick(gameObjects, this);
	}
	
	public void render(Graphics g) {
		g.drawImage(animationLoader.getAnimations()[getAction()][animationLoader.getAnimationIndex()], (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
		
		hammer.render(g);
	}

	public int getAction() {
		if (entityState == EntityState.IDLE_RIGHT) {
			return 0;
		} else if (entityState == EntityState.IDLE_LEFT) {
			return 1;
		} else if (entityState == EntityState.WALKING_RIGHT) {
			return 2;
		} else if (entityState == EntityState.WALKING_LEFT){
			return 3;
		}
		return -1;
	}

	public AnimationLoader getAnimationLoader() {
		return animationLoader;
	}
	
	public Hammer getHammer() {
		return hammer;
	}
}
