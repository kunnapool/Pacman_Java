package graphics.player;

import graphics.Screen;
import graphics.sprites.Sprite;
import input.Keyboard;

public class Player_pacman extends Mob {
	
	private Screen screen;
	public static int x_pos, y_pos;
	private Sprite sprite=Sprite.pac_right_open;
	private boolean flip_flag=false;
	
	public Player_pacman(int x_spwan, int y_spawn, Screen screen)
	{
		this.x_pos=x_spwan;
		this.y_pos=y_spawn;
		this.screen=screen;
		
		Sprite.revAll();
	}
	
	
	public void updatePosition(Keyboard key) {
		
		flip_flag=!flip_flag;
		
		if(key.up && y_pos>0)
		{
			direction=0;
			if (flip_flag)
				sprite=Sprite.pac_up_open;
			else
				sprite=Sprite.pac_up_closed;
			y_pos-=5;
		}

		if(key.down)
		{
			direction=2;
			if (flip_flag)
				sprite=Sprite.pac_down_open;
			else
				sprite=Sprite.pac_down_closed;
			y_pos+=5;
		}
		if(key.left && x_pos>0)
		{
			direction=3;
			if (flip_flag)
				sprite=Sprite.pac_left_closed;
			else
				sprite=Sprite.pac_left_open;
			x_pos-=5;
		}
		if(key.right)
		{
			direction=1;
			if (flip_flag)
				sprite=Sprite.pac_right_open;
			else
				sprite=Sprite.pac_right_closed;
			x_pos+=5;
		}
	}
	
	
	/**
	 * @return Absolute x position of player (pixel precision)
	 */
	public int getXPos()
	{
		return x_pos;
	}
	
	/**
	 * 
	 * @return Absolute y position of player (pixel precision)
	 */
	public int getYPos()
	{
		return y_pos;
	}
	
	protected boolean collision() {
		return false;
	}
	
	public void renderMob() {
		screen.renderSprite(x_pos, y_pos, sprite);
	}
	
}
