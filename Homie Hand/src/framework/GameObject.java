package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	protected int x, y;
	protected int xSpeed, ySpeed;
	protected int originalWidth, originalHeight;
	protected int scaledWidth, scaledHeight;
	protected int scale;
	protected String ID;
	
	public GameObject(int x, int y, int width, int height, int scale, String ID) {
		this.x = x;
		this.y = y;
		originalWidth = width;
		originalHeight = height;
		this.scale = scale;
		scaledWidth = width * scale;
		scaledHeight = height * scale;
		this.ID = ID;
	}
    
	public abstract void render(Graphics g);
	public abstract void tick(LinkedList<GameObject> gameObjects);
	public abstract Rectangle getBounds();

	public int getOriginalWidth() {
		return originalWidth;
	}
	public int getOrignialHeight() {
		return originalHeight;
	}
	public int getScaledWidth() {
		return scaledWidth;
	}
	public int getScaledHeight() {
		return scaledHeight;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getID() {
		return ID;
	}
	public void setXSpeed(int speed) {
		xSpeed = speed;
	}
	public void setYSpeed(int speed) {
		ySpeed = speed;
	}
	public int getXSpeed() {
		return xSpeed;
	}
	public int getYSpeed() {
		return ySpeed;
	}
}
