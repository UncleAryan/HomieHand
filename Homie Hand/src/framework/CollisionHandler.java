package framework;

import java.awt.Graphics;
import java.util.ArrayList;

import assets.BigPlayer;
import assets.Grass;
import core.Panel;

public class CollisionHandler {
	private BigPlayer bigPlayer;
	private ArrayList<GameObject> blocks;
	private ArrayList<Grass> grass;
	
	public CollisionHandler(Panel panel) {
		bigPlayer = panel.getBigPlayer();
		blocks = panel.getLevelHandler().getBlocks();
		grass = panel.getLevelHandler().getGrass();
	}
	
	public void tick() {
		for(int i = 0; i < blocks.size(); i++) {
			if(bigPlayer.getBounds().intersects(grass.get(i).getBounds())) {
				bigPlayer.setGravity(0);
				System.out.println("asdfasd");
			}
		}
		
	}
	
	public void render(Graphics g) {
		
	}
}
