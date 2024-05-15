package framework;

public class Camera {
	private float x;
	private float y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player) {
		x = -player.getX() + Constants.WIDTH/2;
	}
	
	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
