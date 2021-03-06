package me.drnyt.talesofhana.entities.statics;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.entities.Entity;

/*
 * This is the parent class for static entities which dont need to move
 */
public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);	//Calls the constructor of the parent class "Entity"
	}
}
