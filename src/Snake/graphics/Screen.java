package Snake.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	//creating the constructor
	public Screen(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		start();
	}
	
	//this method is updating stuff
	public void tick(){
		
	}
	
	//painting on to this method
	public void paint(Graphics g){
		
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "Game Loop"); //must implement the interface Runnable
		thread.start();
	}
	
	public void stop(){
		
	}
	
	public void run(){
		while(running){
			tick();
			repaint();
		}
	}
}
