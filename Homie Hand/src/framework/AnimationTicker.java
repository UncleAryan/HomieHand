package framework;

public class AnimationTicker {
	private int animationTick, animationIndex, animationSpeed;

	public AnimationTicker(int speed) {
		animationSpeed = speed;
	}
	
	public void tickAnimation() {
		animationTick++;
		if(animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= 9) {
				animationIndex = 0;
			}
		}
	}
	
	public int getAnimationIndex() {
		return animationIndex;
	}
}
