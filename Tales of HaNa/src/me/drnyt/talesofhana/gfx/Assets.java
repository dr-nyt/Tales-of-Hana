package me.drnyt.talesofhana.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

/*
 * This is the Assets class which loads and alligns all the sprites for later use
 */
public class Assets {
	
	@SuppressWarnings("unused")
	private static final int width = 64, height = 64, x = 64, y = 64;
	
	public static Font slkscr28;	
	
	public static BufferedImage cbble_wall, sand, grass, dirt, brick_wall, tree;
	
	public static BufferedImage start_button_static;
	public static BufferedImage[] start_button_anim;
	
	public static BufferedImage[] player_right, player_left, player_up, player_down, player_static_down, player_static_left, player_static_right;
	
	public static BufferedImage inventoryScreen;
	
	public static void init()
	{
		//Player Sprites
		
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/seku_sprite.png"));
		//player = playerSheet.crop(3, 4, 31, 38);
		player_right = new BufferedImage[4];
		player_right[0] = playerSheet.crop(128, 128, 128, 128);
		player_right[1] = playerSheet.crop(128 * 2, 128, 128, 128);
		player_right[2] = playerSheet.crop(128 * 3, 128, 128, 128);
		player_right[3] = playerSheet.crop(128 * 2, 128, 128, 128);
		
		player_left = new BufferedImage[4];
		player_left[0] = flip(playerSheet.crop(128, 128, 128, 128));
		player_left[1] = flip(playerSheet.crop(128 * 2, 128, 128, 128));
		player_left[2] = flip(playerSheet.crop(128 * 3, 128, 128, 128));
		player_left[3] = flip(playerSheet.crop(128 * 2, 128, 128, 128));
		
		player_up = new BufferedImage[1];
		player_up[0] = playerSheet.crop(0, 0, 128, 128);
		
		player_down = new BufferedImage[4];
		player_down[0] = playerSheet.crop(128, 0, 128, 128);
		player_down[1] = playerSheet.crop(128 * 2, 0, 128, 128);
		player_down[2] = playerSheet.crop(128 * 3, 0, 128, 128);
		player_down[3] = playerSheet.crop(128 * 2, 0, 128, 128);
		
		player_static_down = new BufferedImage[1];
		player_static_down[0] = playerSheet.crop(0, 0, 128, 128);			//Static Down
		
		player_static_left = new BufferedImage[1];
		player_static_left[0] = flip(playerSheet.crop(0, 128, 128, 128));	//Static Left
		
		player_static_right = new BufferedImage[1];
		player_static_right[0] = playerSheet.crop(0, 128, 128, 128);		//Static Right
		
		
		//World Sprites
		
		SpriteSheet groundSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ground_sprite.png"));
		cbble_wall = groundSheet.crop(0, 0, width, height);
		sand = groundSheet.crop(0, y, width, height);
		grass = groundSheet.crop(0, y * 2, width, height);
		dirt = groundSheet.crop(0, y * 3, width, height);
		brick_wall = groundSheet.crop(0, y * 4, width, height);
		
		//World Static Entity Sprites
		
		SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tree_sprite.png"));
		tree = treeSheet.crop(0, 0, 85, 120);
		
		//Start Menu Button Sprites
		
		//SpriteSheet startBSheet = new SpriteSheet(ImageLoader.loadImage("/textures/button_sprite.png"));
		start_button_anim = new BufferedImage[3];
		start_button_anim[0] = playerSheet.crop(128, 0, 128, 128);
		start_button_anim[1] = playerSheet.crop(128 * 2, 0, 128, 128);
		start_button_anim[2] = playerSheet.crop(128 * 3, 0, 128, 128);
		
		start_button_static = playerSheet.crop(0, 0, 128, 128);
		
		//Inventory Sprites
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventory_sprite.png");
		
		
		
		
		//Fonts
		slkscr28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
	}
	
	
	/*
	 * This method flips the images horizontally when called
	 */
    @SuppressWarnings("unused")
	private static BufferedImage flip(BufferedImage sprite)
    {
        BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        for(int xx = sprite.getWidth()-1;xx>0;xx--)
        {
            for(int yy = 0;yy < sprite.getHeight();yy++)
            {
                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
            }
        }
        return img;
    }
}
