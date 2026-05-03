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
		angle = Constants.HAMMER_IDLE_ANGLE;
		this.bigPlayer = bigPlayer;
		throwHammer = false;
		spinDirection = 1;
		hammerWithBigPlayer = true;

		float offsetX = bigPlayer.getScaledWidth()  * Constants.HAMMER_OFFSET_X;
		float offsetY = bigPlayer.getScaledHeight() * Constants.HAMMER_OFFSET_Y;

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
			angle += Constants.HAMMER_SPIN_SPEED * spinDirection;

			x += xSpeed;
			y += ySpeed;

			ySpeed = Constants.HAMMER_FLOAT_SPEED;
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
		float offsetX = bigPlayer.getScaledWidth()  * Constants.HAMMER_OFFSET_X;
		float offsetY = bigPlayer.getScaledHeight() * Constants.HAMMER_OFFSET_Y;

		x = bigPlayer.getX() + offsetX;
		y = bigPlayer.getY() + offsetY;

		if (bigPlayer.getEntityState() == EntityState.IDLE_RIGHT || bigPlayer.getEntityState() == EntityState.WALKING_RIGHT) {
			angle = Constants.HAMMER_IDLE_ANGLE;
			xSpeed = Constants.HAMMER_THROW_SPEED;
			spinDirection = 1;
		} else {
			angle = -Constants.HAMMER_IDLE_ANGLE;
			xSpeed = -Constants.HAMMER_THROW_SPEED;
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
