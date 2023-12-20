package core;

import javax.swing.JFrame;

public class Frame {
	public Frame(Panel panel) {
		JFrame frame = new JFrame("Homie Hand");
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
