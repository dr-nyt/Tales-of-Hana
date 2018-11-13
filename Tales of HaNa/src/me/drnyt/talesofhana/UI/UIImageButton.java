package me.drnyt.talesofhana.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.drnyt.talesofhana.gfx.Animation;
import me.drnyt.talesofhana.gfx.Assets;

public class UIImageButton extends UIObject {

	private BufferedImage images;
	private ClickListener clicker;
	
	//Animation Button
	private Animation animButton;
	private int animSpeed = 100;
	
	public UIImageButton(float x, float y, int width, int height, BufferedImage images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
		
		animButton = new Animation(animSpeed, Assets.start_button_anim);
	}

	@Override
	public void tick() {
		animButton.tick();
	}

	@Override
	public void render(Graphics g) {
		if(hovering)
		{
			g.drawImage(animButton.getCurrentFrame(), (int) x, (int) y, width * 2, height * 2, null);
		}
		else
			g.drawImage(images, (int) x, (int) y, width * 2, height * 2, null);
			//g.setColor(Color.RED);
			//g.fillRect((int) (bounds.x), (int) (bounds.y), bounds.width, bounds.height);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
