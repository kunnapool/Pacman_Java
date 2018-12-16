import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import graphics.Screen;
import graphics.sprites.Sprite;
import input.Keyboard;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*								Game Variables									*/
	/* ---------------------------------------------------------------------------- */
	
	
	public static int game_width=300;
	public static int game_height=game_width/16 *9;
	public static int scale_factor=3;
	private String game_title="Pacman";
	private boolean is_game_running=false;
	private int x=0, y=0;
	
	/*								Class objects needed							*/
	/* ---------------------------------------------------------------------------- */
	
	private Thread thread;
	private JFrame game_frame;
	private Screen screen=new Screen(game_width, game_height);
	private Keyboard key_presses;
	private Sprite pac;
	
	
	
	/* Create and cast 'image' as a writable int array */
	private BufferedImage image= new BufferedImage(game_width, game_height, BufferedImage.TYPE_INT_RGB);
	private int game_pixels[]=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	public Game()
	{
		game_frame=new JFrame();
		key_presses=new Keyboard();
		
		Dimension d=new Dimension(game_width*scale_factor,game_height*scale_factor);
		setPreferredSize(d);
		
		
		requestFocus();
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.add(this); //show this game to the frame
		game_frame.setResizable(false);
		game_frame.pack();
		game_frame.setVisible(true);
		game_frame.setLocationRelativeTo(null); //center
		addKeyListener(key_presses);

		startThread(); // --> this inturn call thread.start which calls runGame()
		
	}
	
	/**
	 * Starts the game thread 
	 */
	private synchronized void startThread()
	{
		is_game_running=true;
		
		thread=new Thread(this, "Pacman");
		
		thread.start(); //calls run
		
		
	}
	
	/**
	 * Safely stop the thread
	 */
	private synchronized void stopThread()
	{
		is_game_running=false;
		
		try
		{
			thread.join();
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Update and Render
	 */
	public void runGame()
	{
		long last=System.currentTimeMillis();
		long delta=0;
		
		while(is_game_running)
		{
			
			
			renderGame();
			
			long now = System.currentTimeMillis();
			delta+=now-last;
			
			if(delta>=100)
			{
				updateLogic();
				delta=0;
				
			}
			last=now;
			
	
		}
	}
	
	/**
	 * Update logic
	 */
	public void updateLogic()
	{
		//System.out.println(x);
		flag=!flag;
		key_presses.update();
		
		
		if(key_presses.up && y>=1)
			y--;
		if(key_presses.down)
			y++;
		if(key_presses.left && x>=1)
			x--;
		if(key_presses.right)
			x++;
		
		
		
	}
	
	
	
	private static boolean flag=false;
	
	/**
	 * Draw things onto the screen
	 */
	private void renderGame() {
	
		/* Canva's graphic holding buffer */
		BufferStrategy graphics_buffer=getBufferStrategy();
		
		if (graphics_buffer==null)
		{
			createBufferStrategy(3);
			return;
		}
		
		screen.renderBackground();
		Sprite.spriteRender(x, y, screen, flag );
		
		
		for(int i=0;i<game_pixels.length;i++)
			game_pixels[i]=screen.screen_pixels[i];
		
		
		/* Convert into a writable pixel format */
		Graphics g=graphics_buffer.getDrawGraphics();
		g.drawImage(image, 0,0, getWidth(), getHeight(), null);
		g.dispose();
		
		graphics_buffer.show();
		
		
	}
	
	public void run()
	{
		runGame();
	}
	
	
	public static void main(String args[])
	{
		System.out.println("Starting...");
		Game game=new Game();
		System.out.println("Stopping...");
		
	}

	
	
	

}
