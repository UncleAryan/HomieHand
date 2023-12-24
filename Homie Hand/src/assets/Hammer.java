package assets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.LoadSave;
	
public class Hammer extends GameObject {
	private BufferedImage image;
	private double angle;
	private AffineTransform resetImage;
	private AffineTransform rotatedImageInstance;
	private Graphics2D g2d;
	
	public Hammer(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		image = LoadSave.getSpriteSheet(LoadSave.HAMMER);
		angle = 0;
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
		angle += 0.05;
		
		if(angle >= 360) {
			angle = 0;
		}
		
		x++;
	}

}
