package framework;

import java.util.LinkedList;

/*
 * Will handle all collisions in game.
 */
public class CollisionHandler {

	/*
	 * Collision handling algorithm implemented here was shown by RedFlyer Coding on
	 * YouTube.
	 */
	public static void tick(LinkedList<GameObject> gameObjects, GameObject objectColliding) {
		// horizontal collision
		objectColliding.getBounds().x += objectColliding.getXSpeed();
		for (int i = 0; i < gameObjects.size(); i++) {
			// blocks to player collision
			if (gameObjects.get(i).getID().equals("Block")
					&& objectColliding.getBounds().intersects(gameObjects.get(i).getBounds())
					&& (objectColliding.getID().equals("BigPlayer") || objectColliding.getID().equals("SmallPlayer"))) {
				objectColliding.getBounds().x -= objectColliding.getXSpeed();
				while (!gameObjects.get(i).getBounds().intersects(objectColliding.getBounds())) {
					objectColliding.getBounds().x += Math.signum(objectColliding.getXSpeed());
				}
				objectColliding.getBounds().x -= Math.signum(objectColliding.getXSpeed());
				objectColliding.setXSpeed(0);
				objectColliding.setX(objectColliding.getBounds().x);
			}

			// hammer to small player collision
			if (gameObjects.get(i).getID().equals("SmallPlayer") && objectColliding.getID().equals("Hammer")) {
				if(objectColliding.getBounds().intersects(gameObjects.get(i).getBounds())) {
					gameObjects.get(i).setXSpeed(3);
					gameObjects.get(i).setYSpeed(-2);
				} else {
					gameObjects.get(i).setXSpeed(0);
				}
			}
		}

		// vertical collision
		objectColliding.getBounds().y += objectColliding.getYSpeed();
		for (int i = 0; i < gameObjects.size(); i++) {
			// block to players
			if (gameObjects.get(i).getID().equals("Block")
			 && objectColliding.getBounds().intersects(gameObjects.get(i).getBounds())
			&& (objectColliding.getID().equals("BigPlayer") || objectColliding.getID().equals("SmallPlayer"))) {
				objectColliding.getBounds().y -= objectColliding.getYSpeed();
				while (!gameObjects.get(i).getBounds().intersects(objectColliding.getBounds())) {
					objectColliding.getBounds().y += Math.signum(objectColliding.getYSpeed());
				}
				
				objectColliding.getBounds().y -= Math.signum(objectColliding.getYSpeed());
				objectColliding.setYSpeed(0);
				objectColliding.setY(objectColliding.getBounds().y);
			}
		}
	}
}
