package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import framework.Constants;
import framework.GameObject;

public class Background extends GameObject {
	public Background(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
		xSpeed = -1;	
	}

	public void render(Graphics g) {
		//g.drawImage(cloudyBackground, x, y, scaledWidth, scaledHeight, null);
		//if(x <= 0) {
		//	g.drawImage(cloudyBackground, x + scaledWidth, y, scaledWidth, scaledHeight, null);
		//}
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, (int)Constants.WIDTH, (int)Constants.HEIGHT);
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
}
