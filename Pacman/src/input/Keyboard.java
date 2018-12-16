package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
public boolean up, down, left, right;
private boolean id[]=new boolean[120];
	
	public Keyboard()
	{
		
	}
	
	public void update()
	{
		right= id[KeyEvent.VK_RIGHT] || id[KeyEvent.VK_D];
		up= id[KeyEvent.VK_UP] || id[KeyEvent.VK_W];
		left= id[KeyEvent.VK_LEFT] || id[KeyEvent.VK_A];
		down= id[KeyEvent.VK_DOWN] || id[KeyEvent.VK_S];
	}

	
	public void keyPressed(KeyEvent e) {
		
		id[e.getKeyCode()]=true;
		
	}

	
	public void keyReleased(KeyEvent e) {
		
				right=up=left=right=false;
				id[e.getKeyCode()]=false;
	}
	
	
	
	public void keyTyped(KeyEvent e) {
	
		
	}

}
