package me.drnyt.talesofhana.tile;

import me.drnyt.talesofhana.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.cbble_wall, id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}

}
