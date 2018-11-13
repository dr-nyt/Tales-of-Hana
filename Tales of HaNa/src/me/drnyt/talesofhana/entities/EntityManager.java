package me.drnyt.talesofhana.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.entities.creatures.Player;

public class EntityManager {

	private Handler handler;
	private Player player;
	
	private ArrayList<Entity> entities;		//Stores entities
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>()	//This Anonymous class checks wether to render the entity before or after another entity
			{
				@Override
				public int compare(Entity a, Entity b) {
					if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
					{
						return -1;
					}
					return 1;
				}
			};
	
	public EntityManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	//Ticks the entity commands and removes them if they have been classed dead
	public void tick()
	{
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext())
		{
			Entity e = it.next();
			e.tick();
			if(!e.isActive())
				it.remove();
		}
		entities.sort(renderSorter);	//calls the render sort on every tick to define if the entity will get rendered first or after
	}
	
	/*
	 * This method renders all entities in the entities ArrayList
	 */
	public void render(Graphics g)
	{
		for(Entity e : entities)
		{
			e.render(g);
		}
		player.postRender(g);
	}
	
	/*
	 * This method adds entities to the entities ArrayList when called
	 */
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	//GETTER AND SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
}