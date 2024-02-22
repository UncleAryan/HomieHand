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
import framework.Camera;
import framework.Constants;
import framework.GameObjectHandler;
import framework.GameState;
import framework.LevelHandler;
import inputs.KeyInput;
import inputs.MouseInput;

public class Panel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private MouseInput mouseInput;
	private int FPS;
	private final int TICKS;
	private Thread thread;
	private BigPlayer bigPlayer;
	private SmallPlayer smallPlayer;
	private LevelHandler levelHandler;
	private GameObjectHandler gameObjectHandler;
	private JLabel performanceStat;
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;
	private boolean FPSChanged;
	
	public Panel() {
		TICKS = 200;
		
		setSize();
		loadInputs();
		setFocusable(true);
		requestFocusInWindow();
		loadGameObjects();
		start();
	}
	
	public void loadGameObjects() {
		FPS = 60;
		FPSChanged = false;
		performanceStat = new JLabel();
		performanceStat.setFont(new Font("Arial", Font.BOLD, 32));
		performanceStat.setForeground(Color.WHITE);
		performanceStat.setBounds(0, 0, 1280, 32);
		performanceStat.setText("FPS: 0" + " TICKS: 0");
		add(performanceStat);
		performanceStat.setVisible(false);
		gameObjectHandler = new GameObjectHandler();
		gameObjectHandler.addGameObject(new Background(0, 0, (int)Constants.WIDTH, (int)Constants.HEIGHT, 1, "Background"));
		levelHandler = new LevelHandler(this);
		bigPlayer = new BigPlayer(200, 200, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 4, "BigPlayer");
		smallPlayer = new SmallPlayer(400, 200, Constants.DEFAULT_GAMEOBJECT_WIDTH, Constants.DEFAULT_GAMEOBJECT_HEIGHT, 2, "SmallPlayer");
		gameObjectHandler.addGameObject(bigPlayer);
		gameObjectHandler.addGameObject(smallPlayer);
		gameObjectHandler.addGameObject(bigPlayer.getHammer());
		mainMenu = new MainMenu(this);
		settingsMenu = new SettingsMenu(this);
		
	}
	
	public void tick() {
		switch(GameState.state) {
		case MENU:
			mainMenu.tick();
			break;
		case PLAY:
			gameObjectHandler.tick();
			break;
		case PAUSE:
			break;
		case SETTINGS:
			settingsMenu.tick();
			break;
		default:
			break;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		switch(GameState.state) {
		case MENU:
			performanceStat.setVisible(false);
			mainMenu.render(g);
			break;
		case PLAY:
			gameObjectHandler.render(g);
			performanceStat.setVisible(true);
			break;
		case PAUSE:
			performanceStat.setVisible(false);
			break;
		case SETTINGS:
			performanceStat.setVisible(false);
			settingsMenu.render(g);
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
			if(FPSChanged) {
				timePerFrame = 1000000000 / FPS;
				timePerTick = 1000000000 / TICKS;
				
				preTime = System.nanoTime();
				lastCheck = System.currentTimeMillis();
				
				fps = 0;
				ticks = 0;
				
				changeInTicks = 0;
				changeInFrames = 0;
				
				FPSChanged = false;
			}
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
	    mouseInput = new MouseInput(this);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
	}
	
	private void setSize() {
		if(Constants.WIDTH == 1920 && Constants.HEIGHT == 1080) {
			
		}
		setPreferredSize(new Dimension((int)Constants.WIDTH , (int)Constants.HEIGHT));
		
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
	
	public MainMenu getMainMenu() {
		return mainMenu;
	}
	
	public SettingsMenu getSettingsMenu() {
		return settingsMenu;
	}
	
	public void setFPS(int fps) {
		FPS = fps;
	}
	
	public int getFPS() {
		return FPS;
	}
	
	public void FPSChanged(boolean value) {
		FPSChanged = value;
	}
}
