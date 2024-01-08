package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSlider;

import framework.Constants;
import framework.GameState;

public class SettingsMenu {
	
	
	public SettingsMenu(Panel panel) {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
	}
	
	public void tick() {
		
	}
}
