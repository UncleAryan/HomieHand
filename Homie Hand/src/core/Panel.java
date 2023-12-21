package core;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import assets.BigPlayer;
import framework.Camera;
import framework.CollisionHandler;
import framework.LevelHandler;
import inputs.KeyInput;
import inputs.MouseInput;

public class Panel extends JPanel implements Runnable {
	public final static int DEFAULT_BLOCK_SIZE = 32;
	public final static int SCALE = 2;
	public final static int BLOCKS_IN_ROW = 26;
	public final static int BLOCKS_IN_COL = 14;
	public final static int BLOCK_SIZE = DEFAULT_BLOCK_SIZE * SCALE;
	public final static int WIDTH = BLOCK_SIZE * BLOCKS_IN_ROW;
	public final static int HEIGHT = BLOCK_SIZE * BLOCKS_IN_COL;
	private MouseInput mouseInput;
	private final int FPS = 120, TICKS = 200;
	private Thread thread;
	private BigPlayer bigPlayer;
	private LevelHandler levelHandler;
	private Camera camera;
	private CollisionHandler collisionHandler;
	
	// this is a comment
	
	public Panel() {
		setSize();
		loadInputs();
		setFocusable(true);
		requestFocusInWindow();
		loadGameObjects();
		start();
	}
	
	public void loadGameObjects() {
		levelHandler = new LevelHandler();
		bigPlayer = new BigPlayer(200, 200, BigPlayer.WIDTH, BigPlayer.HEIGHT, "BigPlayer");
		//camera = new Camera(0, 0);
		collisionHandler = new CollisionHandler(this);
	}
	
	public void tick() {
		bigPlayer.tick();
		levelHandler.tick();
		//camera.tick(bigPlayer);
		collisionHandler.tick();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//g.translate(camera.getX(), camera.getY());
		
		bigPlayer.render(g);
		levelHandler.render(g);
		
		//g.translate(-camera.getX(), -camera.getY());
	}
	
	public void run() {
		double timePerFrame = 1000000000 / FPS;
		double timePerTick = 1000000000 / TICKS;
		
		long preTime = System.nanoTime();
		long lastCheck = System.currentTimeMillis();
		
		int fps = 0;
		int ticks = 0;
		
		double changeInTicks = 0;
		double changeInFrames = 0;
		
		while(true) {
			long now = System.nanoTime();
			
			changeInTicks += (now - preTime) / timePerTick;
			changeInFrames += (now - preTime) / timePerFrame;
			preTime = now;
			
			if(changeInTicks >= 1) {
				tick();
				ticks++;
				changeInTicks--;
			}
			
			if(changeInFrames >= 1) {
				repaint();
				fps++;
				changeInFrames--;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + fps + " TICKS: " + ticks);
				fps = 0;
				ticks = 0;
			}
		}
	}
	
	private void start() {
		thread = new Thread(this);
		thread.start();
	}
	private void loadInputs() {
		addKeyListener(new KeyInput(this));
	    mouseInput = new MouseInput();
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
	}
	
	private void setSize() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
	}
	
	public BigPlayer getBigPlayer() {
		return bigPlayer;
	}
	
	public LevelHandler getLevelHandler() {
		return levelHandler;
	}
}
