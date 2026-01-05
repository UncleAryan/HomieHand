package ui;

import core.Panel;
import framework.Constants;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu {
	private BufferedImage background;
	private BufferedImage pointer;
	
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

	private Panel panel;

	private ButtonHandler buttonHandler;
	private Button start, settings, exit;
	
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

		// NEW IMPLEMENTATION OF UI STARTS HERE
		buttonHandler = new ButtonHandler();
		start = new Button(centerXForAllButtons, startButtonY, Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT, buttonScale, UIType.START_BUTTON);
		settings = new Button(centerXForAllButtons, settingsButtonY, Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT, buttonScale, UIType.SETTINGS_BUTTON);
		exit = new Button(centerXForAllButtons, exitButtonY, Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT, buttonScale, UIType.EXIT_BUTTON);
		buttonHandler.addButton(start);
		buttonHandler.addButton(settings);
		buttonHandler.addButton(exit);
	}
	
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, (int)Constants.WIDTH, (int)Constants.HEIGHT, null);

		buttonHandler.render(g);

		renderAnimations(g);
	}
	
	public void tick() {
		panel.getBigPlayer().getAnimationLoader().tickAnimation();
		panel.getSmallPlayer().getAnimationLoader().tickAnimation();
		int mouseX = panel.getMouseInput().getMouseX();
		int mouseY = panel.getMouseInput().getMouseY();
		buttonHandler.tick(mouseX, mouseY);
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
