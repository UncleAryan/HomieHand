package core;

import framework.Constants;
import framework.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelEditor {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage level;

    public LevelEditor() {
        loadWindow();
        loadLevel();
    }

    private void loadLevel() {
        level = ImageLoader.getSpriteSheet(ImageLoader.LEVEL_ONE);

    }

    public void render(Graphics g) {
        g.drawImage(level, 0, 0, null);
    }

    public void tick() {

    }

    private void loadWindow() {
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
