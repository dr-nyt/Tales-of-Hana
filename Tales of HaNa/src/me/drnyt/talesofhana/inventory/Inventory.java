package me.drnyt.talesofhana.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.gfx.Assets;
import me.drnyt.talesofhana.gfx.Text;
import me.drnyt.talesofhana.items.Item;

public class Inventory 
{
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int step = 0;
	
	private final int invX = 180,
							invY = 180,
							invWidth = 600,
							invHeight = 400,
							invListCenterX = invX + 185,
							invListCenterY = invY + invHeight / 2 + 5,
							invListSpacing = 30;
	
	private final int invImageSlotX = (invX + invWidth) - 139,
					  invImageSlotY = (invY) + 36,
					  invImageSlotWidth = 64, 
					  invImageSlotHeight = 64;
	
	private int invImageX,
				invImageY,
				invImageWidth, 
				invImageHeight;
	
	private int invCountX = (invX + invWidth) - 107,
				invCountY = (invY) + 130;
	
	private Rectangle itemBounds;
	private Rectangle invBounds;
	
	private Point mp;
	
	private int selectedItem = 0;
	
	public Inventory(Handler handler)
	{
		this.handler = handler;
		
		this.invImageX = this.invImageSlotX;
		this.invImageY = this.invImageSlotY;
		this.invImageWidth = this.invImageSlotWidth;
		this.invImageHeight = this.invImageSlotHeight;
		
		inventoryItems = new ArrayList<Item>();
		
		mp = new Point();
		
		addItem(Item.sandItem.createNew(5));
		addItem(Item.treeItem.createNew(8));
		//addItem(Item.grassItem.createNew(17));
		//addItem(Item.brickItem.createNew(64));
	}
	
	public void tick()
	{
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
			
		if(!active)
			return;
		
		/*
		System.out.println("INVENTORY WORKS!");
		for(Item i : inventoryItems)
		{
			System.out.println(i.getName() + " " + i.getCount());
		}*/
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
		
		if(handler.getMouseManager().mouseJustClicked(MouseEvent.BUTTON1))
		{	
			itemBounds = new Rectangle();
			itemBounds.x = invImageX;
			itemBounds.y = invImageY;
			itemBounds.width = invImageWidth;
			itemBounds.height = invImageHeight;
			
			mp.x = handler.getMouseManager().getMouseX();
			mp.y = handler.getMouseManager().getMouseY();
			
			if(itemBounds.contains(mp))
			{
				invImageX = mp.x - 32;
				invImageY = mp.y - 32;
				step = 1;
			}
			else
			{
				invImageX = mp.x - 32;
				invImageY = mp.y - 32;
			}
		}
		else
		{
			invBounds = new Rectangle();
			invBounds.x = invX;
			invBounds.y = invY;
			invBounds.width = invWidth;
			invBounds.height = invHeight;
			
			mp.x = handler.getMouseManager().getMouseX();
			mp.y = handler.getMouseManager().getMouseY();
			
			if(invBounds.contains(mp))
			{
				invImageX = invImageSlotX;
				invImageY = invImageSlotY;
				invImageWidth = invImageSlotWidth;
				invImageHeight = invImageSlotHeight;
				step = 0;
			}
			else if(step == 0)
			{
				handler.getWorld().getItemManager().addItem(inventoryItems.get(selectedItem).createNew((int) (handler.getWorld().getEntityManager().getPlayer().getX() + 5), (int) (handler.getWorld().getEntityManager().getPlayer().getY() + 5)));
				removeItem(inventoryItems.get(selectedItem));
				invImageX = invImageSlotX;
				invImageY = invImageSlotY;
				invImageWidth = invImageSlotWidth;
				invImageHeight = invImageSlotHeight;
				step = 1;
			}
			
		}
		
	}
	
	public void render(Graphics g)
	{
		if(!active)
			return;
		
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
		
		//Text.drawString(g, "> Wood Item <", invListCenterX, invListCenterY, true, Color.WHITE, Assets.slkscr28);
		
		int len = inventoryItems.size();
		if(len == 0)
			return;
		
		for(int i = -5; i < 6; i++)
		{
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			
			if(i == 0)
			{
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.slkscr28);
			}
			else
			{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true, Color.GRAY, Assets.slkscr28);
			}
		}
		
		Item item = inventoryItems.get(selectedItem);	//Gets the highlighted item in the inventory
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);	//Draws the item icon
		
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.slkscr28);	//Draws the number of items
	}
	
	//Inventory Methods
	
	public void addItem(Item item)
	{
		for(Item i : inventoryItems)
		{
			if(i.getId() == item.getId())
			{
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	public void removeItem(Item item)
	{
		item.setCount(item.getCount() - 1);
		if(item.getCount() <= 0)
		{
			inventoryItems.remove(item);
		}
	}

	//Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}
	
}