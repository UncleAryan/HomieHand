package core;

import javax.swing.JFrame;

public class Frame {
	public Frame(Panel panel) {
		JFrame frame = new JFrame("Homie Hand");
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
}
