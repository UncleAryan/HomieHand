package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.Constants;
import framework.LoadSave;

public class Menu {
	private BufferedImage pointer;
	private BufferedImage[][] buttons;
	
	private int pointerScale;
	private int buttonScale;
	
	private int scaledPointerWidth;
	private int scaledPointerHeight;
	private int scaledButtonWidth;
	private int scaledButtonHeight;
	
	private int startButtonY;
	private int settingsButtonY;
	private int exitButtonY;
	private int centerXForAllButtons;
	private int pointerYStart;
	private int pointerYSettings;
	private int pointerYExit;
	private int pointerX;
	private int pointerY;
	
	private String pointerPointing;
	
	// used to determine if button should light up or not
	private int startButtonLight = 1;
	private int settingsButtonLight = 0;
	private int exitButtonLight = 0;
	
	public Menu() {
		loadImages();
		
		pointerScale = 2;
		buttonScale = 8;
		
		scaledPointerWidth = Constants.DEFAULT_POINTER_WIDTH * pointerScale;
		scaledPointerHeight = Constants.DEFAULT_POINTER_HEIGHT * pointerScale;
		scaledButtonWidth = Constants.DEFAULT_BUTTON_WIDTH * buttonScale;
		scaledButtonHeight = Constants.DEFAULT_BUTTON_HEIGHT * buttonScale;
		
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
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		
		// START button
		g.drawImage(buttons[0][startButtonLight], centerXForAllButtons, startButtonY,
				                   scaledButtonWidth, scaledButtonHeight, null);
		// SETTINGS button
		g.drawImage(buttons[3][settingsButtonLight], centerXForAllButtons, settingsButtonY,
                                   scaledButtonWidth, scaledButtonHeight, null);
		
		// EXIT button
		g.drawImage(buttons[1][exitButtonLight], centerXForAllButtons, exitButtonY,
		                           scaledButtonWidth, scaledButtonHeight, null);
		
		// pointer is initially pointing at START button
		g.drawImage(pointer, pointerX, pointerY,
						     scaledPointerWidth, scaledPointerHeight, null);
	}
	
	public void tick() {
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
		pointer = LoadSave.getSpriteSheet(LoadSave.POINTER);
		
		BufferedImage buttonsSpriteSheet = LoadSave.getSpriteSheet(LoadSave.BUTTONS_SPRITESHEET);
		buttons = new BufferedImage[4][2];
		for(int row = 0; row < buttons.length; row++) {
			for(int col = 0; col < buttons[row].length; col++) {
				buttons[row][col] = buttonsSpriteSheet.getSubimage(
						col * Constants.DEFAULT_BUTTON_WIDTH, row * Constants.DEFAULT_BUTTON_HEIGHT, 
						Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT);
			}
		}
	}
}
