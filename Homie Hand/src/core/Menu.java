package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import framework.Constants;

public class Menu {
	public Menu() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		g.setFont(new Font("Ariel", Font.BOLD, 50));
		g.setColor(Color.WHITE);
		g.drawString("Homie Hand", Constants.WIDTH/3, 100);
		g.setFont(new Font("Ariel", Font.BOLD, 25));
		g.drawString("Click Mouse To START", Constants.WIDTH/3, 500);
	}
	
	public void tick() {
		
	}
}
