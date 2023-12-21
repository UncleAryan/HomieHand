package framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import assets.Grass;

public class LevelHandler {
	public static ArrayList<GameObject> blocks;
	public static ArrayList<Grass> grass;
	
	public LevelHandler() {
		blocks = new ArrayList<>();
		grass = new ArrayList<>();
		loadLevel(LoadSave.getSpriteSheet(LoadSave.LEVEL_ONE));
	}
	
	public ArrayList<Grass> getGrass(){
		return grass;
	}
	
	public ArrayList<GameObject> getBlocks(){
		return blocks;
	}
	
	private void loadLevel(BufferedImage level) {
		for(int row = 0; row < level.getHeight(); row++) {
			for(int col = 0; col < level.getWidth(); col++) {
				int pixel = level.getRGB(row, col);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
			
				if(red == 255 && green == 255 && blue == 255) {
					grass.add(new Grass(row * 32, col * 32, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, "Grass")); 
				}
			}
		}
	}

	public void render(Graphics g) {
		for(int i = 0; i < grass.size(); i++) {
			grass.get(i).render(g);
		}
	}
	
	public void tick() {
		
	}
}
