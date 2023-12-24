package framework;

import java.util.LinkedList;

import assets.SmallPlayer;

public class CollisionHandler {
	public CollisionHandler() {
		
	}
	
	/*
	 * Collision handling algorithm adapted from RedFlyer Coding on YouTube.
	 */
	public void checkCollision(LinkedList<GameObject> gameObjects, GameObject player) {
		// horizontal collision
		player.getBounds().x += player.getXSpeed();
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).getID().equals("Block") && player.getBounds().intersects(gameObjects.get(i).getBounds())) {
				player.getBounds().x -= player.getXSpeed();
				while(!gameObjects.get(i).getBounds().intersects(player.getBounds())) {
					player.getBounds().x += Math.signum(player.getXSpeed());
				}
				player.getBounds().x -= Math.signum(player.getXSpeed());
				player.setXSpeed(0);
				player.setX(player.getBounds().x);
			} 
		}
		
		// vertical collision
		player.getBounds().y += player.getYSpeed();
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).getID().equals("Block") && player.getBounds().intersects(gameObjects.get(i).getBounds())) {
				player.getBounds().y -= player.getYSpeed();
				while(!gameObjects.get(i).getBounds().intersects(player.getBounds())) {
					player.getBounds().y += Math.signum(player.getYSpeed());
					if(player.getID().equals("SmallPlayer")) {
						SmallPlayer.onGround = true;
					} else {
						SmallPlayer.onGround = false;
					}
				}
				player.getBounds().y -= Math.signum(player.getYSpeed());
				player.setYSpeed(0);
				player.setY(player.getBounds().y);
				
			} 
		}
	}
}
