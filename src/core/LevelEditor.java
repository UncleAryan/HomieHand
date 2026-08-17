package core;

import framework.Constants;
import framework.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelEditor extends JPanel implements Runnable {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage level;
    private Thread thread;

    public LevelEditor() {
        loadWindow();
        startThread();
        loadLevel();
    }

    private void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    private void loadLevel() {
        level = ImageLoader.getSpriteSheet(ImageLoader.LEVEL_ONE);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(level, 0, 0, null);
    }

    public void tick() {

    }

    private void loadWindow() {
        frame = new JFrame("Level Editor");
        this.setPreferredSize(new Dimension((int) Constants.WIDTH , (int)Constants.HEIGHT));
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void run() {
        while(true) {
            try {
                repaint();
                tick();

                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
