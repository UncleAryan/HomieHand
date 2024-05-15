package framework;

import java.awt.*;
import java.util.LinkedList;

public class GameObjectHandler {
	private LinkedList<GameObject> gameObjects;
	
	public GameObjectHandler() {
		gameObjects = new LinkedList<GameObject>();
	}
	
	public void tick() {
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).tick(gameObjects);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).render(g);
		}
	}
	
	public void addGameObject(GameObject gameObject) {
		this.gameObjects.add(gameObject);
	}
	
	public void removeGameObject(GameObject gameObject) {
		this.gameObjects.remove(gameObject);
	}
	
	public LinkedList<GameObject> getGameObjects(){
		return gameObjects;
	}
}
