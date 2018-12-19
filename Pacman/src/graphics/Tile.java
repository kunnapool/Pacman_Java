package graphics;


import graphics.sprites.Sprite;

public class Tile {
	
	public final int SIZE;
	public Sprite sprite;
	public int tile_pixels[];
	
	
	public static Tile grass_tile=new Tile(16, Sprite.grass_sprite);
	public static Tile rock_tile= new Tile(16, Sprite.rock_sprite);
	public static Tile wood_tile=new Tile(16, Sprite.wood_sprite);
	public static Tile void_tile= new Tile(16, Sprite.void_sprite);
	
	
	public Tile(int size, Sprite sprite)
	{
		this.SIZE=size;
		tile_pixels=new int[SIZE*SIZE];
		
		for(int i=0;i<tile_pixels.length;i++)
		{
			tile_pixels[i]=sprite.sprite_pixels[i];
		}
				
	}
	
	public void renderTile(Tile tile, int xabs, int yabs, Screen screen)
	{
		screen.renderTile(this, xabs>>4, yabs>>4);
	}
	


}
