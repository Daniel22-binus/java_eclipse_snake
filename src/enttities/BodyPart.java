package enttities;

import java.awt.Color;
import java.awt.Graphics;

public class BodyPart implements Tick {
	
	private int xCoor, yCoor, width, height;
	
	public BodyPart(int xCoor, int yCoor, int tileSize){
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}
	
	//this method will update
	public void tick(){
		
	}
	
	//method to draw on the screen
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		//draw a rectangle
		g.fillRect(xCoor * width, yCoor * height, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width + 2, yCoor * height + 2, width - 3, height - 3);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
}
