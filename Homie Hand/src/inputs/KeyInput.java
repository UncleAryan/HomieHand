package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import assets.SmallPlayer;
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
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			movingRight = false;
			panel.getBigPlayer().setAction(3);
			panel.getBigPlayer().setXSpeed(-Constants.BIGPLAYER_XSPEED);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movingRight = true;
			panel.getBigPlayer().setAction(2);
			panel.getBigPlayer().setXSpeed(Constants.BIGPLAYER_XSPEED);
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && panel.getSmallPlayer().isOnGround()) {
			panel.getSmallPlayer().setJumping(true);
		} 
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			panel.getBigPlayer().getHammer().throwHammer();
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT && !movingRight) {
			panel.getBigPlayer().setAction(1);
			panel.getBigPlayer().setXSpeed(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && movingRight) {
			panel.getBigPlayer().setAction(0);
			panel.getBigPlayer().setXSpeed(0);
		}
	}
	
	public void keyTyped(KeyEvent e) { }
}
