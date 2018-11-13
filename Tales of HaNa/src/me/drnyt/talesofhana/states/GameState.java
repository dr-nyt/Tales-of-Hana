package me.drnyt.talesofhana.states;

import java.awt.Graphics;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.worlds.World;

public class GameState extends State 
{
	
	private World world;
	
	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
		//handler.getGameCamera().move(100, 200);
	}

	@Override
	public void tick() 
	{
		world.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		world.render(g);
	}

}
