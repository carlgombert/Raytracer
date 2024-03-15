package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Raytracer extends Canvas {
	
	public static final int WIDTH = 800, HEIGHT = 400;
	
	private static final long serialVersionUID = -995199956205934258L;
	
	private Image image;

	public static void main(String[] args) {
		new Raytracer();
	}
	
	public Raytracer() {
		
		image = new Image(WIDTH, HEIGHT);
		
		image.generateImageData();
		
		image.Draw();
		
		new Window(WIDTH, HEIGHT, "raytracer", this);
	}
	
	public void paint(Graphics g) {
		 image.render(g);
    }
}
