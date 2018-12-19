package graphics.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.Screen;
import graphics.Tile;


public class Level {
	
	private int map_pixels[];
	public int map_tile_width, map_tile_height;
	
	private int[] map_tiles;
	
	
//	public static Level lvl=new Level(100, 100, "/levels/level1.png");
	
	
	public static Level level=new Level(16, 16, "/levels/level.png");
	
	
	public Level(int w_t, int h_t, String path)
	{
		this.map_tile_width=w_t;
		this.map_tile_height=h_t;
		map_pixels=new int[(w_t<<4)*(h_t<<4)]; //required??
		
		map_tiles=new int[w_t*h_t];
		
		
		
		loadLevel(path);
	}
	
	/**
	 * Return tile at (x,y) (tile precision) in tile-map 
	 * @param x_tile x in tile precision
	 * @param y_tile x in tile precision
	 * @return
	 */
	public Tile getTile(int x_tile, int y_tile)
	{
		if((x_tile+y_tile*map_tile_width<256))
		{
			if( map_tiles[x_tile+y_tile*map_tile_width]==0xff00ff00 )
				return Tile.grass_tile;
			else if( map_tiles[x_tile+y_tile*map_tile_width]==0xffffff00 )
				return Tile.wood_tile;
			else if( map_tiles[x_tile+y_tile*map_tile_width]==0xff7f7f00 )
				return Tile.void_tile;
			else 
				return Tile.void_tile;
		}
		else
			return Tile.void_tile;
	}
	
	
	
	
	public void renderLevel(Screen screen)
	{
//		screen.renderLevel(this);
	}
	
	private void getLevelFromImage()
	{
		
	}
	
	
	private void loadLevel(String path)
	{
		try
		{
			BufferedImage image=ImageIO.read(Level.class.getResource(path));
			
			int h=image.getHeight();
			int w=image.getWidth();
			
			image.getRGB(0,0,w,h,map_tiles,0,w);
			
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Couldn't open level    :/    ");
		}
	}

}
