package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Panel;
import framework.Constants;

public class KeyInput implements KeyListener {
	private Panel panel;
	private boolean movingRight;
	
	public KeyInput(Panel panel) {
		this.panel = panel;
		movingRight = false;
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			movingRight = false;
			panel.getBigPlayer().setAction(3);
			panel.getBigPlayer().setXSpeed(-Constants.BIGPLAYER_XSPEED);
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			movingRight = true;
			panel.getBigPlayer().setAction(2);
			panel.getBigPlayer().setXSpeed(Constants.BIGPLAYER_XSPEED);
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && !panel.getSmallPlayer().isJumping()) {
			panel.getSmallPlayer().setJumping(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			panel.getBigPlayer().getHammer().throwHammer();
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A && !movingRight) {
			panel.getBigPlayer().setAction(1);
			panel.getBigPlayer().setXSpeed(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_D && movingRight) {
			panel.getBigPlayer().setAction(0);
			panel.getBigPlayer().setXSpeed(0);
		}
	}
	
	public void keyTyped(KeyEvent e) { }
}
