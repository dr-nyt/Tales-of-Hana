package me.drnyt.talesofhana.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.drnyt.talesofhana.Handler;
import me.drnyt.talesofhana.entities.Entity;
import me.drnyt.talesofhana.gfx.Animation;
import me.drnyt.talesofhana.gfx.Assets;
import me.drnyt.talesofhana.inventory.Inventory;

public class Player extends Creature {
	
	//Attack Collision Box
	private Rectangle ar;
	private Rectangle cb;
	
	//Animations
	private Animation animRight;	//Animation Set for Right Movement
	private Animation animLeft;		//Animation Set for left Movement
	private Animation animUp;		//Animation Set for Up Movement
	private Animation animDown;		//Animation Set for Down Movement
	private Animation animStaticDown;	//Animation Set for NO Movement Looking Down
	private Animation animStaticLeft;	//Animation Set for NO Movement Looking Left
	private Animation animStaticRight;	//Animation Set for NO Movement Looking Right
	
	private int animSide = 0;
	
	private int animSpeed = (int) Creature.DEFAULT_SPEED;	//Defines the default animation speed

	//Attack Timer
	private long lastAttackTimer, attackCoolDown = 100, attackTimer = attackCoolDown;	//Defines the coolDown timer for meele attacks
	
	//Inventory
	private Inventory inventory;
	
	public Player(Handler handler, float x, float y) 
	{
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);	//Calls the constructor of the parent class "Creature"
		
		bounds.x = 50;			//Defines the x, y coordinates and width, height of the player hitbox relative to player sprite
		bounds.y = 58;			//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//
		bounds.width = 28;		//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//
		bounds.height = 69;		//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//
		
		//Animations
		animSpeed = 100;	//Animation Speed Override
		
		animRight = new Animation(animSpeed, Assets.player_right);		//Initializes the Right Movement animation set
		animLeft = new Animation(animSpeed, Assets.player_left);		//Initializes the Left Movement animation set
		animUp = new Animation(animSpeed, Assets.player_up);			//Initializes the Up Movement animation set
		animDown = new Animation(animSpeed, Assets.player_down);		//Initializes the Down Movement animation set
		animStaticDown = new Animation(animSpeed, Assets.player_static_down);	//Initializes the NO Movement Down animation set
		animStaticLeft = new Animation(animSpeed, Assets.player_static_left);	//Initializes the NO Movement Left animation set
		animStaticRight = new Animation(animSpeed, Assets.player_static_right);	//Initializes the NO Movement Right animation set
		
		//Inventory
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() 
	{
		//Animation
		animRight.tick();	//Ticks the Right Movement animation set
		animLeft.tick();	//Ticks the	Left Movement animation set
		animUp.tick();		//Ticks the Up Movement animation set
		animDown.tick();	//Ticks the	Down Movement animation set
		animStaticDown.tick();  //Ticks the	NO Movement Down animation set
		animStaticLeft.tick();  //Ticks the	NO Movement Left animation set
		animStaticRight.tick();  //Ticks the	NO Movement Right animation set
		
		//Movement
		getInput();
		move();
		
		//Camera
		handler.getGameCamera().centerOnEntity(this);	//Centers the camera on the player
		
		//Attack
		checkAttacks();
		
		//Inventory
		inventory.tick();
	}
	
	/*
	 * This method checks if the player is attacking an entity and if so then hurts the entity
	 */
	public void checkAttacks()
	{
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCoolDown)
			return;
		
		if(inventory.isActive())
			return;
		
		cb = getCollisionBounds(0, 0);
		ar = new Rectangle();		//Temporary box to check if the target entity is in range
		int arSize = 40;					//This variable defines range of the attack
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp)		//If the Player attacks Up
		{
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;	
		}
		else if(handler.getKeyManager().aDown)	//If the Player attacks Down
		{
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(handler.getKeyManager().aLeft)	//If the Player attacks Left
		{
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(handler.getKeyManager().aRight)	//If the Player attacks Right
		{
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else
		{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities())
		{
			if(e.equals(this))
				continue;
			
			if(e.getCollisionBounds(0, 0).intersects(ar))
			{
				e.hurt(1);	//this executest the hurt method and damages the entity based on the parameter
				return;
			}
		}
	}

	/*
	 * This method checks which direction the character intends to move and changes variables based on that
	 */
	public void getInput()
	{
		xMove = 0;
		yMove = 0;
		
		if(inventory.isActive())
			return;
		
		if(handler.getKeyManager().up)
		{
			yMove = -speed;
		}
		
		if(handler.getKeyManager().down)
		{
			yMove = speed;
		}
		
		if(handler.getKeyManager().left)
		{
			xMove = -speed;
		}
		
		if(handler.getKeyManager().right)
		{
			xMove = speed;
		}
	}
	
	/*
	 * This method renders the player on the screen
	 */
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width * 2, height * 2, null);
		//g.setColor(Color.RED);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		
		//Inventory
		inventory.render(g);
	}
	
	public void postRender(Graphics g)
	{
		inventory.render(g);
	}
	
	/*
	 * This method gets the animation set based on what direction the player is moving
	 */
	private BufferedImage getCurrentAnimationFrame()
	{
		if(xMove < 0)
		{
			animSide = 1;
			return animLeft.getCurrentFrame();
		}
		else if(xMove > 0)
		{
			animSide = 2;
			return animRight.getCurrentFrame();
		}
		else if(yMove < 0)
		{
			animSide = 0;
			return animUp.getCurrentFrame();
		}
		else if(yMove > 0)
		{
			animSide = 0;
			return animDown.getCurrentFrame();
		}
		else
		{
			if(animSide == 1)	//Looking Left
				return animStaticLeft.getCurrentFrame();
			else if(animSide == 2)	//Looking Right
				return animStaticRight.getCurrentFrame();
			
			return animStaticDown.getCurrentFrame();	//Default Look Down
		}
	}
	
	/*
	 * This method executes commands on player death
	 */
	@Override
	public void die()
	{
		
	}
	
	//Getters and Setters

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	

}
