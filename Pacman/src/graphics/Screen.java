package graphics;

import java.util.Random;


import graphics.level.Level;
import graphics.player.Player_pacman;
import graphics.sprites.Sprite;
import graphics.sprites.SpriteSheet;
import input.Keyboard;

public class Screen {
	
	/**
	 * Pixel precision
	 */
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
	
	
	/**
	 * Renders level.
	 * Goes though all the tiles present in the level and draws accordingly
	 * @param level Level to render
	 */
	public void renderLevel(Level level, Player_pacman pac_boi)
	{
		
		
		int x_corner=pac_boi.getXPos();
		int y_corner=pac_boi.getYPos();
		

		int x0=x_corner>>4, y0=y_corner>>4;
		int x1=(x_corner + width+16)>>4, y1=(y_corner + height+16)>>4;
		
//		System.out.println("("+x0+","+y0+")");
//		System.out.println("("+x1+","+y1+")");
		
		
		for(int y=0, ym=y0;y+16<height && ym<y1;y+=16, ym++)
		{			
			for(int x=0, xm=x0;x+16<width && xm<x1;x+=16, xm++)
			{
				if(xm<0 || xm>=level.map_tile_width || y<0 || ym>=level.map_tile_height)
					break;
				renderTile(level.getTile(xm, ym), x, y);
					
			}
		}
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
	
	/**
	 * Renders the 'player' onto the screen
	 * @param x_abs The absolute x pixel value on the screen
	 * @param y_abs The absolute y pixel value on the screen
	 * @param sprite The 'player' to draw
	 */
	public void renderSprite(int x_abs, int y_abs, Sprite sprite)
	{
		
		if(x_abs>=width/2)
			x_abs=width/2;
		
		if(y_abs>=height/2)
			y_abs=height/2;
	
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
	
	/**
	 * Clears screen -- black
	 */
	public void clearScreen()
	{
		for(int i=0;i<screen_pixels.length;i++)
			screen_pixels[i]=0;
	}
	
	
	
}
