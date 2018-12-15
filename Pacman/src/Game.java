import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static int game_width=300;
	public static int game_height=game_width/16 *9;
	public static int scale_factor=3;
	private String game_title="Pacman";
	private boolean is_game_running=false;
	
	
	private Thread thread;
	private JFrame game_frame;
	
	
	/* Create and cast 'image' as a writable int array */
	private BufferedImage image= new BufferedImage(game_width, game_height, BufferedImage.TYPE_INT_RGB);
	private int game_pixels[]=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	public Game()
	{
		game_frame=new JFrame();
		
		Dimension d=new Dimension(game_width*scale_factor,game_height*scale_factor);
		setPreferredSize(d);
		
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.add(this); //show this game to the frame
		game_frame.setResizable(false);
		game_frame.pack();
		game_frame.setVisible(true);
		game_frame.setLocationRelativeTo(null); //center
		
		

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
	
	
	
	public void runGame()
	{
		while(is_game_running)
		{
			renderGame();
			updateLogic();
	
		}
	}
	
	
	public void updateLogic()
	{
		
	}
	
	
	private void renderGame() {
	
		/* Canva's graphic holding buffer */
		BufferStrategy graphics_buffer=getBufferStrategy();
		
		if (graphics_buffer==null)
		{
			createBufferStrategy(3);
			return;
		}
		
//		for(int i=0;i<game_pixels.length;i++)
//			game_pixels[i]=0;
		
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
