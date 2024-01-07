package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import assets.Background;
import assets.BigPlayer;
import assets.SmallPlayer;
import framework.Constants;
import framework.GameObjectHandler;
import framework.GameState;
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
	private JLabel performanceStat;
	private Menu menu;
	
	public Panel() {
		setSize();
		loadInputs();
		setFocusable(true);
		requestFocusInWindow();
		loadGameObjects();
		start();
	}
	
	public void loadGameObjects() {
		menu = new Menu();
		performanceStat = new JLabel();
		performanceStat.setFont(new Font("Arial", Font.BOLD, 32));
		performanceStat.setForeground(Color.WHITE);
		performanceStat.setBounds(0, 0, 1280, 32);
		performanceStat.setText("FPS: 0" + " TICKS: 0");
		add(performanceStat);
		performanceStat.setVisible(false);
		gameObjectHandler = new GameObjectHandler();
		gameObjectHandler.addGameObject(new Background(0, 0, Constants.WIDTH, Constants.HEIGHT, 1, "Background"));
		levelHandler = new LevelHandler(this);
		bigPlayer = new BigPlayer(200, 200, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 4, "BigPlayer");
		smallPlayer = new SmallPlayer(400, 200, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 2, "SmallPlayer");
		gameObjectHandler.addGameObject(bigPlayer);
		gameObjectHandler.addGameObject(smallPlayer);
		gameObjectHandler.addGameObject(bigPlayer.getHammer());
	}
	
	public void tick() {
		switch(GameState.state) {
		case MENU:
			menu.tick();
			break;
		case PLAY:
			gameObjectHandler.tick();
			break;
		case PAUSE:
			break;
		default:
			break;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		switch(GameState.state) {
		case MENU:
			menu.render(g);
			break;
		case PLAY:
			gameObjectHandler.render(g);
			performanceStat.setVisible(true);
			break;
		case PAUSE:
			break;
		default:
			break;
		}
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
				performanceStat.setText("FPS: " + fps + " TICKS: " + ticks);
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
		setLayout(null);
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
	
	public Menu getMenu() {
		return menu;
	}
}
