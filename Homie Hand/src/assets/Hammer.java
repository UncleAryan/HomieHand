package assets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.Constants;
import framework.GameObject;
import framework.LoadSave;
	
public class Hammer extends GameObject {
	private BufferedImage image;
	private double angle;
	private AffineTransform resetImage;
	private AffineTransform rotatedImageInstance;
	private Graphics2D g2d;
	private BigPlayer bigPlayer;
	private boolean throwHammer;
	private double spinDirection;
	private int mouseX, mouseY;
	private int gravity;
	
	public Hammer(int x, int y, int width, int height, int scale, String ID, BigPlayer bigPlayer) {
		super(x, y, width, height, scale, ID);
		image = LoadSave.getSpriteSheet(LoadSave.HAMMER);
		angle = 45;
		this.bigPlayer = bigPlayer;
		throwHammer = false;
		spinDirection = 1;
		mouseX = 1;
		mouseY = 1;
		gravity = 1;
	}

	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		resetImage = g2d.getTransform();
	    rotatedImageInstance = AffineTransform.getRotateInstance(angle, x + scaledWidth/2, y + scaledHeight/2);
	    g2d.setTransform(rotatedImageInstance);
	    g2d.drawImage(image, x, y, scaledWidth, scaledHeight, null);
	    g2d.setTransform(resetImage);
	    
	    showBoundsOutline(g);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		if(throwHammer) {
			angle += 0.05 * spinDirection;
			
			if(angle >= 360) {
				angle = 0;
			}
			x += xSpeed;
			y += ySpeed;
			
			ySpeed = -1;
		} else {
			x = bigPlayer.getX() + bigPlayer.getScaledWidth()/4;
			y = (bigPlayer.getY() + bigPlayer.getScaledHeight()/3);
			
			if(bigPlayer.getAction() == 0 || bigPlayer.getAction() == 2) {
				angle = 45;
				xSpeed = 2;
				spinDirection = 1;
			} else {
				angle = -45;
				xSpeed = -2;
				spinDirection = -1;
				
			}
		}
		
		if(x > Constants.WIDTH || x < -scaledWidth) {
			throwHammer = false;
			resetPosition();
		}
	}
	
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	public void throwHammer() {
		throwHammer = true;
	}
	
	public void resetPosition() {
		x = bigPlayer.getX() + bigPlayer.getScaledWidth()/4;
		y = (bigPlayer.getY() + bigPlayer.getScaledHeight()/3);
	}

}
