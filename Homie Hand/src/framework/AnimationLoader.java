package framework;

import java.awt.image.BufferedImage;

public class AnimationLoader {
	private int animationTick, animationIndex, animationSpeed;
	private int numColumes;
	private BufferedImage[][] animations;
	
	public AnimationLoader(int speed) {
		animationSpeed = speed;
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
	
	public void loadAnimations(int r, int c, float width, float height, String filePath) {
		numColumes = c;
		BufferedImage image = ImageLoader.getSpriteSheet(filePath);
		
		animations = new BufferedImage[r][c];
		for(int row = 0; row < animations.length; row++) {
			for(int col = 0; col < animations[row].length; col++) {
				animations[row][col] = image.getSubimage(col * (int)width, row * (int)height, (int)width, (int)height);
			}
		}
	}
	
	public BufferedImage[][] getAnimations(){
		return animations;
	}
	
	public void setAnimationSpeed(int speed) {
		animationSpeed = speed;
	}
	
}
