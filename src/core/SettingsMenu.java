package core;

import framework.Constants;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsMenu {
	private BufferedImage[] arrowButtons;
	private BufferedImage[] fpsOptions;
	private BufferedImage[] backButtons;
	private BufferedImage settingsMenuBackground;
	
	private int fpsSettingIndex;
	
	private float fpsOptionsScale;
	private float arrowButtonsScale;
	private float backButtonsScale;
	
	private float scaledFPSOptionsWidth;
	private float scaledFPSOptionsHeight;
	private float scaledArrowButtonWidth;
	private float scaledArrowButtonHeight;
	private float scaledBackButtonsWidth;
	private float scaledBackButtonsHeight;
	
	private float fpsOptionsX;
	private float fpsOptionsY;
	private float leftFPSOptionsArrowButtonX;
	private float rightFPSOptionsArrowButtonX;
	private float fpsOptionsArrowButtonY;
	private float backButtonsX;
	private float backButtonsY;
	
	private int leftFPSOptionsButtonLight;
	private int rightFPSOptionsButtonLight;
	private int backButtonLight;
	private boolean hoveringOverLeftFPSOptionsButton;
	private boolean hoveringOverRightFPSOptionsButton;
	private boolean hoveringOverBackButton;
	
	private Panel panel;
	
	public SettingsMenu(Panel panel) {
		this.panel = panel;
		
		loadImages();
		
		fpsOptionsScale = 2;
		arrowButtonsScale = 4;
		backButtonsScale = 2;
		
		scaledFPSOptionsWidth = Constants.DEFAULT_FPS_TEXTFIELD_WIDTH * fpsOptionsScale;
		scaledFPSOptionsHeight = Constants.DEFAULT_FPS_TEXTFIELD_HEIGHT * fpsOptionsScale;
		scaledArrowButtonWidth = Constants.DEFAULT_ARROW_BUTTON_WIDTH * arrowButtonsScale;
		scaledArrowButtonHeight = Constants.DEFAULT_ARROW_BUTTON_HEIGHT * arrowButtonsScale;
		scaledBackButtonsWidth = Constants.DEFAULT_BACK_BUTTON_WIDTH * backButtonsScale;
		scaledBackButtonsHeight = Constants.DEFAULT_BACK_BUTTON_HEIGHT * backButtonsScale;
		
		fpsOptionsX = (Constants.WIDTH/2)-(Constants.DEFAULT_FPS_TEXTFIELD_WIDTH/2);
		fpsOptionsY = Constants.HEIGHT/3;
		leftFPSOptionsArrowButtonX = (fpsOptionsX - scaledArrowButtonWidth) - scaledArrowButtonWidth/10;
		rightFPSOptionsArrowButtonX = (fpsOptionsX + scaledFPSOptionsWidth) + scaledArrowButtonWidth/10;
		fpsOptionsArrowButtonY = fpsOptionsY;
		backButtonsX = panel.getSmallPlayer().getScaledWidth()/2 + scaledBackButtonsWidth / 2;
		backButtonsY = panel.getBigPlayer().getScaledHeight()/4 + scaledBackButtonsWidth / 10;
		
		fpsSettingIndex = 3;

		leftFPSOptionsButtonLight = 0;
		rightFPSOptionsButtonLight = 3;
		backButtonLight = 0;
		hoveringOverLeftFPSOptionsButton = false;
		hoveringOverRightFPSOptionsButton = false;
		hoveringOverBackButton = false;
	}
	
	public void render(Graphics g) {
		// rendering same background and animations as main menu
		g.drawImage(settingsMenuBackground, 0, 0, (int)Constants.WIDTH, (int)Constants.HEIGHT, null);
		panel.getMainMenu().renderAnimations(g);
		
		// settings
		g.drawImage(fpsOptions[fpsSettingIndex], (int)fpsOptionsX, (int)fpsOptionsY,
				(int)scaledFPSOptionsWidth, (int)scaledFPSOptionsHeight, null);
		g.drawImage(arrowButtons[leftFPSOptionsButtonLight], (int)leftFPSOptionsArrowButtonX, (int)fpsOptionsArrowButtonY,
				(int)scaledArrowButtonWidth, (int)scaledArrowButtonHeight, null);
		g.drawImage(arrowButtons[rightFPSOptionsButtonLight], (int)rightFPSOptionsArrowButtonX, (int)fpsOptionsArrowButtonY,
				(int)scaledArrowButtonWidth, (int)scaledArrowButtonHeight, null);
		g.drawImage(backButtons[backButtonLight], (int)backButtonsX, (int)backButtonsY, 
				(int)scaledBackButtonsWidth, (int)scaledBackButtonsHeight, null);
	}
	
	public void tick() {
		panel.getBigPlayer().getAnimationLoader().tickAnimation();
		panel.getSmallPlayer().getAnimationLoader().tickAnimation();
		
		if(hoveringOverLeftFPSOptionsButton) {
			leftFPSOptionsButtonLight = 1;
		} else {
			leftFPSOptionsButtonLight = 0;
		}
		
		if(hoveringOverRightFPSOptionsButton) {
			rightFPSOptionsButtonLight = 2;
		} else {
			rightFPSOptionsButtonLight = 3;
		}
		
		if(fpsSettingIndex < 0) {
			fpsSettingIndex = 7;
		}
		if(fpsSettingIndex > 7) {
			fpsSettingIndex = 0;
		}
		
		if(hoveringOverBackButton) {
			backButtonLight = 1;
		} else {
			backButtonLight = 0;
		}
		
	}
	
	public void hoveringOverBackButton(boolean value) {
		hoveringOverBackButton = value;
	}
	public void hoveringOverLeftFPSOptionsButton(boolean value) {
		hoveringOverLeftFPSOptionsButton = value;
	}
	public void hoveringOverRightFPSOptionsButton(boolean value) {
		hoveringOverRightFPSOptionsButton = value;
	}
	
	private void loadImages() {
		settingsMenuBackground = ImageLoader.getSpriteSheet(ImageLoader.SETTINGS_MENU_BACKGROUND);
		
		BufferedImage arrowButtonsSpriteSheet = ImageLoader.getSpriteSheet(ImageLoader.ARROW_BUTTONS_SPRITESHEET);
		arrowButtons = new BufferedImage[4];
		for(int i = 0; i < arrowButtons.length; i++) {
				arrowButtons[i]= arrowButtonsSpriteSheet.getSubimage(
						i * Constants.DEFAULT_ARROW_BUTTON_WIDTH, 0, 
						Constants.DEFAULT_ARROW_BUTTON_WIDTH, Constants.DEFAULT_ARROW_BUTTON_HEIGHT);
		}
		
		BufferedImage fpsTextFieldsSpriteSheet = ImageLoader.getSpriteSheet(ImageLoader.FPS_OPTIONS_SPRITESHEET);
		fpsOptions = new BufferedImage[8];
		for(int i = 0; i < fpsOptions.length; i++) {
				fpsOptions[i]= fpsTextFieldsSpriteSheet.getSubimage(
						i * Constants.DEFAULT_FPS_TEXTFIELD_WIDTH, 0, 
						Constants.DEFAULT_FPS_TEXTFIELD_WIDTH, Constants.DEFAULT_FPS_TEXTFIELD_HEIGHT);
		}
		
		BufferedImage backButtonsSpriteSheet = ImageLoader.getSpriteSheet(ImageLoader.BACK_BUTTON_SPRITESHEET);
		backButtons = new BufferedImage[2];
		for(int i = 0; i < backButtons.length; i++) {
			backButtons[i] = backButtonsSpriteSheet.getSubimage(i * Constants.DEFAULT_BACK_BUTTON_WIDTH, 0, 
							 Constants.DEFAULT_BACK_BUTTON_WIDTH, Constants.DEFAULT_BACK_BUTTON_HEIGHT);
		}
	}
	
	public int getFPSValue() {
		if(fpsSettingIndex == 0) {
			return 30;
		} else if(fpsSettingIndex == 1) {
			return 60;
		} else if(fpsSettingIndex == 2) {
			return 75;
		} else if(fpsSettingIndex == 3) {
			return 120;
		} else if(fpsSettingIndex == 4) {
			return 144;
		} else if(fpsSettingIndex == 5) {
			return 165;
		} else if(fpsSettingIndex == 6) {
			return 240;
		} else {
			return 360;
		}
	}
	public float getBackButtonsX() {
		return backButtonsX;
	}
	public float getBackButtonsY() {
		return backButtonsY;
	}
	public float getScaledBackButtonsWidth() {
		return scaledBackButtonsWidth;
	}
	public float getScaledBackButtonsHeight() {
		return scaledBackButtonsHeight;
	}
	public int getFPSSettingIndex() {
		return fpsSettingIndex;
	}
	public void setFPSSettingIndex(int fpsSettingIndex) {
		this.fpsSettingIndex = fpsSettingIndex;
	}
	public float getLeftFPSOptionsArrowButtonX() {
		return leftFPSOptionsArrowButtonX;
	}
	public float getRightFPSOptionsArrowButtonX() {
		return rightFPSOptionsArrowButtonX;
	}
	public float getScaledArrowButtonWidth() {
		return scaledArrowButtonWidth;
	}
	public float getScaledArrowButtonHeight() {
		return scaledArrowButtonHeight;
	}
	public float getFPSOptionsArrowButtonY() {
		return fpsOptionsArrowButtonY;
	}
	public boolean getHoveringOverLeftFPSOptionsButton() {
		return hoveringOverLeftFPSOptionsButton;
	}
	public boolean getHoveringOverRightFPSOptionsButton() {
		return hoveringOverRightFPSOptionsButton;
	}
	public boolean getHoveringOverBackButton() {
		return hoveringOverBackButton;
	}
}
