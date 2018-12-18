package graphics.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Level {
	
	public int map_pixels[];
	public int map_width, map_height;
	
	
	public static Level lvl=new Level(100, 100, "/levels/level1.png");
	
	
	public Level(int w, int h, String path)
	{
		this.map_width=w;
		this.map_height=h;
		map_pixels=new int[w*h];
		
		loadLevel(path);
	}
	
	
	
	public void renderLevel()
	{
		
		
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
			
			image.getRGB(0,0,w,h,map_pixels,0,w);
			
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Couldn't open level    :/    ");
		}
	}

}
