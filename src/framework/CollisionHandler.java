package framework;

import java.util.LinkedList;

/*
 * Will handle all collisions in game.
 * Algorithm based on RedFlyer Coding (YouTube).
 */
public class CollisionHandler {

	public static void tick(LinkedList<GameObject> gameObjects, GameObject objectColliding) {
		// Horizontal collision
		objectColliding.getBounds().x += objectColliding.getXSpeed();
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject other = gameObjects.get(i);

			// Block stops players and hammer
			if (other.getEntityType() == EntityType.BLOCK
					&& objectColliding.getBounds().intersects(other.getBounds())
					&& (objectColliding.getEntityType() == EntityType.BIG_PLAYER
					 || objectColliding.getEntityType() == EntityType.SMALL_PLAYER)) {
				objectColliding.getBounds().x -= objectColliding.getXSpeed();
				while (!other.getBounds().intersects(objectColliding.getBounds())) {
					objectColliding.getBounds().x += Math.signum(objectColliding.getXSpeed());
				}
				objectColliding.getBounds().x -= Math.signum(objectColliding.getXSpeed());
				objectColliding.setXSpeed(0);
				objectColliding.setX(objectColliding.getBounds().x);
			}

			// BigPlayer cannot throw hammer while touching SmallPlayer
			if (other.getEntityType() == EntityType.BIG_PLAYER
					&& objectColliding.getEntityType() == EntityType.SMALL_PLAYER) {
				other.setCanThrowHammer(!objectColliding.getBounds().intersects(other.getBounds()));
			}

			// knockback direction to the smallplayer
			if (other.getEntityType() == EntityType.SMALL_PLAYER
					&& objectColliding.getEntityType() == EntityType.HAMMER
					&& !objectColliding.hammerWithBigPlayer) {
				if (objectColliding.getBounds().intersects(other.getBounds())) {
					float direction = objectColliding.getXSpeed() > 0 ? Constants.KNOCKBACK_X : -Constants.KNOCKBACK_X;
					other.applyKnockback(direction, Constants.KNOCKBACK_Y);
				} else {
					other.setXSpeed(0);
				}
			}
		}

		// Vertical collision
		objectColliding.getBounds().y += objectColliding.getYSpeed();
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject other = gameObjects.get(i);

			if (other.getEntityType() == EntityType.BLOCK
					&& objectColliding.getBounds().intersects(other.getBounds())
					&& (objectColliding.getEntityType() == EntityType.BIG_PLAYER
					 || objectColliding.getEntityType() == EntityType.SMALL_PLAYER)) {
				objectColliding.getBounds().y -= objectColliding.getYSpeed();
				while (!other.getBounds().intersects(objectColliding.getBounds())) {
					objectColliding.getBounds().y += Math.signum(objectColliding.getYSpeed());
					objectColliding.onLand();
				}
				objectColliding.getBounds().y -= Math.signum(objectColliding.getYSpeed());
				objectColliding.setYSpeed(0);
				objectColliding.setY(objectColliding.getBounds().y);
			}
		}
	}
}
