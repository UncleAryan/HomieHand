package framework;

public class AnimationTicker {
	private int animationTick, animationIndex, animationSpeed;
	private int numColumes;
	public AnimationTicker(int speed, int numColumes) {
		animationSpeed = speed;
		this.numColumes = numColumes;
	}
	
	public void tickAnimation() {
		animationTick++;
		if(animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= numColumes) {
				animationIndex = 0;
			}
		}
	}
	
	public int getAnimationIndex() {
		return animationIndex;
	}
}
