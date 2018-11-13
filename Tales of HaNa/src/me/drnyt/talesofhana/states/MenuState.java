package me.drnyt.talesofhana.states;

import java.awt.Graphics;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.UI.ClickListener;
import me.drnyt.talesofhana.UI.UIImageButton;
import me.drnyt.talesofhana.UI.UIManager;
import me.drnyt.talesofhana.gfx.Assets;

public class MenuState extends State 
{
	
	private UIManager uiManager;
	
	public MenuState(Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(550, 300, 64, 64, Assets.start_button_static, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(handler.getGame().gameState);
				
			}	
		}));
	}
	
	@Override
	public void tick() 
	{
		//System.out.println(handler.getMouseManager().getMouseX() + "  " + handler.getMouseManager().getMouseY());
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		//g.setColor(Color.RED);
		//g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
		uiManager.render(g);
	}

}
