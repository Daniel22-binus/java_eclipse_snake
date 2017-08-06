package Snake.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import enttities.BodyPart;

public class Screen extends JPanel implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	//this is where the head of the snake will be
	private int xCoor = 10, yCoor = 10;
	//size of the head of the snake
	private int size = 3;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private int ticks = 0;
	
	//creating the constructor
	public Screen(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//intializw the snake
		snake = new ArrayList<BodyPart>();
		
		start();
	}
	
	//this method is updating stuff
	public void tick(){
		if(snake.size() == 0){
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		
		ticks++;
		
		if(ticks > 250000){
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			ticks = 0;
			
			//create new body part for the snake
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			
			//remove last part of the snake
			if(snake.size() > size){
				snake.remove(0);
			}
		}
	}
	
	//painting on to this method
	public void paint(Graphics g){
		//repaint the screen, clear the screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		
		g.setColor(Color.BLACK);
		//draw the grid
		for(int i = 0; i < WIDTH/10; i++){
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		
		for(int i = 0; i < HEIGHT/10; i++){
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
		
		//draw the arraylist
		for(int i = 0; i < snake.size(); i++){
			snake.get(i).draw(g);
		}
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
