package me.drnyt.talesofhana.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * This class listens for keyboard keystrokes
 */
public class KeyManager implements KeyListener {
	
	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight;
	
	public KeyManager()
	{
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick()
	{
		for(int i = 0; i < keys.length; i++)
		{
			if(cantPress[i] && !keys[i])
				cantPress[i] = false;
			
			else if(justPressed[i])
			{
				cantPress[i] = true;
				justPressed[i] = false;
			}
			
			if(!cantPress[i] && keys[i])
				justPressed[i] = true;	
		}
		
		up = keys[KeyEvent.VK_W];		//W
		down = keys[KeyEvent.VK_S];		//S
		left = keys[KeyEvent.VK_A];		//A
		right = keys[KeyEvent.VK_D];	//D
		
		aUp = keys[KeyEvent.VK_UP];			//UP_ARROW
		aDown = keys[KeyEvent.VK_DOWN];		//DOWN_ARROW
		aLeft = keys[KeyEvent.VK_LEFT];		//LEFT_ARROW
		aRight = keys[KeyEvent.VK_RIGHT];	//RIGHT_ARROW
		
	}

	public boolean keyJustPressed(int keyCode)
	{
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
	
		return justPressed[keyCode];
	}
	
	/*
	 *This method is executed whenever a key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	/*
	 *This method is executed whenever a key is released
	 */
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

}
