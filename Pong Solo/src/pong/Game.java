package pong;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import input.KeyManager;
import states.CPUState;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable{

	private Thread thread;
	private boolean running = false;
	
	private Display display;
	private int width, height;
	private String title;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState;
	private State menuState;
	private State cpuState;
	
	private KeyManager keyManager;
	
	public Game(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		keyManager = new KeyManager();
	}
	
	@Override
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now;
		
		while(running) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if (delta >= 1) {
				update();
				render();
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		display = new Display(width, height, title);
		display.getFrame().addKeyListener(keyManager);
		
		menuState = new MenuState(this);
		cpuState = new CPUState(this);
		gameState = new GameState(this);
		State.setState(menuState);
	}
	
	private void update() {
		
		keyManager.update();
		
		if (State.getState() == null) {
			return;
		}
		State.getState().update();
	}
	
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if (State.getState() == null) {
			return;
		}
		State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public State getGameState() {
		return gameState;
	}
	
	public State getMenuState() {
		return menuState;
	}
	
	public State getCPUState() {
		return cpuState;
	}

}
