package me.drnyt.talesofhana.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.drnyt.talesofhana.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 10;	//Default health for all entities
	
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected int health;
	protected boolean active = true;

	public Entity(Handler handler, float x, float y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	/*
	 * This method damages the entity when called, based on parameters and classes them dead aswell
	 */
	public void hurt(int amt)
	{
		health -= amt;
		if(health <= 0)
		{
			active = false;
			die();
		}
	}
	
	/*
	 * This method returns if the entity is living/true or dead/false
	 */
	public boolean isActive() {
		return active;
	}
	
	/*
	 * This method checks if the hitbox of the entity is colliding with another entity or solid object
	 */
	public boolean checkEntityCollisions(float xOffset, float yOffset)
	{
		for(Entity e : handler.getWorld().getEntityManager().getEntities())
		{
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * This method returns the hitbox of the entity
	 */
	public Rectangle getCollisionBounds(float xOffset, float yOffset)
	{
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	
	//Getters and Setters
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
