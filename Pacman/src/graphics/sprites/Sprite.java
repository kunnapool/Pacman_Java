package graphics.sprites;

import graphics.Screen;

public class Sprite {
	
	public int width, height;
	public final int SIZE;
	public int sprite_pixels[];
	
	
	public static final Sprite pac_open=new Sprite(16,16, SpriteSheet.pac2,0,0);
	public static final Sprite pac_closed=new Sprite(16,16, SpriteSheet.pac1,0,0);
	
	
	public Sprite(int height, int width, SpriteSheet sheet, int x_sh, int y_sh)
	{
		this.width=width;
		this.height=height;
		this.SIZE=width;
		this.sprite_pixels=new int[width*height];
		
		loadSprite(x_sh, y_sh,sheet);
		
	}
	
	private void loadSprite(int x_sh, int y_sh, SpriteSheet sheet)
	{
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
				sprite_pixels[x+y*width]=sheet.sprite_sheet_pixels[(x_sh+x)+(y+y_sh)*width];
		}
	}
	
	public static void spriteRender(int x_abs_on_screen, int y_abs_on_screen, Screen screen, boolean flag)
	{
		screen.renderSprite(x_abs_on_screen, y_abs_on_screen, flag);
	}

}
