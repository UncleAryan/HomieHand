package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.AnimationTicker;
import framework.CollisionHandler;
import framework.GameObject;
import framework.LoadSave;

public class SmallPlayer extends GameObject {
	private BufferedImage[][] animations;
	private AnimationTicker animationTicker;
	private CollisionHandler collisionHandler;
	private Rectangle bounds;
	
	/*
	 * 0 = idle right
	 * 1 = idle left
	 * 2 = walking right
	 * 3 = walking left
	 */
	private int action;
	private int scale;
	private int gravity;
	
	public SmallPlayer(int x, int y, int width, int height, String ID) {
		super(x, y, width, height, ID);
		this.width = width;
		this.height = height;
		scale = 4;
		animationTicker = new AnimationTicker(30, 19);
		bounds = new Rectangle(x, y, width * scale, height * scale);
		action = 1; // starts off jumping right
		collisionHandler = new CollisionHandler();
		gravity = 1;
		
		loadAnimations();
	}

	public void render(Graphics g) {
		g.drawImage(animations[action][animationTicker.getAnimationIndex()], x, y, width * scale, height * scale, null);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width * scale, height * scale);
	}

	public void tick(LinkedList<GameObject> gameObjects) {
		x += xSpeed;
		y += ySpeed;
		ySpeed = gravity;
		
		collisionHandler.checkCollision(gameObjects, this);
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
