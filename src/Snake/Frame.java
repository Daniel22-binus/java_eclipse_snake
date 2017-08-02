package Snake;

import java.awt.GridLayout;

import javax.swing.JFrame;

import Snake.graphics.Screen;

public class Frame extends JFrame{
	
	public Frame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sbake");
		setResizable(false);
		
		init();
	}
	
	public void init() {
		//grid layout
		setLayout(new GridLayout(1, 1, 0, 0));
		
		//
		Screen s = new Screen();
		add(s);
		
		//whatever is in this window make the window that size - this is what pack does
		pack();
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new Frame();
	}

}
