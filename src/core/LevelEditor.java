package core;

import framework.Constants;

import javax.swing.*;
import java.awt.*;

public class LevelEditor {
    private JFrame frame;
    private JPanel panel;

    public LevelEditor() {
        frame = new JFrame("Level Editor");
        panel = new JPanel();
        panel.setPreferredSize(new Dimension((int) Constants.WIDTH , (int)Constants.HEIGHT));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
