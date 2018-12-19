package graphics.sprites;
import java.util.Collections;

import graphics.Screen;

public class Sprite {
	
	public int width, height;
	public int sprite_pixels[];
	public final int SIZE;
	
	public static final Sprite pac_right_open=new Sprite(16,16, SpriteSheet.pac2,0,0);
	public static final Sprite pac_right_closed=new Sprite(16,16, SpriteSheet.pac1,0,0);
	public static final Sprite pac_down_open=new Sprite(16,16, SpriteSheet.pac3,0,0);
	public static final Sprite pac_down_closed=new Sprite(16,16, SpriteSheet.pac4,0,0);
	
	public static final Sprite pac_up_open=new Sprite(16,16, SpriteSheet.pac3,0,0);
	public static final Sprite pac_up_closed=new Sprite(16,16, SpriteSheet.pac4,0,0);
	public static final Sprite pac_left_open=new Sprite(16,16, SpriteSheet.pac6,0,0);
	public static final Sprite pac_left_closed=new Sprite(16,16, SpriteSheet.pac5,0,0);
	
	public static final Sprite grass_sprite=new Sprite(16, 16, SpriteSheet.spawn_level,16,0);
	public static final Sprite rock_sprite=new Sprite(16, 16, SpriteSheet.spawn_level,0,16);
	public static final Sprite wood_sprite=new Sprite(16, 16, SpriteSheet.spawn_level,16,16);
	public static final Sprite void_sprite=new Sprite(16, 16, SpriteSheet.spawn_level,0, 32);
	
	
	public static void reverseSprite(Sprite sprite)
	{
		for(int i = 0; i < sprite.sprite_pixels.length / 2; i++)
		{
		    int temp = sprite.sprite_pixels[i];
		    sprite.sprite_pixels[i] = sprite.sprite_pixels[sprite.sprite_pixels.length - i - 1];
		    sprite.sprite_pixels[sprite.sprite_pixels.length - i - 1] = temp;
		}

	}
	
	public static void revAll()
	{
		reverseSprite(pac_up_open);
		reverseSprite(pac_up_closed);

	}
	
	
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
				sprite_pixels[x+y*width]=sheet.sprite_sheet_pixels[(x_sh+x)+(y+y_sh)*sheet.SIZE];
		}
	}
	
	public static void spriteRender(int x_abs_on_screen, int y_abs_on_screen, Screen screen, boolean flag)
	{
//		screen.renderSprite(x_abs_on_screen, y_abs_on_screen);
	}

}
