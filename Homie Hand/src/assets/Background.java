package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;

public class Background extends GameObject {
	public Background(int x, int y, int width, int height, int scale, String ID) {
		super(x, y, width, height, scale, ID);
	}

	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, scaledWidth, scaledHeight);
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		
	}
	
}
