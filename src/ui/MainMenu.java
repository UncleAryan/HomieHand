package ui;

import core.Panel;
import framework.Constants;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu {
	private BufferedImage background;

	private float buttonScale;
	private float bigPlayerScale;
	private float smallPlayerScale;

    private final float scaledBigPlayerWidth, scaledBigPlayerHeight;
	private final float scaledSmallPlayerWidth, scaledSmallPlayerHeight;

    private final float bigPlayerX, bigPlayerY;
	private final float smallPlayerX, smallPlayerY;

	private final Panel panel;

	private final ButtonHandler buttonHandler;

    public MainMenu(Panel panel) {
		this.panel = panel;
		
		loadImages();

		pointerScale = 2;
		buttonScale = 8;
		bigPlayerScale = 4;
		smallPlayerScale = 6;

        float scaledButtonWidth = Constants.DEFAULT_BUTTON_WIDTH * buttonScale;
        float scaledButtonHeight = Constants.DEFAULT_BUTTON_HEIGHT * buttonScale;

		scaledBigPlayerWidth = Constants.DEFAULT_GAMEOBJECT_WIDTH * bigPlayerScale;
		scaledBigPlayerHeight = Constants.DEFAULT_GAMEOBJECT_HEIGHT * bigPlayerScale;
		scaledSmallPlayerHeight = Constants.DEFAULT_GAMEOBJECT_HEIGHT * smallPlayerScale;
		scaledSmallPlayerWidth = Constants.DEFAULT_GAMEOBJECT_WIDTH * smallPlayerScale;

        float centerXForAllButtons = Constants.WIDTH / 2 - (scaledButtonWidth / 2);
        float startButtonY = Constants.HEIGHT / 3;
        float settingsButtonY = (startButtonY + scaledButtonHeight) + scaledButtonHeight / 10;
        float exitButtonY = (settingsButtonY + scaledButtonHeight) + scaledButtonHeight / 10;

        bigPlayerX = Constants.WIDTH * 0.1f;
		bigPlayerY = startButtonY + scaledButtonHeight /2;
		smallPlayerX = Constants.WIDTH * 0.8f;
		smallPlayerY = settingsButtonY + scaledButtonHeight /2;

		// NEW IMPLEMENTATION OF UI STARTS HERE
		buttonHandler = new ButtonHandler();
        Button start = new Button(centerXForAllButtons, startButtonY, Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT, buttonScale, UIType.START_BUTTON);
        Button settings = new Button(centerXForAllButtons, settingsButtonY, Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT, buttonScale, UIType.SETTINGS_BUTTON);
        Button exit = new Button(centerXForAllButtons, exitButtonY, Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT, buttonScale, UIType.EXIT_BUTTON);
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

	private void loadImages() {
		background = ImageLoader.getSpriteSheet(ImageLoader.MENU_BACKGROUND);
	}
	
	public void renderAnimations(Graphics g) {
		// big player animation
		g.drawImage(panel.getBigPlayer().getAnimationLoader().getAnimations()[0][panel.getBigPlayer().getAnimationLoader().getAnimationIndex()],
				(int)bigPlayerX, (int)bigPlayerY, (int)scaledBigPlayerWidth, (int)scaledBigPlayerHeight, null);
				
		// small player animation
	    g.drawImage(panel.getSmallPlayer().getAnimationLoader().getAnimations()[1][panel.getSmallPlayer().getAnimationLoader().getAnimationIndex()],
	    		(int)smallPlayerX, (int)smallPlayerY, (int)scaledSmallPlayerWidth, (int)scaledSmallPlayerHeight, null);
	}

	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}
}
