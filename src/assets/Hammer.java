package assets;

import framework.*;

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
	
	public Hammer(float x, float y, float width, float height, float scale, EntityType entityType, BigPlayer bigPlayer) {
		super(x, y, width, height, scale, entityType);
		hammer = ImageLoader.getSpriteSheet(ImageLoader.HAMMER);
		angle = 45;
		this.bigPlayer = bigPlayer;
		throwHammer = false;
		spinDirection = 1;
		hammerWithBigPlayer = true;

		// my attempt to calculate relative position based on BigPlayer's dimensions
		float offsetX = bigPlayer.getScaledWidth() * 0.25f;
		float offsetY = bigPlayer.getScaledHeight() * 0.33f;

		this.x = bigPlayer.getX() + offsetX;
		this.y = bigPlayer.getY() + offsetY;
	}

	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		AffineTransform originalTransform = g2d.getTransform();
		g2d.translate(x + scaledWidth/2, y + scaledHeight/2);
		g2d.rotate(angle);
		g2d.translate(-scaledWidth/2, -scaledHeight/2);
		g2d.drawImage(hammer, 0, 0, (int)scaledWidth, (int)scaledHeight, null);
		g2d.setTransform(originalTransform);
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
		float offsetX = bigPlayer.getScaledWidth() * 0.25f;
		float offsetY = bigPlayer.getScaledHeight() * 0.33f;

		x = bigPlayer.getX() + offsetX;
		y = bigPlayer.getY() + offsetY;
		
		if(bigPlayer.getEntityState() == EntityState.IDLE_RIGHT || bigPlayer.getEntityState() == EntityState.WALKING_RIGHT) {
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
