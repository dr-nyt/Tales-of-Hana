package me.drnyt.talesofhana.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//STATIC
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	//CLASS
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public int getId()
	{
		return id;
	}
}
