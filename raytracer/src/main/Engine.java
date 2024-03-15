package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Engine extends Canvas {
	
	public static final int WIDTH = 800, HEIGHT = 400;
	
	private static final long serialVersionUID = -995199956205934258L;
	
	private Raytracer image;

	public static void main(String[] args) {
		new Engine();
	}
	
	public Engine() {
		
		image = new Raytracer(WIDTH, HEIGHT);
		
		image.generateImageData();
		
		image.draw();
		
		new Window(WIDTH, HEIGHT, "raytracer", this);
	}
	
	public void paint(Graphics g) {
		 image.render(g);
    }
}
