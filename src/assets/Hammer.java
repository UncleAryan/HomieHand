package assets;

import framework.CollisionHandler;
import framework.Constants;
import framework.GameObject;
import framework.ImageLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
	
public class Hammer extends GameObject {
	private BufferedImage hammer;
	private double angle;
	private AffineTransform resetImage;
	private AffineTransform rotatedImageInstance;
	private Graphics2D g2d;
	private BigPlayer bigPlayer;
	private boolean throwHammer;
	private double spinDirection;
	
	public Hammer(float x, float y, float width, float height, float scale, String ID, BigPlayer bigPlayer) {
		super(x, y, width, height, scale, ID);
		hammer = ImageLoader.getSpriteSheet(ImageLoader.HAMMER);
		angle = 45;
		this.bigPlayer = bigPlayer;
		throwHammer = false;
		spinDirection = 1;
		hammerWithBigPlayer = true;
		this.x = bigPlayer.getX() + bigPlayer.getScaledWidth()/4;
		this.y = bigPlayer.getY() + bigPlayer.getScaledHeight()/3;
	}

	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		resetImage = g2d.getTransform();
	    rotatedImageInstance = AffineTransform.getRotateInstance(angle, x + scaledWidth/2, y + scaledHeight/2);
	    g2d.setTransform(rotatedImageInstance);
	    g2d.drawImage(hammer, (int)x, (int)y, (int)scaledWidth, (int)scaledHeight, null);
	    g2d.setTransform(resetImage);
	}
	
	public void tick(LinkedList<GameObject> gameObjects) {
		if(throwHammer) {
			angle += 0.05 * spinDirection;
			
			x += xSpeed;
			y += ySpeed;
			
			ySpeed = -1;
		} else {
			updatePosition();
		}
		
		if(x > Constants.WIDTH || x < -scaledWidth || y > Constants.HEIGHT || y < -scaledHeight) {
			throwHammer = false;
			resetPosition();
		}
		
		CollisionHandler.tick(gameObjects, this);
		
		updateBounds();
	}
	
	public void updatePosition() {
		x = bigPlayer.getX() + bigPlayer.getScaledWidth()/4;
		y = bigPlayer.getY() + bigPlayer.getScaledHeight()/3;
		
		if(bigPlayer.getAction() == 0 || bigPlayer.getAction() == 2) {
			angle = 45;
			xSpeed = 3;
			spinDirection = 1;
		} else {
			angle = -45;
			xSpeed = -3;
			spinDirection = -1;
		}
	}
	
	public void throwHammer() {
		throwHammer = true;
	}
	
	public boolean getThrowHammer() {
		return throwHammer;
	}
	
	public void resetPosition() {
		x = bigPlayer.getX() + bigPlayer.getScaledWidth()/4;
		y = (bigPlayer.getY() + bigPlayer.getScaledHeight()/3);
	}
	
	public void startRotate() {
		angle += 0.05 * spinDirection;
		
		if(angle >= 360) {
			angle = 0;
		}
	}
	
	public double getAngle() {
		return angle;
	}
	
	public BufferedImage getHammerImage() {
		return hammer;
	}

}
