package inputs;

import core.Panel;
import framework.Constants;
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
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				panel.getMainMenu().movePointerUp();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				panel.getMainMenu().movePointerDown();
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(panel.getMainMenu().getPointerPointing().equals("START")) {
					GameState.state = GameState.PLAY;
				}
				if(panel.getMainMenu().getPointerPointing().equals("SETTINGS")) {
					GameState.state = GameState.SETTINGS;
				}
				if(panel.getMainMenu().getPointerPointing().equals("EXIT")) {
					System.exit(0);
				}
			}
			break;
		case PAUSE:
			break;
		case PLAY:
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
