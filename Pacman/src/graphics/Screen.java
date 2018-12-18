package graphics;

import java.util.Random;

import graphics.level.Level;
import graphics.sprites.Sprite;
import graphics.sprites.SpriteSheet;

public class Screen {
	
	
	public static int width, height;
	
	private Random rand=new Random();
	public static int rand_map[];
	
	
	public int screen_pixels[];
	
	public Screen(int width, int height)
	{
		this.height=height;
		this.width=width;
		screen_pixels=new int[this.width*this.height];
		rand_map = new int[( this.width>>4) * ( this.height>>4 )];
		
		for(int i=0;i<rand_map.length;i++)
			rand_map[i]=rand.nextInt(4);
		
		clearScreen();
	}
	
	
	public void tryy()
	{
		for(int y=0;y<16;y++)
		{
			for(int x=0;x<16;x++)
			{
				screen_pixels[x+y*width]=Sprite.pac_down_closed.sprite_pixels[x+y*16];
			}
		}
	}
	
	/**
	 * Draw pixels onto the screen
	 */
	public void renderBackground()
	{
		for(int y=0, ym=0;y<height && ym<height>>4;y+=16, ym++)
		{
			for(int x=0, xm=0;x<width && xm<width>>4;x+=16, xm++)
			{
				if(rand_map[xm+ym*width>>4]==0)
					renderTile(Tile.grass_tile, x, y);
				else
					renderTile(Tile.void_tile, x, y);
					
			}
		}
	}
	
	
	public void renderLevel()
	{
		for(int i=0;i<Level.lvl.map_pixels.length;i++)
			screen_pixels[i]=Level.lvl.map_pixels[i];
	}
	
	
	/**
	 * Renders 1 tile onto the screen
	 * @param tile The tile to render
	 * @param xabs Absolute position of x on the screen
	 * @param yabs Absolute position of y on the screen
	 */
	public void renderTile(Tile tile, int xabs, int yabs)
	{
		
		for(int y=0;y<tile.SIZE;y++)
		{
			int y_screen= yabs + y;
			for(int x=0;x<tile.SIZE;x++)
			{
				int x_screen= xabs + x;
				screen_pixels[x_screen+y_screen*width]=tile.tile_pixels[x+y*tile.SIZE];
				
			}
		}
		
	}
	
	
	public void renderSprite(int x_abs, int y_abs, Sprite sprite)
	{
	
			for(int y=0;y<sprite.height;y++)
			{
				for(int x=0;x<sprite.width;x++)
				{
					int col=sprite.sprite_pixels[x+y*sprite.width];
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
