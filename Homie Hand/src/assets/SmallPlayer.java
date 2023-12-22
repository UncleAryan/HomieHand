package assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.AnimationTicker;
import framework.GameObject;
import framework.LoadSave;

public class SmallPlayer extends GameObject {
	private BufferedImage[][] animations;
	private AnimationTicker animationTicker;
	private Rectangle bounds;
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = jumping right
	 * 3 = jumping left
	 */
	private int action;
	private int scale;
	
	public SmallPlayer(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		scale = 2;
		animationTicker = new AnimationTicker(25);
		bounds = new Rectangle(x, y, width * scale, height * scale);
		action = 2; // starts off jumping right
		
		loadAnimations();
	}

	public void render(Graphics g) {
		
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		animationTicker.tickAnimation();
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	private void loadAnimations() {
		BufferedImage image = LoadSave.getSpriteSheet(LoadSave.SMALLPLAYER_SPRITESHEET);
		
		animations = new BufferedImage[2][19];
		for(int row = 0; row < animations.length; row++) {
			for(int col = 0; col < animations[row].length; col++) {
				animations[row][col] = image.getSubimage(col * width, row * height, 32, 32);
			}
		}
	}

}
