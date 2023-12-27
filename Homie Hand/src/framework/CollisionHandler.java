package framework;

import java.util.LinkedList;

import assets.SmallPlayer;

/*
 * Will handle all collisions in game.
 */
public class CollisionHandler {
	
	public CollisionHandler() {
		
	}
	
	/*
	 * Collision handling algorithm adapted from RedFlyer Coding on YouTube.
	 */
	public void checkCollision(LinkedList<GameObject> gameObjects, GameObject objectColliding) {
		// horizontal collision
		objectColliding.getBounds().x += objectColliding.getXSpeed();
		for(int i = 0; i < gameObjects.size(); i++) {
			// blocks to player collision
			if(gameObjects.get(i).getID().equals("Block") && objectColliding.getBounds().intersects(gameObjects.get(i).getBounds()) &&
			  (objectColliding.getID().equals("BigPlayer") || objectColliding.getID().equals("SmallPlayer"))) {
				objectColliding.getBounds().x -= objectColliding.getXSpeed();
				while(!gameObjects.get(i).getBounds().intersects(objectColliding.getBounds())) {
					objectColliding.getBounds().x += Math.signum(objectColliding.getXSpeed());
				}
				objectColliding.getBounds().x -= Math.signum(objectColliding.getXSpeed());
				objectColliding.setXSpeed(0);
				objectColliding.setX(objectColliding.getBounds().x);
			} 
			
			if(gameObjects.get(i).getID().equals("SmallPlayer") && objectColliding.getID().equals("Hammer") &&
			   objectColliding.getBounds().intersects(gameObjects.get(i).getBounds())) {
				System.out.println("It works");
				System.out.println("x:" + objectColliding.getX() + " | y: " + objectColliding.getY());
				
			}
		}
		
		// vertical collision
		objectColliding.getBounds().y += objectColliding.getYSpeed();
		for(int i = 0; i < gameObjects.size(); i++) {
			// block to players
			if(gameObjects.get(i).getID().equals("Block") && objectColliding.getBounds().intersects(gameObjects.get(i).getBounds()) &&
			  (objectColliding.getID().equals("BigPlayer") || objectColliding.getID().equals("SmallPlayer"))) {
				objectColliding.getBounds().y -= objectColliding.getYSpeed();
				while(!gameObjects.get(i).getBounds().intersects(objectColliding.getBounds())) {
					objectColliding.getBounds().y += Math.signum(objectColliding.getYSpeed());
					if(objectColliding.getID().equals("SmallPlayer")) {
						SmallPlayer.onGround = true;
					} else {
						SmallPlayer.onGround = false;
					}
				}
				objectColliding.getBounds().y -= Math.signum(objectColliding.getYSpeed());
				objectColliding.setYSpeed(0);
				objectColliding.setY(objectColliding.getBounds().y);
			}
			
			if(gameObjects.get(i).getID().equals("SmallPlayer") && objectColliding.getID().equals("Hammer") &&
				objectColliding.getBounds().intersects(gameObjects.get(i).getBounds())) {
				objectColliding.getBounds().y -= objectColliding.getYSpeed();	
				while(!gameObjects.get(i).getBounds().intersects(objectColliding.getBounds())) {
					objectColliding.getBounds().y += Math.signum(objectColliding.getYSpeed());
					System.out.println("It works");
				}
				
			}
		}
	}
}
