package core;

import framework.Constants;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu {
	private BufferedImage background;
	private BufferedImage pointer;
	private BufferedImage[][] buttons;
	
	private float pointerScale;
	private float buttonScale;
	private float bigPlayerScale;
	private float smallPlayerScale;
	
	private float scaledPointerWidth;
	private float scaledPointerHeight;
	private float scaledButtonWidth;
	private float scaledButtonHeight;
	private float scaledBigPlayerWidth, scaledBigPlayerHeight;
	private float scaledSmallPlayerWidth, scaledSmallPlayerHeight;
	
	private float startButtonY;
	private float settingsButtonY;
	private float exitButtonY;
	private float centerXForAllButtons;
	private float pointerYStart;
	private float pointerYSettings;
	private float pointerYExit;
	private float pointerX;
	private float pointerY;
	private float bigPlayerX, bigPlayerY;
	private float smallPlayerX, smallPlayerY;
	
	private String pointerPointing;
	
	// used to determine if button should light up or not
	private int startButtonLight = 1;
	private int settingsButtonLight = 0;
	private int exitButtonLight = 0;
	
	private Panel panel;
	
	public MainMenu(Panel panel) {
		this.panel = panel;
		
		loadImages();
		
		pointerScale = 2;
		buttonScale = 8;
		bigPlayerScale = 4;
		smallPlayerScale = 6;
		
		scaledPointerWidth = Constants.DEFAULT_POINTER_WIDTH * pointerScale;
		scaledPointerHeight = Constants.DEFAULT_POINTER_HEIGHT * pointerScale;
		scaledButtonWidth = Constants.DEFAULT_BUTTON_WIDTH * buttonScale;
		scaledButtonHeight = Constants.DEFAULT_BUTTON_HEIGHT * buttonScale;
		scaledBigPlayerWidth = Constants.DEFAULT_GAMEOBJECT_WIDTH * bigPlayerScale;
		scaledBigPlayerHeight = Constants.DEFAULT_GAMEOBJECT_HEIGHT * bigPlayerScale;
		scaledSmallPlayerHeight = Constants.DEFAULT_GAMEOBJECT_HEIGHT * smallPlayerScale;
		scaledSmallPlayerWidth = Constants.DEFAULT_GAMEOBJECT_WIDTH * smallPlayerScale;
		
		centerXForAllButtons = Constants.WIDTH/2-(scaledButtonWidth/2);
		startButtonY = Constants.HEIGHT/3;
		settingsButtonY = (startButtonY + scaledButtonHeight) + scaledButtonHeight/10;
		exitButtonY = (settingsButtonY + scaledButtonHeight) + scaledButtonHeight/10;
		
		// pointer's x position will stay the same
		pointerX = (centerXForAllButtons - scaledPointerWidth) - scaledPointerWidth/10;
		// pointer's y position will change based on input and starts pointing at START
		pointerYStart = (startButtonY + scaledButtonHeight/2) - scaledPointerHeight/2;
		pointerYSettings = (settingsButtonY + scaledButtonHeight/2) - scaledPointerHeight/2;
		pointerYExit = (exitButtonY + scaledButtonHeight/2) - scaledPointerHeight/2;
		pointerY = pointerYStart;
		pointerPointing = "START";
		
		bigPlayerX = pointerX - (scaledButtonWidth + scaledPointerWidth);
		bigPlayerY = startButtonY + scaledButtonHeight/2;
		smallPlayerX = pointerX + (scaledButtonWidth*2 + scaledPointerWidth);
		smallPlayerY = settingsButtonY + scaledButtonHeight/2;
	}
	
	public void render(Graphics g) {
		// background
		g.drawImage(background, 0, 0, (int)Constants.WIDTH, (int)Constants.HEIGHT, null);
		
		// START button
		g.drawImage(buttons[0][startButtonLight], (int)centerXForAllButtons, (int)startButtonY,
				(int)scaledButtonWidth, (int)scaledButtonHeight, null);
		// SETTINGS button
		g.drawImage(buttons[3][settingsButtonLight], (int)centerXForAllButtons, (int)settingsButtonY,
				(int)scaledButtonWidth, (int)scaledButtonHeight, null);
		
		// EXIT button
		g.drawImage(buttons[1][exitButtonLight], (int)centerXForAllButtons, (int)exitButtonY,
				(int)scaledButtonWidth, (int)scaledButtonHeight, null);
		
		// pointer is initially pointing at START button
		g.drawImage(pointer, (int)pointerX, (int)pointerY,
				(int)scaledPointerWidth, (int)scaledPointerHeight, null);
		
		renderAnimations(g);
	}
	
	public void tick() {
		panel.getBigPlayer().getAnimationLoader().tickAnimation();
		panel.getSmallPlayer().getAnimationLoader().tickAnimation();
		
		switch(pointerPointing) {
		case "START":
			pointerY = pointerYStart;
			startButtonLight = 1;
			settingsButtonLight = 0;
			exitButtonLight = 0;
			break;
		case "SETTINGS":
			pointerY = pointerYSettings;
			startButtonLight = 0;
			settingsButtonLight = 1;
			exitButtonLight = 0;
			break;
		case "EXIT":
			pointerY = pointerYExit;
			startButtonLight = 0;
			settingsButtonLight = 0;
			exitButtonLight = 1;
			break;
		}
	}
	
	public String getPointerPointing() {
		return pointerPointing;
	}
	public void movePointerDown() {
		switch(pointerPointing) {
		case "START":
			pointerPointing = "SETTINGS";
			break;
		case "SETTINGS":
			pointerPointing = "EXIT";
			break;
		case "EXIT":
			pointerPointing = "START";
			break;
		}
	}
	
	public void movePointerUp() {
		switch(pointerPointing) {
		case "START":
			pointerPointing = "EXIT";
			break;
		case "SETTINGS":
			pointerPointing = "START";
			break;
		case "EXIT":
			pointerPointing = "SETTINGS";
			break;
		}
	}
	
	private void loadImages() {
		background = ImageLoader.getSpriteSheet(ImageLoader.MENU_BACKGROUND);
		pointer = ImageLoader.getSpriteSheet(ImageLoader.POINTER);
		
		BufferedImage buttonsSpriteSheet = ImageLoader.getSpriteSheet(ImageLoader.BUTTONS_SPRITESHEET);
		buttons = new BufferedImage[4][2];
		for(int row = 0; row < buttons.length; row++) {
			for(int col = 0; col < buttons[row].length; col++) {
				buttons[row][col] = buttonsSpriteSheet.getSubimage(
						col * Constants.DEFAULT_BUTTON_WIDTH, row * Constants.DEFAULT_BUTTON_HEIGHT, 
						Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT);
			}
		}
	}
	
	public BufferedImage getBackgroundImage() {
		return background;
	}
	
	public void renderAnimations(Graphics g) {
		// big player animation
		g.drawImage(panel.getBigPlayer().getAnimationLoader().getAnimations()[0][panel.getBigPlayer().getAnimationLoader().getAnimationIndex()],
				(int)bigPlayerX, (int)bigPlayerY, (int)scaledBigPlayerWidth, (int)scaledBigPlayerHeight, null);
				
		// small player animation
	    g.drawImage(panel.getSmallPlayer().getAnimationLoader().getAnimations()[1][panel.getSmallPlayer().getAnimationLoader().getAnimationIndex()],
	    		(int)smallPlayerX, (int)smallPlayerY, (int)scaledSmallPlayerWidth, (int)scaledSmallPlayerHeight, null);
	}
}
