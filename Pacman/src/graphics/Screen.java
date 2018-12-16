package graphics;

import graphics.sprites.Sprite;

public class Screen {
	
	
	private int height;
	private int width;
	
	public int screen_pixels[];
	
	public Screen(int width, int height)
	{
		this.height=height;
		this.width=width;
		screen_pixels=new int[this.width*this.height];
	}
	
	
	/**
	 * Draw pixels onto the screen
	 */
	public void renderBackground()
	{
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				screen_pixels[x+y*width]=0x696969;
			}
		}
	}
	
	
	
	
	
	public void renderSprite(int x_abs, int y_abs, boolean flag)
	{
		
		
		if(flag)
			for(int y=0;y<Sprite.pac_open.height;y++)
			{
				for(int x=0;x<Sprite.pac_open.width;x++)
				{
					int col=Sprite.pac_open.sprite_pixels[x+y*Sprite.pac_open.width];
					if(col!=0)
						screen_pixels[(x_abs + x) + (y + y_abs) * width]=col;
				}
			}
		else
			
			for(int y=0;y<Sprite.pac_closed.height;y++)
			{
				for(int x=0;x<Sprite.pac_closed.width;x++)
				{
					int col=Sprite.pac_closed.sprite_pixels[x+y*Sprite.pac_closed.width];
					
					if(col!=0)
						screen_pixels[(x_abs + x) + (y + y_abs) * width]=col;
				}
			}

	}
	



}
