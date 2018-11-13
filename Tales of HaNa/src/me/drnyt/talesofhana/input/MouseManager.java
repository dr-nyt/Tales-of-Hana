package me.drnyt.talesofhana.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.drnyt.talesofhana.UI.UIManager;

/*
 * This class listens for Mouse keystrokes
 */
public class MouseManager implements MouseListener, MouseMotionListener 
{
	private boolean[] buttons;
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	public void setUIManager(UIManager uiManager)
	{
		this.uiManager = uiManager;
		buttons = new boolean[3];
	}
	
	public boolean mouseJustClicked(int buttonCode)
	{
		if(buttonCode < 0 || buttonCode >= buttons.length)		//if buttonCode is the same as the left mouse button
		{
			return false;
		}
		
		return buttons[buttonCode];
	}
	
	//Getters
	
	public boolean isLeftPressed()
	{
		return leftPressed;
	}
	
	public boolean isRightPressed()
	{
		return rightPressed;
	}
	
	public int getMouseX()
	{
		return mouseX;
	}
	
	public int getMouseY()
	{
		return mouseY;
	}
	
	//Implemented Methods
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			leftPressed = true;
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
		
		if(uiManager != null)
			uiManager.onMouseRelease(e);
		
		if(e.getButton() < 0 || e.getButton() >= buttons.length)
			return;
		
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(e.getButton() < 0 || e.getButton() >= buttons.length)
			return;
		
		buttons[e.getButton()] = true;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
