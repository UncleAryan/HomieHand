package core;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import assets.BigPlayer;
import assets.SmallPlayer;
import framework.Constants;
import framework.GameObjectHandler;
import framework.LevelHandler;
import inputs.KeyInput;
import inputs.MouseInput;

public class Panel extends JPanel implements Runnable {
	private MouseInput mouseInput;
	private final int FPS = 120, TICKS = 200;
	private Thread thread;
	private BigPlayer bigPlayer;
	private SmallPlayer smallPlayer;
	private LevelHandler levelHandler;
	private GameObjectHandler gameObjectHandler;
	
	public Panel() {
		setSize();
		loadInputs();
		setFocusable(true);
		requestFocusInWindow();
		loadGameObjects();
		start();
	}
	
	public void loadGameObjects() {
		gameObjectHandler = new GameObjectHandler();
		levelHandler = new LevelHandler(this);
		bigPlayer = new BigPlayer(200, 200, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, "BigPlayer");
		smallPlayer = new SmallPlayer(400, 200, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, "SmallPlayer");
		gameObjectHandler.addGameObject(bigPlayer);
		gameObjectHandler.addGameObject(smallPlayer);
	}
	
	public void tick() {
		gameObjectHandler.tick();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		gameObjectHandler.render(g);
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
		setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
	}
	
	public BigPlayer getBigPlayer() {
		return bigPlayer;
	}
	
	public SmallPlayer getSmallPlayer() {
		return smallPlayer;
	}
	
	public GameObjectHandler getGameObjectHandler() {
		return gameObjectHandler;
	}
}
