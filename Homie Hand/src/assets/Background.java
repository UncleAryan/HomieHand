package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ImageLoader;

public class Background extends GameObject {
	private BufferedImage cloudyBackground;
	
	public Background(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		cloudyBackground = ImageLoader.getSpriteSheet(ImageLoader.CLOUDY_BACKGROUND);
		xSpeed = -1;	
	}

	public void render(Graphics g) {
		g.drawImage(cloudyBackground, x, y, scaledWidth, scaledHeight, null);
		if(x <= 0) {
			g.drawImage(cloudyBackground, x + scaledWidth, y, scaledWidth, scaledHeight, null);
		}
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		if(x <= -scaledWidth) {
			x = 0;
		}
	}
	
}
