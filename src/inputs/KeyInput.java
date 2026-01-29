package inputs;

import core.Panel;
import framework.Constants;
import framework.EntityState;
import framework.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	private Panel panel;
	private boolean movingRight;
	
	public KeyInput(Panel panel) {
		this.panel = panel;
		movingRight = false;
	}

	public void keyPressed(KeyEvent e) {
		switch(GameState.state) {
		case MENU:
			break;
		case PAUSE:
			break;
		case PLAY:
			if(e.getKeyCode() == KeyEvent.VK_A) {
				movingRight = false;
				panel.getBigPlayer().setEntityState(EntityState.WALKING_LEFT);
				panel.getBigPlayer().setXSpeed(-Constants.BIGPLAYER_XSPEED);
			}
			if(e.getKeyCode() == KeyEvent.VK_D) {
				movingRight = true;
				panel.getBigPlayer().setEntityState(EntityState.WALKING_RIGHT);
				panel.getBigPlayer().setXSpeed(Constants.BIGPLAYER_XSPEED);
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE && !panel.getSmallPlayer().isJumping()) {
				panel.getSmallPlayer().setJumping(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_W && panel.getBigPlayer().canThrowHammer()) {
				panel.getBigPlayer().getHammer().throwHammer();
			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				GameState.state = GameState.MENU;
			}
			break;
		case SETTINGS:
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				GameState.state = GameState.MENU;
			}
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A && !movingRight) {
			panel.getBigPlayer().setEntityState(EntityState.IDLE_LEFT);
			panel.getBigPlayer().setXSpeed(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_D && movingRight) {
			panel.getBigPlayer().setEntityState(EntityState.IDLE_RIGHT);
			panel.getBigPlayer().setXSpeed(0);
		}
	}
	
	public void keyTyped(KeyEvent e) { }
}
