package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean keys[];
	public boolean up = false, down = false, enter = false, easy = false, medium = false, hard = false;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		enter = keys[KeyEvent.VK_ENTER];
		easy = keys[KeyEvent.VK_1];
		medium = keys[KeyEvent.VK_2];
		hard = keys[KeyEvent.VK_3];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
