package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import framework.GameState;

public class MouseInput implements MouseListener, MouseMotionListener {
	private int mouseX, mouseY;
	
	public MouseInput() {
		mouseX = 0;
		mouseY = 0;
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		GameState.state = GameState.PLAY;
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	public void updateMousePosition(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
}
