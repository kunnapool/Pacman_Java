import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import graphics.Screen;
import graphics.level.Level;
import graphics.player.Player_pacman;
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
	Player_pacman pac_boi;
	
	
	
	/* Create and cast 'image' as a writable int array */
	private BufferedImage image= new BufferedImage(game_width, game_height, BufferedImage.TYPE_INT_RGB);
	private int game_pixels[]=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	public Game()
	{
		game_frame=new JFrame();
		key_presses=new Keyboard();
		
		Dimension d=new Dimension(game_width*scale_factor,game_height*scale_factor);
		setPreferredSize(d);
		
		
		
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.add(this); //show this game to the frame
		game_frame.setResizable(false);
		game_frame.pack();
		game_frame.setVisible(true);
		game_frame.setLocationRelativeTo(null); //center
		requestFocus();
		addKeyListener(key_presses);
		
		
		pac_boi=new Player_pacman(x, y, screen);

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
			
			//10 times a sec
			if(delta>=100)
			{
				updateLogic();
				delta=0;
				
			}
			last=now;
			
	
		}
	}
	
	
	private boolean flip_flag=false;
	/**
	 * Update logic
	 */
	public void updateLogic()
	{
		key_presses.update();
		pac_boi.updatePosition(key_presses);
		
	}

	
	/**
	 * Draw things onto the screen
	 */
	private void renderGame() {
	
		/* Canvas' graphic holding buffer */
		BufferStrategy graphics_buffer=getBufferStrategy();
		
		if (graphics_buffer==null)
		{
			createBufferStrategy(3);
			return;
		}
		
		
		
		
		/* ----------------------------------------------------------------*/
		
		screen.clearScreen();
//		screen.renderBackground();
		screen.renderLevel(Level.level, pac_boi);
//		screen.tryy();
		pac_boi.renderMob();
		
		
		
		/* ----------------------------------------------------------------*/
		
		
		
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
