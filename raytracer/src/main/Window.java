package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Window {
	
	public Window(int width, int height, String title, Raytracer raytracer)
	{
		JFrame frame = new JFrame(title); 
		
		frame.setLayout(new BorderLayout());
		
		frame.setSize(width, height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.add(raytracer);
		frame.setVisible(true);
	}
}
