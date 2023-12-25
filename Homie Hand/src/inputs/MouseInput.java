package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import core.Panel;

public class MouseInput implements MouseListener, MouseMotionListener {
	private Panel panel;
	private JLabel coords;
	private int mouseX, mouseY;
	
	public MouseInput(Panel panel) {
		this.panel = panel;
		coords = new JLabel();
		panel.add(coords);
		mouseX = 0;
		mouseY = 0;
	}
	
	public void mouseDragged(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseMoved(MouseEvent e) {
		updateMousePosition(e);
		coords.setText("x: " + mouseX + " | y: " + mouseY);
	}

	public void mouseClicked(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mousePressed(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseReleased(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseEntered(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseExited(MouseEvent e) {
		updateMousePosition(e);
	}
	
	public void updateMousePosition(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
}
