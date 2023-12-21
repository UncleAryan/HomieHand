package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	protected int x, y;
	protected int xSpeed, ySpeed;
	protected int width, height;
	protected int scale;
	protected String ID;
	
	public GameObject(int x, int y, int width, int height, String ID) {
		this.x = x;
		this.y = y;
		this.width = width * scale;
		this.height = height * scale;
		this.ID = ID;
	}
    
	public abstract void render(Graphics g);
	public abstract void tick(LinkedList<GameObject> gameObjects);
	public abstract Rectangle getBounds();

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
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
