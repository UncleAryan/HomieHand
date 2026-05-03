package assets;

import framework.*;

import java.awt.*;
import java.util.LinkedList;

public class SmallPlayer extends GameObject {

	private enum AnimationState {
		IDLE_RIGHT(0),
		IDLE_LEFT(1),
		JUMPING_RIGHT(2),
		JUMPING_LEFT(3);
		final int index;
		AnimationState(int index) { this.index = index; }
	}

	private AnimationLoader animationLoader;
	private AnimationState animationState;
	private float gravity;
	private boolean jumping;
	private float maxJump;
	private float jumpSpeed;

	public SmallPlayer(float x, float y, float width, float height, float scale, EntityType entityType) {
		super(x, y, width, height, scale, entityType);
		animationLoader = new AnimationLoader(30);
		animationState = AnimationState.IDLE_LEFT;
		gravity = Constants.GRAVITY * 0.5f;
		jumping = false;
		onGround = false;
		maxJump = y - scaledHeight * Constants.JUMP_HEIGHT_MULTIPLIER;
		jumpSpeed = Constants.JUMP_SPEED;
		animationLoader.loadAnimations(4, 19, originalWidth, originalHeight, ImageLoader.SMALLPLAYER_SPRITESHEET);
	}

	public void render(Graphics g) {
		g.drawImage(
			animationLoader.getAnimations()[animationState.index][animationLoader.getAnimationIndex()],
			(int) x, (int) y, (int) scaledWidth, (int) scaledHeight, null
		);
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		ySpeed += gravity;
		y += ySpeed;

		faceBigPlayer(gameObjects);

		if (jumping) {
			performJump();
		}

		animationLoader.tickAnimation();
		CollisionHandler.tick(gameObjects, this);
		updateBounds();
		updateMaxJump();
	}

	public void onLand() {
		onGround = true;
	}

	public void applyKnockback(float xSpeed, float ySpeed) {
		setXSpeed(xSpeed);
		setYSpeed(ySpeed);
	}

	private void faceBigPlayer(LinkedList<GameObject> gameObjects) {
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i).getEntityType() == EntityType.BIG_PLAYER) {
				animationState = gameObjects.get(i).getX() < x ? AnimationState.IDLE_LEFT : AnimationState.IDLE_RIGHT;
			}
		}
	}

	private void performJump() {
		animationState = animationState == AnimationState.IDLE_LEFT ? AnimationState.JUMPING_LEFT : AnimationState.JUMPING_RIGHT;

		if (animationLoader.getAnimationIndex() == Constants.JUMP_LAUNCH_FRAME) {
			animationLoader.setAnimationSpeed(15);
			ySpeed = -jumpSpeed;
			onGround = false;
		}

		if (y <= maxJump) {
			jumping = false;
			animationLoader.setAnimationSpeed(30);
		}
	}

	private void updateMaxJump() {
		if (onGround) {
			maxJump = y - scaledHeight * Constants.JUMP_HEIGHT_MULTIPLIER;
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
