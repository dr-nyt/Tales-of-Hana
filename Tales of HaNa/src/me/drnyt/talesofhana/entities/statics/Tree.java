package me.drnyt.talesofhana.entities.statics;

import java.awt.Graphics;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.gfx.Assets;
import me.drnyt.talesofhana.items.Item;
import me.drnyt.talesofhana.tile.Tile;

public class Tree extends StaticEntity
{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);	//Calls the constructor of the parent class "StaticEntity"
		
		//Collision Box of the tree entity
		bounds.x = 30;
		bounds.y = 60;
		bounds.width = 30;
		bounds.height = 30;
	}

	@Override
	public void tick() 
	{
		
	}
	
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 80, 96, null);
		//g.setColor(Color.RED);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	/*
	 * This method executes commands on tree death
	 */
	@Override
	public void die()
	{
		handler.getWorld().getItemManager().addItem(Item.treeItem.createNew((int) (x + 25), (int) (y + 35)));
	}
	
	
	
}
