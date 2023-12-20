package framework;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected int width, height;
	protected int scale;
	public static String ID;
	
	public GameObject(int x, int y, int width, int height, String ID) {
		this.x = x;
		this.y = y;
		this.width = width * scale;
		this.height = height * scale;
		this.ID = ID;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
    
	public abstract void render(Graphics g);
	public abstract void tick();

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
}
