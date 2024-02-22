package framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	protected float x, y;
	protected float xSpeed, ySpeed;
	protected float originalWidth, originalHeight;
	protected float scaledWidth, scaledHeight;
	protected float scale;
	protected String ID;
	protected Rectangle bounds;
	protected boolean onGround;
	protected boolean hammerWithBigPlayer;
	
	public GameObject(float x, float y, float width, float height, float scale, String ID) {
		this.x = x;
		this.y = y;
		originalWidth = width;
		originalHeight = height;
		this.scale = scale;
		scaledWidth = width * scale;
		scaledHeight = height * scale;
		this.ID = ID;
		bounds = new Rectangle((int)x, (int)y, (int)scaledWidth, (int)scaledHeight);
		onGround = false;
	}
	
	public void setHammerWithBigPlayer(boolean b) {
		hammerWithBigPlayer = b;
	}
	
	public boolean isHammerWithBigPlayer() {
		return hammerWithBigPlayer;
	}
	
	public boolean isOnGround() {
		return onGround;
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
    
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void updateBounds() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}
	
	public abstract void render(Graphics g);
	public abstract void tick(LinkedList<GameObject> gameObjects);

	public void showBoundsOutline(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)x, (int)y, (int)scaledWidth, (int)scaledHeight);
	}
	
	public float getOriginalWidth() {
		return originalWidth;
	}
	public float getOrignialHeight() {
		return originalHeight;
	}
	public float getScaledWidth() {
		return scaledWidth;
	}
	public float getScaledHeight() {
		return scaledHeight;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public String getID() {
		return ID;
	}
	public void setXSpeed(float speed) {
		xSpeed = speed;
	}
	public void setYSpeed(float speed) {
		ySpeed = speed;
	}
	public float getXSpeed() {
		return xSpeed;
	}
	public float getYSpeed() {
		return ySpeed;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
}
