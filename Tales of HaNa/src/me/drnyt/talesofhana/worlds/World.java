package me.drnyt.talesofhana.worlds;

import java.awt.Graphics;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.entities.EntityManager;
import me.drnyt.talesofhana.entities.creatures.Player;
import me.drnyt.talesofhana.entities.statics.Tree;
import me.drnyt.talesofhana.items.ItemManager;
import me.drnyt.talesofhana.tile.Tile;
import me.drnyt.talesofhana.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	//Item
	private ItemManager itemManager;
	
	public World(Handler handler, String path)
	{
		this.handler = handler;
		
		//Player
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		//Item
		itemManager = new ItemManager(handler);
		//TREES
		entityManager.addEntity(new Tree(handler, 305, 85));	//corrected
		entityManager.addEntity(new Tree(handler, 245, 275));	//corrected
		entityManager.addEntity(new Tree(handler, 563, 343));	//corrected
		entityManager.addEntity(new Tree(handler, 435, 595));	//corrected
		entityManager.addEntity(new Tree(handler, 820, 465));	//corrected
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick()
	{
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g)
	{
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++)
		{
			for(int x = xStart; x < xEnd; x++)
			{
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height)
		{
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if(t == null)
		{
			return Tile.dirtTile;
		}
		
		return t;
		
		
	}
	
	private void loadWorld(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width + 4)]);
			}
		}
		
	}
	
	//Getters and Setters
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
}
