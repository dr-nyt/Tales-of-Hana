package me.drnyt.talesofhana.gfx;

import java.awt.image.BufferedImage;

/*
 * This class defines the crop feature for the spreadsheets to simplfy the process
 */
public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
}
