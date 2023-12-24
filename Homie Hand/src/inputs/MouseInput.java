package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import core.Panel;

public class MouseInput implements MouseListener, MouseMotionListener {
	private Panel panel;
	private JLabel coords;
	
	public MouseInput(Panel panel) {
		this.panel = panel;
		coords = new JLabel();
		panel.add(coords);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		coords.setText("x: " + e.getX() + " | y: " + e.getY());
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
