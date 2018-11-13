package me.drnyt.talesofhana.gfx;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.entities.Entity;
import me.drnyt.talesofhana.tile.Tile;

/*
 * This Class places the gameCamera in its correct position
 */
public class GameCamera {
	
	private float xOffset, yOffset;
	private Handler handler;
	
	public GameCamera(Handler handler, float xOffset, float yOffset)
	{
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	/*
	 * This method checks if the camera has reached the edge of the world and stops moving if it has
	 */
	public void checkBlankSpace()
	{
		if(xOffset < 0)
		{
			xOffset = 0;
		}
		else if(xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth())
		{
			xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
		}
		
		if(yOffset < 0)
		{
			yOffset = 0;
		}
		else if(yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight())
		{
			yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
		}
	}
	
	/*
	 * This method helps center the camera on an entity... usually the main player
	 */
	public void centerOnEntity(Entity e)
	{
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
		
	}

	/*
	 * This method moves the camera based on the factors defined
	 */
	public void move(float xAmt, float yAmt)
	{
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	//Getters and Setters
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
