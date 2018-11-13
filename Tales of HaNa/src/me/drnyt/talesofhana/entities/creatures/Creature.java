package me.drnyt.talesofhana.entities.creatures;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.entities.Entity;
import me.drnyt.talesofhana.tile.Tile;

/*
 * This is the parent class for living entities which move around
 */
public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 4.0f;		//Default Speed for all creatures
	public static final int DEFAULT_CREATURE_WIDTH = 64,	//Default Size for all creatures
							DEFAULT_CREATURE_HEIGHT = 64;	//	//	//	//	//	//	//	//
	
	protected float speed;	//The movement speed variable
	protected float xMove, yMove;	//The movement position variables

	public Creature(Handler handler, float x, float y, int width, int height) 
	{
		super(handler, x, y, width, height);	//Calls the constructor of the parent class "Entity"
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move()
	{
		if(!checkEntityCollisions(xMove, 0f))	//Executes the moveX method as long as the entity is not colliding with anything
			moveX();
		if(!checkEntityCollisions(0f, yMove))	//Executes the moveY method as long as the entity is not colliding with anything
			moveY();
	}
	
	/*
	 * This methods moves the player in the X axis as long as the player is not colliding with anything
	 */
	public void moveX()
	{
		if(xMove > 0)
		{
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) 
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
			{
				x += xMove;
			}
			else
			{
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		}
		else if(xMove < 0)
		{
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
			{
				x += xMove;
			}
			else
			{
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
	}
	
	
	/*
	 * This methods moves the player in the Y axis as long as the player is not colliding with anything
	 */
	public void moveY()
	{
		if(yMove < 0)
		{
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && 
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty))
			{
				y += yMove;
			}
			else
			{
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
		}
		else if (yMove > 0)
		{
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && 
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty))
			{
				y += yMove;
			}
			else
			{
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	/*
	 * This methods checks if the colliding tile is Solid or not
	 */
	protected boolean collisionWithTile(int x, int y)
	{
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//Getters and Setters
	
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
