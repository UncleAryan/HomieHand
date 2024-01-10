package core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.Constants;
import framework.ImageLoader;

public class SettingsMenu {
	private BufferedImage[] arrowButtons;
	private BufferedImage[] fpsOptions;
	
	private int fpsSettingIndex;
	
	private int fpsOptionsScale;
	private int arrowButtonsScale;
	
	private int fpsOptionsX;
	private int fpsOptionsY;
	private int leftFpsOptionsArrowButtonX;
	private int rightFpsOptionsArrowButtonX;
	private int fpsOptionsArrowButtonY;
	
	private Panel panel;
	
	public SettingsMenu(Panel panel) {
		this.panel = panel;
		
		loadImages();
		
		fpsOptionsScale = 2;
		arrowButtonsScale = 4;
		
		fpsOptionsX = (Constants.WIDTH/2)-(Constants.DEFAULT_FPS_TEXTFIELD_WIDTH/2);
		fpsOptionsY = Constants.HEIGHT/3;
		leftFpsOptionsArrowButtonX = (fpsOptionsX - Constants.DEFAULT_ARROW_BUTTON_WIDTH) - Constants.DEFAULT_ARROW_BUTTON_WIDTH/10;
		rightFpsOptionsArrowButtonX = (fpsOptionsX - Constants.DEFAULT_ARROW_BUTTON_WIDTH) - Constants.DEFAULT_ARROW_BUTTON_WIDTH/10;
	}
	
	public void render(Graphics g) {
		// rendering same background and animations as main menu
		g.drawImage(panel.getMainMenu().getBackgroundImage(), 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
		panel.getMainMenu().renderAnimations(g);
		
		// settings
		g.drawImage(fpsOptions[0], fpsOptionsX, fpsOptionsY,
				    Constants.DEFAULT_FPS_TEXTFIELD_WIDTH * fpsOptionsScale, Constants.DEFAULT_FPS_TEXTFIELD_HEIGHT * fpsOptionsScale, null);
	}
	
	public void tick() {
		panel.getBigPlayer().getAnimationLoader().tickAnimation();
		panel.getSmallPlayer().getAnimationLoader().tickAnimation();
	}
	
	private void loadImages() {
		BufferedImage arrowButtonsSpriteSheet = ImageLoader.getSpriteSheet(ImageLoader.ARROW_BUTTONS_SPRITESHEET);
		arrowButtons = new BufferedImage[4];
		for(int i = 0; i < arrowButtons.length; i++) {
				arrowButtons[i]= arrowButtonsSpriteSheet.getSubimage(
						i * Constants.DEFAULT_ARROW_BUTTON_WIDTH, 0, 
						Constants.DEFAULT_ARROW_BUTTON_WIDTH, Constants.DEFAULT_ARROW_BUTTON_HEIGHT);
		}
		
		BufferedImage fpsTextFieldsSpriteSheet = ImageLoader.getSpriteSheet(ImageLoader.FPS_OPTIONS_SPRITESHEET);
		fpsOptions = new BufferedImage[8];
		for(int i = 0; i < arrowButtons.length; i++) {
				fpsOptions[i]= fpsTextFieldsSpriteSheet.getSubimage(
						i * Constants.DEFAULT_FPS_TEXTFIELD_WIDTH, 0, 
						Constants.DEFAULT_FPS_TEXTFIELD_WIDTH, Constants.DEFAULT_FPS_TEXTFIELD_HEIGHT);
		}
	}
}
