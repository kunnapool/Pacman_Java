package graphics;

import java.util.Random;

import graphics.sprites.Sprite;

public class Screen {
	
	
	private int height;
	private int width;
	
	private Random rand=new Random();
	public static int pac_dots_location[];
	
	
	public int screen_pixels[];
	
	public Screen(int width, int height)
	{
		this.height=height;
		this.width=width;
		screen_pixels=new int[this.width*this.height];
		pac_dots_location = new int[this.width*this.height];
		
		for(int i=0;i<pac_dots_location.length;i++)
			pac_dots_location[i]=rand.nextInt(500);
		
		clearScreen();
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
				if (screen_pixels[x+y*width]==0)
					screen_pixels[x+y*width]=0x696969;
				if(pac_dots_location[x+y*width]==1 && x+1+(y+1)*width<width*height)
				{
					screen_pixels[(x+1)+y*width]=0x12ff09;
					screen_pixels[x+(y+1)*width]=0x12ff09;
					screen_pixels[x+y*width]=0x12ff09;
					screen_pixels[x+1+(y+1)*width]=0x12ff09;
				}
				
				
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
	
	public void clearScreen()
	{
		for(int i=0;i<screen_pixels.length;i++)
			screen_pixels[i]=0;
	}
	
	
}
