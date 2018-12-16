package graphics.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private int height, width;
	public final int SIZE;
	private String path;
	
	protected int[] sprite_sheet_pixels;
	
	
	
	protected static final SpriteSheet pac1=new SpriteSheet(16,"/pac.png");
	protected static final SpriteSheet pac2=new SpriteSheet(16,"/pac_open.png");
	
	
	
	public SpriteSheet(int size, String path)
	{
		this.SIZE=size;
		this.path=path;
		
		sprite_sheet_pixels=new int[size*size];
		
		loadSpriteSheet();
	}
	
	
	
	private void loadSpriteSheet() {
		
		try {
			BufferedImage sh=ImageIO.read(SpriteSheet.class.getResource(path));
			int w=sh.getWidth();
			int h=sh.getHeight();
			
			sh.getRGB(0, 0, w, h, sprite_sheet_pixels, 0, w);
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
}
