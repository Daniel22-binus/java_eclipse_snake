package Snake.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import enttities.*;

public class Screen extends JPanel implements Runnable, Tick, KeyListener{
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	//this is where the head of the snake will be
	private int xCoor = 10, yCoor = 10;
	//size of the head of the snake
	private int size = 3;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private int ticks = 0;
	
	//creating the constructor
	public Screen(){
		//to use pressed keys
		setFocusable(true);
		this.addKeyListener(this);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		r = new Random();
		
		//intializw the snake
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();
		
		start();
	}
	
	//this method is updating stuff
	public void tick(){
		if(snake.size() == 0){
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		
		//this will spawn a new apple
		if(apples.size() == 0){
			int xCoor = r.nextInt(79);
			int yCoor = r.nextInt(79);
			
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}
		
		for(int i = 0; i < apples.size(); i++){
			if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()){
				size++;
				apples.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < snake.size(); i++){
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){
				if(i != snake.size() - 1){
					stop();
				}
			}
		}
		if(xCoor < 0 || xCoor > 79 || yCoor < 0 || yCoor > 79){
			stop();
		}
		
		ticks++;
		
		if(ticks > 1000000){
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
		//repaint the screen, clear the screen, draw the snake as it is moving
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		
		g.setColor(Color.BLACK);
		//draw the grid
		// for(int i = 0; i < WIDTH/10; i++){
		// 	g.drawLine(i * 10, 0, i * 10, HEIGHT);
		// }
		
		// for(int i = 0; i < HEIGHT/10; i++){
		// 	g.drawLine(0, i * 10, WIDTH, i * 10);
		// }
		
		//draw the arraylist
		for(int i = 0; i < snake.size(); i++){
			snake.get(i).draw(g);
		}
		
		for(int i = 0; i < apples.size(); i++){
			apples.get(i).draw(g);
		}
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "Game Loop"); //must implement the interface Runnable
		thread.start();
	}
	
	public void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(running){
			tick();
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT && !left){
			up = false;
			down = false;
			right = true;
		}
		
		if(key == KeyEvent.VK_LEFT && !right){
			up = false;
			down = false;
			left = true;
		}
		
		if(key == KeyEvent.VK_UP && !down){
			left = false;
			right = false;
			up = true;
		}
		
		if(key == KeyEvent.VK_DOWN && !up){
			left = false;
			right = false;
			down = true;
		}
		
	}

}
